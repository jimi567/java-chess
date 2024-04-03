package chess.dto.db;

import chess.domain.game.Movement;

public record MovementRequest(String chess_game_name, String sourceCoordinate, String targetCoordinate) {

    public static MovementRequest of(final String chess_game_name, final Movement movement) {
        return new MovementRequest(chess_game_name, MovementMapper.mapToString(movement.source()),
                MovementMapper.mapToString(movement.target()));
    }
}
