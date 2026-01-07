package cinema.parser.domain;

import cinema.domain.io.movie.MovieSelectionCommand;
import cinema.error.InvalidInputException;

public class MovieSelectionCommandParser implements DomainParser<MovieSelectionCommand> {
    @Override
    public MovieSelectionCommand parse(String input) {
        try {
            return new MovieSelectionCommand(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        }
    }
}
