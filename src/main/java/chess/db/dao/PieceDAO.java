package chess.db.dao;

import chess.db.DBConnection;
import chess.dto.db.PieceRequest;
import chess.dto.db.PieceResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDAO {
    public void addAll(List<PieceRequest> pieceRequests) {
        String query = "INSERT INTO piece (chess_game_name, coordinate, team, type) VALUES (?,?,?,?)";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            for (PieceRequest pieceRequest : pieceRequests) {
                preparedStatement.setString(1, pieceRequest.chess_game_name());
                preparedStatement.setString(2, pieceRequest.coordinate());
                preparedStatement.setString(3, pieceRequest.team());
                preparedStatement.setString(4, pieceRequest.type());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PieceResponse> findAllByGameName(final String gameName) {
        String query = "SELECT * FROM piece WHERE chess_game_name = ?";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PieceResponse> pieceResponses = new ArrayList<>();
            while (resultSet.next()) {
                pieceResponses.add(PieceResponse.of(
                        resultSet.getString("chess_game_chess"),
                        resultSet.getString("coordinate"),
                        resultSet.getString("team"),
                        resultSet.getString("type")
                ));

            }
            return pieceResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
