package chess.domain.piece;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class InitialBlackPawn extends Pawn {
    public InitialBlackPawn() {
        super(Team.BLACK);
    }

    @Override
    Set<Entry<Integer, Integer>> straightWeights() {
        return Set.of(
                Map.entry(0, -1),
                Map.entry(0, -2)
        );
    }

    @Override
    Set<Entry<Integer, Integer>> diagonalWeights() {
        return Set.of(
                Map.entry(-1, -1),
                Map.entry(1, -1)
        );
    }
}
