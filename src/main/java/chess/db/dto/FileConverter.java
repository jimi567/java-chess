package chess.db.dto;

import chess.domain.board.File;
import java.util.Arrays;

public enum FileConverter {
    a(File.A),
    b(File.B),
    c(File.C),
    d(File.D),
    e(File.E),
    f(File.F),
    g(File.G),
    h(File.H),

    ;
    private final File file;

    FileConverter(final File file) {
        this.file = file;
    }

    public static String convert(final File file) {
        return Arrays.stream(FileConverter.values())
                .filter(fileConverter -> fileConverter.file == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열 값 입니다."))
                .name();
    }
}
