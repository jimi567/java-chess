package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.PieceType;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        Score score = Score.from(PieceType.QUEEN);

        BigDecimal expected = new BigDecimal(9);

        assertThat(score.value()).isEqualTo(expected);
    }

}
