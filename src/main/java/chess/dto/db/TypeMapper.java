package chess.dto.db;

import chess.domain.piece.PieceType;
import java.util.HashMap;
import java.util.Map;

public class TypeMapper {
    private static final Map<String, PieceType> STRING_PIECE_TYPE;

    static {
        STRING_PIECE_TYPE = new HashMap<>();
        STRING_PIECE_TYPE.put("PAWN", PieceType.PAWN);
        STRING_PIECE_TYPE.put("BISHOP", PieceType.BISHOP);
        STRING_PIECE_TYPE.put("ROOK", PieceType.ROOK);
        STRING_PIECE_TYPE.put("QUEEN", PieceType.QUEEN);
        STRING_PIECE_TYPE.put("KING", PieceType.KING);
        STRING_PIECE_TYPE.put("KNIGHT", PieceType.KNIGHT);
        STRING_PIECE_TYPE.put("EMPTY", PieceType.EMPTY);
    }

    public static PieceType mapToPieceType(final String type) {
        return STRING_PIECE_TYPE.get(type);
    }

    public static String mapToString(final PieceType pieceType) {
        return pieceType.name();
    }
}
