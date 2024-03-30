package chess.domain.game;

import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.Map;

public class ChessStatus {
    private final Map<Team, BigDecimal> scores;

    public ChessStatus(final Map<Team, BigDecimal> scores) {
        this.scores = scores;
    }

    public BigDecimal blackScore() {
        return scores.get(Team.BLACK);
    }

    public BigDecimal whiteScore() {
        return scores.get(Team.WHITE);
    }
}
