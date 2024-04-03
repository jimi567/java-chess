package chess.dto.db;

import chess.domain.game.Movement;

public record MovementResponse(String chess_game_name, Movement movement) {

    public static MovementResponse of(final String chess_game_name, final String source, final String target) {
        return new MovementResponse(chess_game_name, MovementMapper.mapToMovement(source, target));
    }
}
