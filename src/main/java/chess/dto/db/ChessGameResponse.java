package chess.dto.db;

import static chess.dto.db.ChessStateMapper.mapToState;

import chess.domain.game.state.State;

public record ChessGameResponse(String name, State state) {

    public static ChessGameResponse of(final String name, final String state) {
        return new ChessGameResponse(name, mapToState(state));
    }
}
