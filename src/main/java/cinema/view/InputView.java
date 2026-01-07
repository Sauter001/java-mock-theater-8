package cinema.view;

import camp.nextstep.edu.missionutils.Console;
import cinema.domain.io.Command;
import cinema.domain.io.SelectionCommand;
import cinema.error.MovieException;
import cinema.parser.domain.DomainParser;
import cinema.parser.domain.SelectionCommandParser;

public class InputView {
    private static void displaySelectionGuide() {
        System.out.println("안녕하세요. 우아한 시네마입니다. \uD83C\uDFAC\n");
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 영화 예매");
        System.out.println("2. 예매 확인");
        System.out.println("3. 예매 취소");
        System.out.println("Q. 종료");
    }

    public Command readSelection() {
        displaySelectionGuide();
        return readWithRetry("\n", Command::from);
    }

    public SelectionCommand readMovieSelection() {
        String prompt = "예매할 영화 번호를 입력하세요.\n";
        return readSelection(prompt);
    }

    public SelectionCommand readScheduleSelection() {
        return readSelection("예매할 상영 번호를 입력하세요.\n");
    }

    private SelectionCommand readSelection(String  prompt) {
        return readWithRetry(prompt, new SelectionCommandParser());
    }

    private <T> T readWithRetry(String prompt, DomainParser<T> parser) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = Console.readLine();
                System.out.println();
                return parser.parse(input);
            } catch (MovieException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private <T> T read(String prompt, DomainParser<T> parser) {
        System.out.print(prompt);
        String input = Console.readLine();
        System.out.println();
        return parser.parse(input);
    }
}
