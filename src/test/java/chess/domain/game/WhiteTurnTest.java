package chess.domain.game;

import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.A8;
import static chess.domain.fixture.CoordinateFixture.D1;
import static chess.domain.fixture.CoordinateFixture.D2;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.D8;
import static chess.domain.piece.directionmove.Queen.WHITE_QUEEN;
import static chess.domain.piece.fixedmove.King.BLACK_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.GameOver;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteTurn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @DisplayName("기물을 움직이고 나면 흑 팀의 차례가 된다.")
    @Test
    void move() {
        Board board = new Board();
        State whiteTurn = WhiteTurn.getInstance();

        assertThat(whiteTurn.move(board, D2, D4)).isInstanceOf(BlackTurn.class);
    }

    @DisplayName("기물을 움직였을때, King이 잡히면 게임이 종료된다.")
    @Test
    void moveCaseCapturedKing() {
        Map<Coordinate, Piece> pieces = new HashMap<>();
        pieces.put(D1, WHITE_QUEEN);
        pieces.put(D8, BLACK_KING);

        Board board = new Board(pieces);
        State whiteTurn = WhiteTurn.getInstance();

        assertThat(whiteTurn.move(board, D1, D8)).isInstanceOf(GameOver.class);
    }

    @DisplayName("source 좌표에 기물이 없으면 예외가 발생한다.")
    @Test
    void moveNothing() {
        Board board = new Board();
        State whiteTurn = WhiteTurn.getInstance();
        assertThatThrownBy(() -> whiteTurn.move(board, A5, A8))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("화이트턴에서 흑색 기물을 움직이려고 하면 예외가 발생한다.")
    @Test
    void moveBlack() {
        Board board = new Board();
        State whiteTurn = WhiteTurn.getInstance();
        assertThatThrownBy(() -> whiteTurn.move(board, A7, A5))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("지금은 흰팀의 차례입니다.");
    }
}
