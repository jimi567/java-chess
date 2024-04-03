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
        String query = "INSERT INTO piece (chess_game_id, coordinate, team, type) VALUES (?,?,?,?)";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            for (PieceRequest pieceRequest : pieceRequests) {
                preparedStatement.setLong(1, pieceRequest.chess_game_id());
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

    public List<PieceResponse> findAll() {
        String query = "SELECT * FROM piece";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PieceResponse> pieceResponses = new ArrayList<>();
            while (resultSet.next()) {
                pieceResponses.add(PieceResponse.of(
                        resultSet.getLong("chess_game_id"),
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

    public void clear() {
        String query = "DELETE FROM piece";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
