package chess.db.dao;

import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D5;
import static chess.domain.fixture.CoordinateFixture.D7;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.dto.MovementRequest;
import chess.db.dto.MovementResponse;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementDaoTest {

    private final MovementDao movementDao = new MovementDao();

    @BeforeEach
    void setUp() {
        movementDao.clear();
    }

    @AfterEach
    void clear() {
        movementDao.clear();
    }

    @DisplayName("움직임 정보를 저장할 수 있다.")
    @Test
    void addMovement() {
        MovementRequest movementRequest = MovementRequest.of(D2, D4);
        movementDao.addMovement(movementRequest);
    }

    @DisplayName("DB에 저장된 움직임 정보를 불러온다.")
    @Test
    void findAll() {
        MovementRequest movementRequest1 = MovementRequest.of(D2, D4);
        movementDao.addMovement(movementRequest1);

        MovementRequest movementRequest2 = MovementRequest.of(D7, D5);
        movementDao.addMovement(movementRequest2);

        MovementRequest movementRequest3 = MovementRequest.of(C2, C4);
        movementDao.addMovement(movementRequest3);

        List<MovementResponse> actual = movementDao.findAll();
        List<MovementResponse> expected = List.of(
                MovementResponse.of("d2", "d4"),
                MovementResponse.of("d7", "d5"),
                MovementResponse.of("c2", "c4")
        );

        assertThat(actual).containsExactlyElementsOf(expected);
    }
}
