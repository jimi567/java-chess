package chess.db.dao;

import chess.db.DBConnection;
import chess.dto.db.MovementRequest;
import chess.dto.db.MovementResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovementDAO {

    public void addMovement(MovementRequest movementRequest) {
        String query = "INSERT INTO movement (chess_game_id, source_coordinate, target_coordinate) VALUES (?, ?, ?)";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, movementRequest.chess_game_id());
            preparedStatement.setString(2, movementRequest.sourceCoordinate());
            preparedStatement.setString(3, movementRequest.targetCoordinate());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MovementResponse> findAll() {
        String query = "SELECT * FROM movement";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<MovementResponse> movementResponses = new ArrayList<>();
            while (resultSet.next()) {
                movementResponses.add(MovementResponse.of(
                        resultSet.getLong("chess_game_id"),
                        resultSet.getString("source_coordinate"),
                        resultSet.getString("target_coordinate")
                ));

            }
            return movementResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        String query = "DELETE FROM movement";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
