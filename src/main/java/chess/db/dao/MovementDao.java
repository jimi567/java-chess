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
        String query = "INSERT INTO movement (chess_game_name, source_coordinate, target_coordinate) VALUES (?, ?, ?)";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movementRequest.chess_game_name());
            preparedStatement.setString(2, movementRequest.sourceCoordinate());
            preparedStatement.setString(3, movementRequest.targetCoordinate());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MovementResponse> findAllByGameName(final String gameName) {
        String query = "SELECT * FROM movement WHERE chess_game_name = ?";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<MovementResponse> movementResponses = new ArrayList<>();
            while (resultSet.next()) {
                movementResponses.add(MovementResponse.of(
                        resultSet.getString("chess_game_name"),
                        resultSet.getString("source_coordinate"),
                        resultSet.getString("target_coordinate")
                ));

            }
            return movementResponses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByGameName(final String gameName) {
        String query = "DELETE FROM movement WHERE chess_game_name = ?";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, gameName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
