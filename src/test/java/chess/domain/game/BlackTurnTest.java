package chess.domain.game;

import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A4;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.A8;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.D5;
import static chess.domain.fixture.CoordinateFixture.D7;
import static chess.domain.fixture.CoordinateFixture.D8;
import static chess.domain.piece.directionmove.Queen.BLACK_QUEEN;
import static chess.domain.piece.fixedmove.King.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.End;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteTurn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(BlackTurn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("기물을 움직이고 나면 흰 팀의 차례가 된다.")
    @Test
    void move() {
        Board board = new Board();
        State blackTurn = new BlackTurn();

        assertThat(blackTurn.move(board, D7, D5)).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("기물을 움직였을때, King이 잡히면 게임이 종료된다.")
    @Test
    void moveCaseCapturedKing() {
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(D8, BLACK_QUEEN);
        pieces.put(D1, WHITE_KING);

        Board board = new Board(pieces);
        State blackTurn = new BlackTurn();

        assertThat(blackTurn.move(board, D8, D1)).isInstanceOf(End.class);
    }

    @DisplayName("source 좌표에 기물이 없으면 예외가 발생한다.")
    @Test
    void moveNothing() {
        Board board = new Board();
        State blackTurn = new BlackTurn();

        assertThatThrownBy(() -> blackTurn.move(board, A5, A8))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("블랙턴에서 흰색 기물을 움직이려고 하면 예외가 발생한다.")
    @Test
    void moveWhite() {
        Board board = new Board();
        State blackTurn = new BlackTurn();

        assertThatThrownBy(() -> blackTurn.move(board, A2, A4))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("지금은 흑팀의 차례입니다.");
    }
}
