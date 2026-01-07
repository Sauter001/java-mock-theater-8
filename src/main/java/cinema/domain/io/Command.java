package cinema.domain.io;

import cinema.error.InvalidInputException;

import java.util.stream.Stream;

public enum Command {
    RESERVATION("1"),
    CONFIRM("2"),
    CANCEL("3"),
    EXIT("Q");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public static Command from(String input) {
        String processedInput = input.strip().toUpperCase();
        return Stream.of(values())
                .filter(c -> c.input.equals(processedInput))
                .findFirst()
                .orElseThrow(InvalidInputException::new);
    }
}
