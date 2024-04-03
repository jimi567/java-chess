package chess.service;

import chess.db.dao.MovementDAO;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessStatus;
import chess.domain.game.Movement;
import chess.dto.db.MovementRequest;
import chess.dto.db.MovementResponse;
import java.util.List;

public class ChessGameService {
    private final MovementDAO movementDao = new MovementDAO();
    private final ChessGame chessGame = new ChessGame();

    public void start() {
        List<Movement> movements = movementDao.findAll().stream()
                .map(MovementResponse::movement)
                .toList();
        chessGame.start(movements);
    }

    public void move(final Movement movement) {
        chessGame.move(movement);
        movementDao.addMovement(MovementRequest.of(movement));
    }

    public ChessStatus status() {
        return chessGame.status();
    }

    public void end() {
        chessGame.end();
        //TODO : BOARD 스냅샷 저장
    }

    public void gameOver() {
        movementDao.clear();
    }

    public boolean isGameOver() {
        return chessGame.isGameOver();
    }

    public boolean isRunning() {
        return chessGame.isRunning();
    }

    public Board chessBoard() {
        return chessGame.board();
    }
    
}
