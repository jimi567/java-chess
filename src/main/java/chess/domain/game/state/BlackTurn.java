package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.NoSuchElementException;

public class BlackTurn extends Running {

    private static final State INSTANCE = new BlackTurn();

    private BlackTurn() {
    }

    public static State getInstance() {
        return INSTANCE;
    }

    @Override
    public State move(final Board board, final Coordinate source, final Coordinate target) {
        validateSourcePiece(board.findByCoordinate(source));
        Piece captured = board.move(source, target);
        if (captured.isSameType(PieceType.KING)) {
            return GameOver.getInstance();
        }
        return WhiteTurn.getInstance();
    }

    private void validateSourcePiece(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }

        if (sourcePiece.isNotSameTeam(Team.BLACK)) {
            throw new IllegalStateException("지금은 흑팀의 차례입니다.");
        }
    }
}
