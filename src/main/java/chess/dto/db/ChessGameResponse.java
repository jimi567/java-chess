package chess.dto.db;

import static chess.dto.db.ChessStateMapper.mapToState;

import chess.domain.game.state.State;

public record ChessGameResponse(long id, String name, State state) {

    public static ChessGameResponse of(final long id, final String name, final String state) {
        return new ChessGameResponse(id, name, mapToState(state));
    }
}
