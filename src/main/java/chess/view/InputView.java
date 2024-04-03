package chess.view;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputView {

    public static final int MINIMUM_ROOM_NAME_SIZE = 1;
    public static final int MAXIMUM_ROOM_NAME_SIZE = 25;
    private final Scanner scanner = new Scanner(System.in);

    public InputTokens readCommand() {
        String command = scanner.nextLine().trim();
        return new InputTokens(command);
    }

    public String readGameRoomName(List<String> gameNames) {
        System.out.println("> 방 이름을 입력해주세요.");
        System.out.println("> 존재하지 않은 방을 입력하면, 새 게임이 시작 됩니다.");
        System.out.println("> 방 목록");
        for (int i = 0; i < gameNames.size(); i++) {
            System.out.println(i + 1 + " - " + gameNames.get(i));
        }
        String roomName = scanner.nextLine().trim();
        if (gameNames.contains(roomName)) {
            return roomName;
        }
        validateRoomName(roomName);
        throw new NoSuchElementException(roomName);
    }

    private void validateRoomName(final String roomName) {
        if (roomName.length() < MINIMUM_ROOM_NAME_SIZE || roomName.length() > MAXIMUM_ROOM_NAME_SIZE) {
            throw new IllegalArgumentException("방 이름은 1글자, 25글자 사이로 입력해주세요.");
        }
    }
}
