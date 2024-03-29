package chess.domain.game;

import chess.domain.board.Board;

public abstract class Running extends ChessGame {
    protected Running(final Board board) {
        super(board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
