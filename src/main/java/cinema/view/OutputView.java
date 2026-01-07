package cinema.view;

import cinema.error.MovieException;

public class OutputView {
    public void printError(MovieException e) {
        System.out.println(e.getMessage());
    }
}