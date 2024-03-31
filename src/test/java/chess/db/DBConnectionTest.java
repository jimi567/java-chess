package chess.db;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectionTest {
    @DisplayName("데이터베이스 연결을 테스트한다.")
    @Test
    void connection() {
        assertThat(DBConnection.getConnection()).isNotNull();
    }
}
