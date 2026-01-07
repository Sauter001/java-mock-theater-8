package cinema.view;

import camp.nextstep.edu.missionutils.Console;
import cinema.error.MovieException;
import cinema.parser.domain.DomainParser;

public class InputView {
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
