package chess.domain.game;

import chess.domain.piece.PieceType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final Map<PieceType, Score> SCORE_POOL;

    static {
        SCORE_POOL = new HashMap<>();
        SCORE_POOL.put(PieceType.QUEEN, new Score(new BigDecimal(9)));
        SCORE_POOL.put(PieceType.ROOK, new Score(new BigDecimal(5)));
        SCORE_POOL.put(PieceType.BISHOP, new Score(new BigDecimal(3)));
        SCORE_POOL.put(PieceType.KNIGHT, new Score(new BigDecimal("2.5")));
        SCORE_POOL.put(PieceType.PAWN, new Score(BigDecimal.ONE));
        SCORE_POOL.put(PieceType.KING, new Score(BigDecimal.ZERO));
        SCORE_POOL.put(PieceType.EMPTY, new Score(BigDecimal.ZERO));
    }

    private final BigDecimal value;

    private Score(final BigDecimal value) {
        this.value = value;
    }

    public static Score from(final PieceType type) {
        if (SCORE_POOL.containsKey(type)) {
            return SCORE_POOL.get(type);
        }
        throw new IllegalArgumentException("존재하지 않는 기물 점수 입니다.");
    }

    public BigDecimal value() {
        return value;
    }
}
