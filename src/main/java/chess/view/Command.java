package chess.view;

import chess.domain.board.Coordinate;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.game.Movement;
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

    public Movement movement(final InputTokens tokens) {
        if (isNotMove()) {
            throw new UnsupportedOperationException("지원하지 않는 Command 기능입니다.");
        }
        return new Movement(toCoordinate(tokens.getSourceCoordinateToken()),
                toCoordinate(tokens.getTargetCoordinateToken()));
    }

    private static Coordinate toCoordinate(final String coordinate) {
        return Coordinate.of(File.from(coordinate.charAt(0)), Rank.from(coordinate.charAt(1) - '0'));
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
