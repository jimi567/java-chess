package chess.dto.db;

import static chess.dto.db.MovementMapper.mapToCoordinate;
import static chess.dto.db.TeamMapper.mapToTeam;
import static chess.dto.db.TypeMapper.mapToPieceType;

import chess.domain.board.Coordinate;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

public record PieceResponse(long id, Coordinate coordinate, Team team, PieceType type) {

    public static PieceResponse of(long id, String coordinate, String team, String type) {
        return new PieceResponse(id, mapToCoordinate(coordinate), mapToTeam(team), mapToPieceType(type));
    }
}
