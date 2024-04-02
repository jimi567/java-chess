package chess.dto.db;

import chess.domain.game.Movement;

public record MovementRequest(String sourceCoordinate, String targetCoordinate) {

    public static MovementRequest of(final Movement movement) {
        return new MovementRequest(MovementMapper.mapToString(movement.source()),
                MovementMapper.mapToString(movement.target()));
    }
}
