package chess.db.dto;

import chess.domain.game.Movement;

public record MovementResponse(Movement movement) {

    public static MovementResponse of(final String source, final String target) {
        return new MovementResponse(MovementMapper.mapToMovement(source, target));
    }
}
