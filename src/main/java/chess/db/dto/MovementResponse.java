package chess.db.dto;

import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;

public record MovementResponse(Coordinate source, Coordinate target) {

    public static MovementResponse of(final String source, final String target) {
        return new MovementResponse(stringToCoordinate(source), stringToCoordinate(target));
    }

    private static Coordinate stringToCoordinate(final String coordinate) {
        return Coordinate.of(File.from(coordinate.charAt(0)), Rank.from(coordinate.charAt(1) - '0'));
    }
}
