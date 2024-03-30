package chess.domain.game;

import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.Map;

public record ChessStatus(Map<Team, BigDecimal> scores) {
    public BigDecimal blackScore() {
        return scores.get(Team.BLACK);
    }

    public BigDecimal whiteScore() {
        return scores.get(Team.WHITE);
    }
}
