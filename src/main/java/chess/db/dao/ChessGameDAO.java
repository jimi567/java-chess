package chess.db.dao;

import chess.db.DBConnection;
import chess.dto.db.ChessGameRequest;
import chess.dto.db.ChessGameResponse;
import chess.exception.DataAccessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessGameDAO {

    public void addGame(ChessGameRequest chessGameRequest) {
        String query = "INSERT INTO chess_game (name, state) VALUES (?, ?)";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGameRequest.name());
            preparedStatement.setString(2, chessGameRequest.state());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public List<ChessGameResponse> findAll() {
        String query = "SELECT * FROM chess_game";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ChessGameResponse> chessGameResponses = new ArrayList<>();
            while (resultSet.next()) {
                chessGameResponses.add(ChessGameResponse.of(
                        resultSet.getString("name"),
                        resultSet.getString("state")
                ));
            }
            return chessGameResponses;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public ChessGameResponse findByName(final String name) {
        String query = "SELECT * FROM chess_game WHERE name = ?";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ChessGameResponse.of(
                        resultSet.getString("name"),
                        resultSet.getString("state")
                );
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return null;
    }

    public void deleteOne(final ChessGameRequest chessGameRequest) {
        String query = "DELETE FROM chess_game WHERE name = ?";

        try (var connection = DBConnection.getConnection();
             var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, chessGameRequest.name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }
}
