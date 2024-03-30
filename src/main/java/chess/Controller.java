package chess;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.view.Command;
import chess.view.InputTokens;
import chess.view.InputView;
import chess.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    private final ChessGame chessGame = new ChessGame();

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        repeatUntilLegalState(this::start);
    }

    private void start() {
        Command command = repeatUntilLegalCommand();
        if (command.isStart()) {
            chessGame.start();
            repeatUntilLegalState(this::proceed);
            return;
        }

        if (command.isEnd()) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void proceed() {
        outputView.printBoard(chessGame.board());
        while (chessGame.isRunning()) {
            InputTokens inputTokens = inputView.readCommand();
            Command command = Command.from(inputTokens);
            if (command.isMove()) {
                chessGame.move(command.sourceCoordinate(inputTokens), command.targetCoordinate(inputTokens));
                outputView.printBoard(chessGame.board());
                continue;
            }

            if (command.isStatus()) {
                ChessStatus status = chessGame.status();
                outputView.printStatus(status);
                continue;
            }

            if (command.isEnd()) {
                chessGame.end();
                break;
            }
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private Command repeatUntilLegalCommand() {
        try {
            InputTokens inputTokens = inputView.readCommand();
            return Command.from(inputTokens);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatUntilLegalCommand();
        }
    }

    private Runnable repeatUntilLegalState(final Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return repeatUntilLegalState(runnable);
        }
    }
}
