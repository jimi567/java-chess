package chess.view;

import static chess.view.RoomNameToken.EXIT;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public InputTokens readCommand() {
        String command = scanner.nextLine().trim();
        return new InputTokens(command);
    }

    public RoomNameToken readGameRoomName(final List<String> gameNames) {
        System.out.println("> 방 이름을 입력해주세요.");
        System.out.println("> 존재하지 않은 방을 입력하면, 새 게임이 시작 됩니다.");
        System.out.println(">" + EXIT + " 를 입력하면, 프로그램이 종료 됩니다. ");
        System.out.println("> 방 목록");
        for (int i = 0; i < gameNames.size(); i++) {
            System.out.println(i + 1 + " - " + gameNames.get(i));
        }
        String roomName = scanner.nextLine().trim();
        return new RoomNameToken(roomName);
    }
}
