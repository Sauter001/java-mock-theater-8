package cinema.parser.domain;

import cinema.domain.io.SelectionCommand;
import cinema.error.InvalidInputException;

public class SelectionCommandParser implements DomainParser<SelectionCommand> {
    @Override
    public SelectionCommand parse(String input) {
        try {
            return new SelectionCommand(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
