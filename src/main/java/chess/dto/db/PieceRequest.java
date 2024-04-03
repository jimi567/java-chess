package chess.dto.db;

import chess.domain.board.Coordinate;
import chess.domain.piece.Piece;

public record PieceRequest(long chess_game_id, String coordinate, String team, String type) {

    public static PieceRequest of(long chess_game_id, Coordinate coordinate, Piece piece) {
        return new PieceRequest(
                chess_game_id,
                MovementMapper.mapToString(coordinate),
                TeamMapper.mapToString(piece.getTeam()),
                TypeMapper.mapToString(piece.getType())
        );
    }
}
