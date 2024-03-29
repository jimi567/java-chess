package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public interface State {
    boolean isRunning();

    State move(final Coordinate source, final Coordinate target);

    Board board();
}
