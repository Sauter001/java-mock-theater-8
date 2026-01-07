package cinema.domain.io.movie;

import cinema.error.InvalidInputException;

public record MovieSelectionCommand(int selectedNumber) {
    public MovieSelectionCommand {
        if (selectedNumber <= 0) {
            throw new InvalidInputException();
        }
    }
}
