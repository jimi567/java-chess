package chess.domain.game;

import chess.domain.board.Coordinate;

public record Movement(Coordinate source, Coordinate target) {
    
    public Movement {
        validate(source, target);
    }

    private void validate(Coordinate source, Coordinate target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 위치로 이동할 수 없습니다.");
        }
    }
}
