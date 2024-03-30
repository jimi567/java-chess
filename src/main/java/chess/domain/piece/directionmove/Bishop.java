package chess.domain.piece.directionmove;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.board.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Bishop extends DirectionMovePiece {
    public static final Piece WHITE_BISHOP = new Bishop(WHITE);
    public static final Piece BLACK_BISHOP = new Bishop(BLACK);
    private static final BigDecimal SCORE = new BigDecimal(3);

    private Bishop(Team team) {
        super(PieceType.BISHOP, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Set.of(
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_DOWN,
                Direction.RIGHT_UP
        );
    }

    @Override
    public BigDecimal score(final List<Piece> sameFilePieces) {
        return SCORE;
    }
}
