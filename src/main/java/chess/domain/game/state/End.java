package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class End implements State {
    
    private static final State INSTANCE = new End();

    private End() {
    }

    public static State getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("종료상태에서는 지원히지 않는 명령어 입니다.");
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException("종료상태에서는 지원히지 않는 명령어 입니다.");
    }

    @Override
    public State move(final Board board, final Coordinate source, final Coordinate target) {
        throw new UnsupportedOperationException("종료상태에서는 지원히지 않는 명령어 입니다.");
    }

    @Override
    public State status() {
        throw new UnsupportedOperationException("종료상태에서는 지원히지 않는 명령어 입니다.");
    }

}
