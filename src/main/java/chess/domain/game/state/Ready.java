package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class Ready implements State {
    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public State start() {
        return new WhiteTurn();
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public State move(final Board board, final Coordinate source, final Coordinate target) {
        throw new UnsupportedOperationException("준비상태에서는 지원히지 않는 명령어 입니다.");
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException("준비상태에서는 지원히지 않는 명령어 입니다.");
    }
}
