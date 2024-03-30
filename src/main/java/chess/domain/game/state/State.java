package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public interface State {
    boolean isRunning();

    State start();

    State end();

    State status();

    State move(final Board board, final Coordinate source, final Coordinate target);
}
