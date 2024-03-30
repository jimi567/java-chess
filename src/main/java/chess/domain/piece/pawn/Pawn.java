package chess.domain.piece.pawn;

import chess.domain.board.Coordinate;
import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pawn extends AbstractPiece {

    private static final BigDecimal DUPLICATE_SAME_FILE_SCORE = new BigDecimal("0.5");
    private static final BigDecimal NORMAL_SCORE = BigDecimal.ONE;

    Pawn(final Team team) {
        super(PieceType.PAWN, team);
    }

    abstract Set<Entry<Integer, Integer>> straightWeights();

    abstract Set<Entry<Integer, Integer>> diagonalWeights();

    @Override
    public List<Coordinate> legalNextCoordinates(final Coordinate now, final Coordinate destination) {
        List<Coordinate> legalNextCoordinates = new ArrayList<>();
        legalNextCoordinates.addAll(straightLegalNextCoordinates(now));
        legalNextCoordinates.addAll(diagonalLegalNextCoordinates(now));
        if (legalNextCoordinates.contains(destination)) {
            return legalNextCoordinates;
        }
        throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    private Set<Coordinate> straightLegalNextCoordinates(final Coordinate now) {
        LinkedHashSet<Coordinate> legalCoordinates = new LinkedHashSet<>();
        straightWeights().forEach(entry -> {
            if (now.canMove(entry.getKey(), entry.getValue())) {
                legalCoordinates.add(now.move(entry.getKey(), entry.getValue()));
            }
        });
        return legalCoordinates;
    }

    private Set<Coordinate> diagonalLegalNextCoordinates(final Coordinate now) {
        return diagonalWeights().stream()
                .filter(entry -> now.canMove(entry.getKey(), entry.getValue()))
                .map(entry -> now.move(entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean canMove(final Coordinate now, final Coordinate destination,
                           final Map<Coordinate, Piece> boardInformation) {

        Set<Coordinate> movableCoordinates = movableStraight(now, boardInformation);
        movableCoordinates.addAll(movableDiagonal(now, boardInformation));
        return movableCoordinates.contains(destination);
    }

    private Set<Coordinate> movableStraight(final Coordinate now, final Map<Coordinate, Piece> boardInformation) {
        return straightLegalNextCoordinates(now).stream()
                .takeWhile(coordinate -> boardInformation.get(coordinate).isEmpty())
                .collect(Collectors.toSet());
    }

    private Set<Coordinate> movableDiagonal(final Coordinate now, final Map<Coordinate, Piece> boardInformation) {
        return diagonalLegalNextCoordinates(now).stream()
                .filter(coordinate -> boardInformation.get(coordinate).isNotEmpty() && boardInformation.get(coordinate)
                        .isNotSameTeam(this))
                .collect(Collectors.toSet());
    }

    @Override
    public BigDecimal score(final List<Piece> sameFileAlly) {
        return sameFileAlly.stream()
                .filter(piece -> countPawn(sameFileAlly) > 1)
                .map(piece -> DUPLICATE_SAME_FILE_SCORE)
                .findFirst()
                .orElse(NORMAL_SCORE);
    }

    private int countPawn(final List<Piece> sameFilePieces) {
        return (int) sameFilePieces.stream()
                .filter(piece -> getType() == piece.getType())
                .count();
    }
}
