package chess;

import chess.domain.game.ChessGame;
import chess.domain.game.Movement;
import chess.service.ChessGameService;
import chess.view.Command;
import chess.view.InputTokens;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.RoomNameToken;
import java.util.List;

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
            checkPresentRoomName();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            enterGameRoom();
        }
    }

    private void checkPresentRoomName() {
        List<String> roomNames = chessGameService.gameNames();
        RoomNameToken roomName = inputView.readGameRoomName(roomNames);
        while (roomName.isNotExit()) {
            checkStartOrContinue(roomNames, roomName);
            roomNames = chessGameService.gameNames();
            roomName = inputView.readGameRoomName(chessGameService.gameNames());
        }
    }

    private void checkStartOrContinue(final List<String> roomNames, final RoomNameToken roomName) {
        String gameName = roomName.value();
        if (roomName.isIn(roomNames)) {
            continueGame(gameName);
        }
        if (roomName.isNotIn(roomNames)) {
            startNewGame(roomName, gameName);
        }
    }

    private void startNewGame(final RoomNameToken roomName, final String gameName) {
        repeatUntilLegalState(() -> start(gameName, chessGameService.newGame(roomName.value())));
    }

    private void continueGame(final String gameName) {
        outputView.printStartMessage();
        repeatUntilLegalState(() -> proceed(gameName, chessGameService.selectGame(gameName)));
    }


    private void start(final String gameName, final ChessGame chessGame) {
        outputView.printStartMessage();
        Command command = repeatUntilLegalCommand();
        if (command.isStart()) {
            chessGame.start();
            repeatUntilLegalState(() -> proceed(gameName, chessGame));
            return;
        }

        if (command.isEnd()) {
            chessGame.end();
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void proceed(final String gameName, final ChessGame chessGame) {
        outputView.printBoard(chessGame.board());
        while (chessGame.isRunning()) {
            execute(gameName, chessGame);
        }

        if (chessGame.isGameOver()) {
            chessGameService.removeHistory(gameName);
        }
    }

    private void execute(final String gameName, final ChessGame chessGame) {
        InputTokens inputTokens = inputView.readCommand();
        Command command = Command.from(inputTokens);
        if (command.isMove()) {
            Movement movement = command.movement(inputTokens);
            chessGame.move(movement);
            chessGameService.addHistory(gameName, movement);
            outputView.printBoard(chessGame.board());
            return;
        }

        if (command.isStatus()) {
            outputView.printStatus(chessGame.status());
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
