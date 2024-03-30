package chess.domain.piece.fixedmove;

import static chess.domain.board.Weight.ONE_LEFT_TWO_DOWN;
import static chess.domain.board.Weight.ONE_LEFT_TWO_UP;
import static chess.domain.board.Weight.ONE_RIGHT_TWO_DOWN;
import static chess.domain.board.Weight.ONE_RIGHT_TWO_UP;
import static chess.domain.board.Weight.TWO_LEFT_ONE_DOWN;
import static chess.domain.board.Weight.TWO_LEFT_ONE_UP;
import static chess.domain.board.Weight.TWO_RIGHT_ONE_DOWN;
import static chess.domain.board.Weight.TWO_RIGHT_ONE_UP;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class Knight extends FixedMovePiece {
    public static final Piece WHITE_KNIGHT = new Knight(WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(BLACK);
    public static final BigDecimal SCORE = new BigDecimal("2.5");

    private Knight(Team team) {
        super(PieceType.KNIGHT, team);
    }

    @Override
    Set<Entry<Integer, Integer>> weights() {
        return Set.of(
                ONE_LEFT_TWO_UP.value(),
                ONE_LEFT_TWO_DOWN.value(),
                TWO_LEFT_ONE_DOWN.value(),
                TWO_LEFT_ONE_UP.value(),
                ONE_RIGHT_TWO_DOWN.value(),
                ONE_RIGHT_TWO_UP.value(),
                TWO_RIGHT_ONE_DOWN.value(),
                TWO_RIGHT_ONE_UP.value()
        );
    }

    @Override
    public BigDecimal score(final List<Piece> sameFileAlly) {
        return SCORE;
    }
}
