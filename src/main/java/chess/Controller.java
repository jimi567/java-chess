package chess;

import chess.db.dto.MovementResponse;
import chess.domain.board.Coordinate;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.service.ChessGameService;
import chess.view.Command;
import chess.view.InputTokens;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    private final ChessGame chessGame = new ChessGame();
    private final ChessGameService chessGameService = new ChessGameService();

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
            List<MovementResponse> movementHistory = chessGameService.loadAllMovement();
            chessGame.start(movementHistory);
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
            execute();
        }

        if (chessGame.isGameOver()) {
            chessGameService.clearDB();
        }
    }

    private void execute() {
        InputTokens inputTokens = inputView.readCommand();
        Command command = Command.from(inputTokens);
        if (command.isMove()) {
            Coordinate sourceCoordinate = command.sourceCoordinate(inputTokens);
            Coordinate targetCoordinate = command.targetCoordinate(inputTokens);
            chessGame.move(sourceCoordinate, targetCoordinate);
            chessGameService.addMovement(sourceCoordinate, targetCoordinate);
            outputView.printBoard(chessGame.board());
            return;
        }

        if (command.isStatus()) {
            ChessStatus status = chessGame.status();
            outputView.printStatus(status);
            return;
        }

        if (command.isEnd()) {
            chessGame.end();
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
