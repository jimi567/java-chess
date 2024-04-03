package chess.dto.db;

import static chess.dto.db.ChessStateMapper.mapToString;

import chess.domain.game.state.State;

public record ChessGameRequest(String name, String state) {

    public static ChessGameRequest of(final String name, State state) {
        return new ChessGameRequest(name, mapToString(state));
    }
}
