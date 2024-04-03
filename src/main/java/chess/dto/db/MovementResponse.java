package chess.dto.db;

import chess.domain.game.Movement;

public record MovementResponse(long chess_game_id, Movement movement) {

    public static MovementResponse of(final long chess_game_id, final String source, final String target) {
        return new MovementResponse(chess_game_id, MovementMapper.mapToMovement(source, target));
    }
}
