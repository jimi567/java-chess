package chess.dto.db;

import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.game.Movement;
import java.util.HashMap;
import java.util.Map;

public class MovementMapper {

    private static final Map<File, String> FILE_STRING;
    private static final Map<Rank, String> RANK_STRING;

    static {
        FILE_STRING = new HashMap<>();
        RANK_STRING = new HashMap<>();

        FILE_STRING.put(File.A, "a");
        FILE_STRING.put(File.B, "b");
        FILE_STRING.put(File.C, "c");
        FILE_STRING.put(File.D, "d");
        FILE_STRING.put(File.E, "e");
        FILE_STRING.put(File.F, "f");
        FILE_STRING.put(File.G, "g");
        FILE_STRING.put(File.H, "h");

        RANK_STRING.put(Rank.ONE, "1");
        RANK_STRING.put(Rank.TWO, "2");
        RANK_STRING.put(Rank.THREE, "3");
        RANK_STRING.put(Rank.FOUR, "4");
        RANK_STRING.put(Rank.FIVE, "5");
        RANK_STRING.put(Rank.SIX, "6");
        RANK_STRING.put(Rank.SEVEN, "7");
        RANK_STRING.put(Rank.EIGHT, "8");
    }

    public static Movement mapToMovement(final String source, final String target) {
        return new Movement(mapToCoordinate(source), mapToCoordinate(target));
    }

    static Coordinate mapToCoordinate(final String coordinate) {
        return Coordinate.of(File.from(coordinate.charAt(0)), Rank.from(coordinate.charAt(1) - '0'));
    }

    static String mapToString(final Coordinate coordinate) {
        return FILE_STRING.get(coordinate.file()) + RANK_STRING.get(coordinate.rank());
    }
}
