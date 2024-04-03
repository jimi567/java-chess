package chess.dto.db;

import static chess.dto.db.MovementMapper.mapToCoordinate;
import static chess.dto.db.TeamMapper.mapToTeam;
import static chess.dto.db.TypeMapper.mapToPieceType;

import chess.domain.board.Coordinate;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

public record PieceResponse(String chess_game_name, Coordinate coordinate, Team team, PieceType type) {

    public static PieceResponse of(final String chess_game_name, final String coordinate, final String team,
                                   final String type) {
        return new PieceResponse(chess_game_name, mapToCoordinate(coordinate), mapToTeam(team), mapToPieceType(type));
    }
}
