package chess.service;

import chess.db.dao.ChessGameDAO;
import chess.db.dao.MovementDAO;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.domain.game.Movement;
import chess.domain.game.state.GameOver;
import chess.dto.db.ChessGameRequest;
import chess.dto.db.ChessGameResponse;
import chess.dto.db.MovementRequest;
import chess.dto.db.MovementResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final MovementDAO movementDao = new MovementDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final Map<String, ChessGame> gameRooms;


    private String gameName;
    private ChessGame playingGame;


    public ChessGameService() {
        gameRooms = new HashMap<>();
        List<ChessGameResponse> chessGameResponses = chessGameDAO.findAll();
        for (ChessGameResponse chessGameResponse : chessGameResponses) {
            gameRooms.put(chessGameResponse.name(), new ChessGame());
        }
    }

    public List<String> gameNames() {
        return gameRooms.keySet().stream()
                .toList();
    }

    public void newGame(final String gameName) {
        playingGame = new ChessGame();
        this.gameName = gameName;
        chessGameDAO.addGame(ChessGameRequest.of(gameName, playingGame.state()));
    }

    public void selectGame(final String gameName) {
        this.gameName = gameName;
        playingGame = gameRooms.get(gameName);
    }

    public void start() {
        playingGame.start(List.of());
    }

    public void rollback() {
        List<Movement> movements = movementDao.findAllByGameName(gameName).stream()
                .map(MovementResponse::movement)
                .toList();
        playingGame.start(movements);
    }

    public void move(final Movement movement) {
        playingGame.move(movement);
        movementDao.addMovement(MovementRequest.of(gameName, movement));
    }

    public ChessStatus status() {
        return playingGame.status();
    }

    public void end() {
        gameRooms.put(gameName, new ChessGame());
        playingGame.end();
    }

    public void gameOver() {
        chessGameDAO.deleteOne(ChessGameRequest.of(gameName, GameOver.getInstance()));
    }

    public boolean isGameOver() {
        return playingGame.isGameOver();
    }

    public boolean isRunning() {
        return playingGame.isRunning();
    }

    public Board chessBoard() {

        return playingGame.board();
    }

}
