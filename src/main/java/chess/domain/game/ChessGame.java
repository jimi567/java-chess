package chess.domain.game;

import chess.domain.board.Board;

public abstract class ChessGame implements State {

    protected final Board board;

    protected ChessGame(final Board board) {
        this.board = board;
    }

    @Override
    public Board board() {
        return board;
    }
}
