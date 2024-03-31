package chess.domain.game.state;

public abstract class Running implements State {
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
        throw new UnsupportedOperationException("실행상태에서는 지원히지 않는 명령어 입니다.");
    }

    @Override
    public State end() {
        return End.getInstance();
    }

    @Override
    public State status() {
        return this;
    }
}
