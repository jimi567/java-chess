package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.Ready;
import chess.domain.game.state.State;
import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private final Board board;
    private State state;

    public ChessGame() {
        this.board = new Board();
        state = Ready.getInstance();
    }

    public void start(final List<Movement> movements) {
        state = state.start();
        for (Movement movement : movements) {
            this.move(movement);
        }
    }

    public void move(final Movement movement) {
        state = state.move(board, movement.source(), movement.target());
    }


    public ChessStatus status() {
        state = state.status();
        Map<Team, BigDecimal> scores = new HashMap<>();
        scores.put(Team.BLACK, board.totalScoreByTeam(Team.BLACK));
        scores.put(Team.WHITE, board.totalScoreByTeam(Team.WHITE));
        return new ChessStatus(scores);
    }

    public void end() {
        state = state.end();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isGameOver() {
        return state.isGameOver();
    }

    public Board board() {
        return board;
    }
}
