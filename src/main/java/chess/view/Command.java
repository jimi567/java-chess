package chess.view;

import chess.dto.MovementRequest;
import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status"),

    ;
    private final String displayFormat;

    Command(final String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public static Command from(final InputTokens tokens) {
        return Arrays.stream(values())
                .filter(command -> command.displayFormat.equals(tokens.getCommandToken()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public MovementRequest movementRequest(final InputTokens tokens) {
        if (isNotMove()) {
            throw new UnsupportedOperationException("지원하지 않는 Command 기능입니다.");
        }
        return new MovementRequest(tokens.getSourceCoordinateToken(), tokens.getTargetCoordinateToken());
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    private boolean isNotMove() {
        return !isMove();
    }
}
