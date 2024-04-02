package chess;

import static chess.dto.MovementMapper.mapToMovement;

import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.domain.game.Movement;
import chess.dto.MovementRequest;
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
            List<Movement> movements = chessGameService.loadAllMovement();
            chessGame.start(movements);
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
            MovementRequest movementRequest = command.movementRequest(inputTokens);
            Movement movement = mapToMovement(movementRequest.sourceCoordinate(), movementRequest.targetCoordinate());
            chessGame.move(movement);
            chessGameService.addMovement(movement);
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
