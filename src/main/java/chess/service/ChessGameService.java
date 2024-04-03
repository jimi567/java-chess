package chess.service;

import chess.db.dao.ChessGameDAO;
import chess.db.dao.MovementDAO;
import chess.db.dao.PieceDAO;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.domain.game.Movement;
import chess.domain.game.state.GameOver;
import chess.domain.game.state.Ready;
import chess.domain.piece.Piece;
import chess.dto.db.ChessGameRequest;
import chess.dto.db.ChessGameResponse;
import chess.dto.db.MovementRequest;
import chess.dto.db.MovementResponse;
import chess.dto.db.PieceRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private final MovementDAO movementDao = new MovementDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final Map<String, ChessGame> gameRooms;


    private String gameName;
    private ChessGame playingGame;


    public ChessGameService() {
        //chessGameDAO.deleteALL();
        gameRooms = new HashMap<>();
        List<ChessGameResponse> chessGameResponses = chessGameDAO.findAll();
        for (ChessGameResponse chessGameResponse : chessGameResponses) {
            gameRooms.put(chessGameResponse.name(), new ChessGame(chessGameResponse.state()));
        }
    }

    public List<String> gameNames() {
        return gameRooms.keySet().stream()
                .toList();
    }

    public void newGame(final String gameName) {
        chessGameDAO.addGame(ChessGameRequest.of(gameName, Ready.getInstance()));
        playingGame = new ChessGame(Ready.getInstance());
        this.gameName = gameName;
        gameRooms.put(gameName, playingGame);
    }

    public void selectGame(final String gameName) {
        this.gameName = gameName;
        playingGame = gameRooms.get(gameName);
        rollback();
    }

    private void rollback() {
        //TODO : Rollback
    }

    public void start() {
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
        Board board = playingGame.board();
        Map<Coordinate, Piece> pieces = board.pieces();
        List<PieceRequest> requests = new ArrayList<>();
        pieces.forEach((coordinate, piece) -> {
            requests.add(PieceRequest.of(gameName, coordinate, piece));
        });
        pieceDAO.addAll(requests);
        chessGameDAO.updateGame(ChessGameRequest.of(gameName, playingGame.state()));

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
