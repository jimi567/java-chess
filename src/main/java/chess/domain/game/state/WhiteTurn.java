package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.fixedmove.King;
import java.util.NoSuchElementException;

public class WhiteTurn extends Running {

    @Override
    public State move(final Board board, final Coordinate source, final Coordinate target) {
        validateCoordinate(source, target);
        validateSourcePiece(board.findByCoordinate(source));
        Piece captured = board.move(source, target);
        if (captured instanceof King) {
            return new End();
        }
        return new BlackTurn();
    }

    private void validateCoordinate(final Coordinate source, final Coordinate target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 위치로 이동할 수 없습니다.");
        }
    }

    private void validateSourcePiece(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new NoSuchElementException("보드에 움직일 대상 기물이 없습니다.");
        }

        if (sourcePiece.isNotSameTeam(Team.WHITE)) {
            throw new IllegalStateException("지금은 흰팀의 차례입니다.");
        }
    }
}
