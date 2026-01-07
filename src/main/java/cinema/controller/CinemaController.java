package cinema.controller;

import cinema.error.MovieException;
import cinema.service.MovieService;
import cinema.view.InputView;
import cinema.view.OutputView;

import java.util.function.Supplier;

public class CinemaController {
    private final MovieService movieService;
    private final InputView inputView;
    private final OutputView outputView;

    public CinemaController(MovieService movieService, InputView inputView, OutputView outputView) {
        this.movieService = movieService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

    }

    private void retry(Runnable task) {
        while (true) {
            try {
                task.run();
                return;
            } catch (MovieException e) {
                outputView.printError(e);
            }
        }
    }

    private <T> T retry(Supplier<T> task) {
        while (true) {
            try {
                return task.get();
            } catch (MovieException e) {
                outputView.printError(e);
            }
        }
    }
}
