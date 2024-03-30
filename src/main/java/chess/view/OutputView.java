package chess.view;

import chess.domain.board.Board;
import chess.domain.game.ChessStatus;
import java.math.BigDecimal;
import java.util.List;

public class OutputView {

    private final BoardConverter boardConverter = new BoardConverter();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Board board) {
        boardConverter.convertToViewData(board)
                .forEach(this::printRow);
        System.out.println();
    }

    private void printRow(List<String> rowData) {
        StringBuilder stringBuilder = new StringBuilder();
        rowData.forEach(stringBuilder::append);
        System.out.println(stringBuilder);
    }

    public void printStatus(ChessStatus status) {
        BigDecimal blackScore = status.blackScore();
        BigDecimal whitScore = status.whiteScore();
        System.out.println("현재 점수는 흑팀 : " + blackScore + "백팀 : " + whitScore);
        if (blackScore.compareTo(whitScore) > 0) {
            System.out.println("흑이 이기는 중입니다.");
            return;
        }
        if (blackScore.compareTo(whitScore) < 0) {
            System.out.println("백이 이기는 중입니다.");
            return;
        }
        System.out.println("무승부 상황입니다.");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
