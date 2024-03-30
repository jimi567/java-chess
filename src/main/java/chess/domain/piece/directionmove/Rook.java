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

public class Rook extends DirectionMovePiece {
    public static final Piece WHITE_ROOK = new Rook(WHITE);
    public static final Piece BLACK_ROOK = new Rook(BLACK);
    private static final BigDecimal SCORE = new BigDecimal(5);

    private Rook(Team team) {
        super(PieceType.ROOK, team);
    }

    @Override
    Set<Direction> legalDirections() {
        return Set.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        );
    }

    @Override
    public BigDecimal score(final List<Piece> sameFilePieces) {
        return SCORE;
    }
}
