package chess.domain.game;

import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D4;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Movement(D2, D4))
                .doesNotThrowAnyException();
    }

    @DisplayName("움직임은 동일한 위치로 갈 수 없다.")
    @Test
    void createFail() {
        assertThatThrownBy(() -> new Movement(D2, D2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
