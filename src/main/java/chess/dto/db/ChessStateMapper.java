package chess.dto.db;

import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteTurn;
import java.util.HashMap;
import java.util.Map;

public class ChessStateMapper {

    private static final Map<String, State> STRING_STATE;

    static {
        STRING_STATE = new HashMap<>();
        STRING_STATE.put("black", BlackTurn.getInstance());
        STRING_STATE.put("white", WhiteTurn.getInstance());
    }

    public static State mapToState(final String state) {
        return STRING_STATE.get(state);
    }

    public static String mapToString(final State state) {
        if (state == BlackTurn.getInstance()) {
            return "black";
        }
        return "white";
    }
}
