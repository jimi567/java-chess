package chess.db.dto;

import chess.domain.board.Coordinate;

public record MovementRequest(String sourceCoordinate, String targetCoordinate) {

    public static MovementRequest of(Coordinate source, Coordinate target) {
        return new MovementRequest(coordinateToString(source), coordinateToString(target));
    }

    private static String coordinateToString(final Coordinate coordinate) {
        return FileConverter.convert(coordinate.file()) + RankConverter.convert(coordinate.rank());
    }
}
