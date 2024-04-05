package chess.view;

import java.util.List;

public class RoomNameToken {
    private static final int MINIMUM_ROOM_NAME_SIZE = 1;
    private static final int MAXIMUM_ROOM_NAME_SIZE = 25;
    static final String EXIT = "exit";

    private final String token;

    public RoomNameToken(final String token) {
        validateRoomName(token);
        this.token = token;
    }

    private void validateRoomName(final String roomName) {
        if (roomName.length() < MINIMUM_ROOM_NAME_SIZE || roomName.length() > MAXIMUM_ROOM_NAME_SIZE) {
            throw new IllegalArgumentException("방 이름은 1글자, 25글자 사이로 입력해주세요.");
        }
    }

    public boolean isIn(final List<String> roomNameList) {
        return roomNameList.contains(token);
    }

    public boolean isNotIn(final List<String> roomNameList) {
        return !isIn(roomNameList);
    }

    public boolean isNotExit() {
        return !EXIT.equals(token);
    }

    public String value() {
        return token;
    }
}
