package cinema.domain.io;

import cinema.error.InvalidInputException;

public record SelectionCommand(int selectedNumber) {
    public SelectionCommand {
        if (selectedNumber <= 0) {
            throw new InvalidInputException();
        }
    }

    public int toIndex() {
        return selectedNumber - 1;
    }
}
