package service;

import chess.db.dao.MovementDao;
import chess.db.dto.MovementRequest;
import chess.db.dto.MovementResponse;
import chess.domain.board.Coordinate;
import java.util.List;

public class ChessGameService {
    private final MovementDao movementDao = new MovementDao();

    public void addMovement(final Coordinate source, final Coordinate target) {
        movementDao.addMovement(MovementRequest.of(source, target));
    }

    public List<MovementResponse> loadAllMovement() {
        return movementDao.findAll();
    }

    public void clearDB() {
        movementDao.clear();
    }
}
