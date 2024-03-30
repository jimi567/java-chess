package chess.view;

import java.util.Arrays;
import java.util.List;

public class InputTokens {

    private static final int COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int COMMAND_TOKEN_POSITION = 0;
    private static final int SOURCE_TOKEN_POSITION = 1;
    private static final int TARGET_TOKEN_POSITION = 2;

    private final List<String> tokens;

    public InputTokens(final String commandInput) {
        List<String> tokens = Arrays.stream(commandInput.split(" ")).toList();
        validateCommand(tokens);
        this.tokens = tokens;
    }

    private void validateCommand(final List<String> splitInput) {
        if (validateSize(splitInput) && validateMoveCommand(splitInput)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private boolean validateMoveCommand(final List<String> splitInput) {
        return splitInput.size() != MOVE_COMMAND_SIZE || !splitInput.get(COMMAND_TOKEN_POSITION).equals("move");
    }

    private boolean validateSize(final List<String> splitInput) {
        return splitInput.size() != COMMAND_SIZE;
    }

    public String getCommandToken() {
        return tokens.get(COMMAND_TOKEN_POSITION);
    }

    public String getSourceCoordinateToken() {
        return tokens.get(SOURCE_TOKEN_POSITION);
    }

    public String getTargetCoordinateToken() {
        return tokens.get(TARGET_TOKEN_POSITION);
    }
}
