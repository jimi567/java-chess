package chess.domain.board;

import static chess.domain.board.BoardFactory.initialEmpty;

import chess.domain.piece.DummyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Coordinate, Piece> pieces;

    public Board(final Map<Coordinate, Piece> pieces) {
        this.pieces = initialEmpty();
        this.pieces.putAll(pieces);
    }

    public Board() {
        pieces = BoardFactory.createInitialPieces();
    }

    public Piece move(final Coordinate source, final Coordinate target) {
        Piece sourcePiece = findByCoordinate(source);
        List<Coordinate> coordinates = sourcePiece.legalNextCoordinates(source, target);
        if (sourcePiece.canMove(source, target, makeBoardInformation(coordinates))) {
            Piece targetPiece = findByCoordinate(target);
            pieces.put(target, sourcePiece.updateAfterMove());
            pieces.put(source, DummyPiece.getInstance());
            return targetPiece;
        }
        throw new IllegalStateException("해당 기물은 목적지 좌표에 갈 수 없습니다.");
    }

    public Piece findByCoordinate(final Coordinate coordinate) {
        return pieces.get(coordinate);
    }

    private Map<Coordinate, Piece> makeBoardInformation(final List<Coordinate> coordinates) {
        Map<Coordinate, Piece> boardInformation = new LinkedHashMap<>();
        coordinates.forEach(coordinate -> boardInformation.put(coordinate, findByCoordinate(coordinate)));
        return boardInformation;
    }

    public BigDecimal totalScoreByTeam(final Team team) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .map(entry -> entry.getValue().score(findSameFileAlly(team, entry.getKey().file())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Piece> findSameFileAlly(final Team team, final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> findByCoordinate(Coordinate.of(file, rank)))
                .filter(piece -> piece.isSameTeam(team))
                .toList();
    }

}
