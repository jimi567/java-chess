package chess.dto.db;

import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

public class TeamMapper {

    private static final Map<String, Team> STRING_TEAM;

    static {
        STRING_TEAM = new HashMap<>();
        STRING_TEAM.put("BLACK", Team.BLACK);
        STRING_TEAM.put("WHITE", Team.WHITE);
        STRING_TEAM.put("EMPTY", Team.EMPTY);
    }

    public static Team mapToTeam(final String team) {
        return STRING_TEAM.get(team);
    }

    public static String mapToString(final Team team) {
        return team.name();
    }
}
