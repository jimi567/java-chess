package chess.service;

import chess.db.dao.MovementDao;
import chess.db.dto.MovementRequest;
import chess.db.dto.MovementResponse;
import chess.domain.game.Movement;
import java.util.List;

public class ChessGameService {
    private final MovementDao movementDao = new MovementDao();

    public void addMovement(final Movement movement) {
        movementDao.addMovement(MovementRequest.of(movement));
    }

    public List<Movement> loadAllMovement() {
        return movementDao.findAll().stream()
                .map(MovementResponse::movement)
                .toList();
    }

    public void clearDB() {
        movementDao.clear();
    }
}
