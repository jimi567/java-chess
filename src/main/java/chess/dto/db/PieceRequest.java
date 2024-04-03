package chess.dto.db;

import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;

public record PieceRequest(String chess_game_name, String coordinate, String team, String type) {

    public static PieceRequest of(final String chess_game_name, Coordinate coordinate, Piece piece) {
        return new PieceRequest(
                chess_game_name,
                MovementMapper.mapToString(coordinate),
                TeamMapper.mapToString(piece.getTeam()),
                TypeMapper.mapToString(piece.getType())
        );
    }
}
