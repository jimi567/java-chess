package chess.dto.db;

import static chess.dto.db.ChessStateMapper.mapToString;

import chess.domain.game.state.State;

public record ChessGameRequest(long id, String name, String state) {

    public static ChessGameRequest of(final long id, final String name, State state) {
        return new ChessGameRequest(id, name, mapToString(state));
    }
}
