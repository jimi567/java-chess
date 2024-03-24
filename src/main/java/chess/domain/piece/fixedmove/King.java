package chess.domain.piece.fixedmove;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class King extends FixedMovePiece {

    public King(Team team) {
        super(PieceType.KING, team);
    }

    @Override
    Set<Entry<Integer, Integer>> weights() {
        return Set.of(
                Map.entry(-1, 1),
                Map.entry(-1, 0),
                Map.entry(-1, -1),
                Map.entry(1, 1),
                Map.entry(1, 0),
                Map.entry(1, -1),
                Map.entry(0, 1),
                Map.entry(0, -1)
        );
    }
}

