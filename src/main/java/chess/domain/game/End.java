package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class End implements State {

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public State move(final Coordinate source, final Coordinate target) {
        return null;
    }

    @Override
    public Board board() {
        return null;
    }

}
