package chess.dto.db;

import chess.domain.game.Movement;

public record MovementRequest(long chess_game_id, String sourceCoordinate, String targetCoordinate) {

    public static MovementRequest of(final long chess_game_id, final Movement movement) {
        return new MovementRequest(chess_game_id, MovementMapper.mapToString(movement.source()),
                MovementMapper.mapToString(movement.target()));
    }
}
