package chess;

import chess.service.ChessGameService;
import chess.view.Command;
import chess.view.InputTokens;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.NoSuchElementException;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGameService chessGameService = new ChessGameService();

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        enterGameRoom();
    }

    private void enterGameRoom() {
        try {
            String gameName = inputView.readGameRoomName(chessGameService.gameNames());
            chessGameService.selectGame(gameName);
            chessGameService.rollback();
            repeatUntilLegalState(this::proceed);
        } catch (NoSuchElementException e) {
            outputView.printStartMessage();
            chessGameService.newGame(e.getMessage());
            repeatUntilLegalState(this::start);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            enterGameRoom();
        }
    }


    private void start() {
        Command command = repeatUntilLegalCommand();
        if (command.isStart()) {
            chessGameService.start();
            repeatUntilLegalState(this::proceed);
            return;
        }

        if (command.isEnd()) {
            chessGameService.end();
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void proceed() {
        outputView.printBoard(chessGameService.chessBoard());
        while (chessGameService.isRunning()) {
            execute();
        }

        if (chessGameService.isGameOver()) {
            chessGameService.gameOver();
        }
    }

    private void execute() {
        InputTokens inputTokens = inputView.readCommand();
        Command command = Command.from(inputTokens);
        if (command.isMove()) {
            chessGameService.move(command.movement(inputTokens));
            outputView.printBoard(chessGameService.chessBoard());
            return;
        }

        if (command.isStatus()) {
            outputView.printStatus(chessGameService.status());
            return;
        }

        if (command.isEnd()) {
            chessGameService.end();
            return;
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
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
