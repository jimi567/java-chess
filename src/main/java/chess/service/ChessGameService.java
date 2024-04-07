package chess.service;

import chess.db.dao.ChessGameDAO;
import chess.db.dao.MovementDAO;
import chess.domain.game.ChessGame;
import chess.domain.game.Movement;
import chess.domain.game.state.GameOver;
import chess.dto.db.ChessGameRequest;
import chess.dto.db.ChessGameResponse;
import chess.dto.db.MovementRequest;
import chess.dto.db.MovementResponse;
import java.util.List;

public class ChessGameService {
    private final MovementDAO movementDao = new MovementDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();

    public List<String> gameNames() {
        return chessGameDAO.findAll().stream()
                .map(ChessGameResponse::name)
                .toList();
    }

    public ChessGame newGame(final String gameName) {
        ChessGame newGame = new ChessGame();
        chessGameDAO.addGame(ChessGameRequest.of(gameName, newGame.state()));
        return newGame;
    }

    public ChessGame selectGame(final String gameName) {
        ChessGameResponse response = chessGameDAO.findByName(gameName);
        if (response == null) {
            return new ChessGame();
        }
        ChessGame chessGame = new ChessGame(response.state());
        rollback(chessGame, gameName);
        return chessGame;
    }

    private void rollback(final ChessGame chessGame, final String gameName) {
        List<Movement> movements = movementDao.findAllByGameName(gameName).stream()
                .map(MovementResponse::movement)
                .toList();
        for (Movement movement : movements) {
            chessGame.move(movement);
        }
    }

    public void addHistory(final String gameName, final Movement movement) {
        movementDao.addMovement(MovementRequest.of(gameName, movement));
    }

    public void removeHistory(final String gameName) {
        chessGameDAO.deleteOne(ChessGameRequest.of(gameName, GameOver.getInstance()));
    }
}
