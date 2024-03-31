package chess.db.dto;

import chess.domain.board.Rank;
import java.util.Arrays;

public enum RankConverter {

    ONE(Rank.ONE, "1"),
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),

    ;

    private final Rank rank;
    private final String value;

    RankConverter(final Rank rank, final String value) {
        this.rank = rank;
        this.value = value;
    }

    public static String convert(final Rank rank) {
        return Arrays.stream(RankConverter.values())
                .filter(fileConverter -> fileConverter.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 행 값 입니다."))
                .value;
    }
}
