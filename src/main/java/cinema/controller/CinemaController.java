package cinema.controller;

import cinema.domain.io.Command;
import cinema.domain.io.movie.MovieSelectionCommand;
import cinema.domain.movie.Movie;
import cinema.domain.movie.Movies;
import cinema.domain.schedule.Schedule;
import cinema.domain.schedule.Schedules;
import cinema.error.MovieException;
import cinema.error.MovieNotExistsException;
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
        while (true) {
            Command command = inputView.readSelection();
            if (command == Command.EXIT) {
                break;
            }
            runMovieSystem(command);
        }
    }

    private void runMovieSystem(Command command) {
        if (command == Command.RESERVATION) {
            runReservation();
        }
    }

    private void runReservation() {
        retry(() -> {
            Movies movies = movieService.findAllMovies();
            outputView.displayMovies(movies.toMovieListDtos());
            MovieSelectionCommand selectionCommand = inputView.readMovieSelection();
            showScreenScheduleOfMovie(movies, selectionCommand);
        });
    }

    private void showScreenScheduleOfMovie(Movies movies, MovieSelectionCommand selectionCommand) {
        if (!isSelectedRight(movies, selectionCommand)) {
            throw new MovieNotExistsException();
        }
        Movie movie = movieService.findMovieOf(selectionCommand.selectedNumber());
        Schedules schedules = movieService.findScheduleOf(selectionCommand.selectedNumber());
        outputView.displaySchedules(movieService.convertToScheduleDisplayDto(schedules, movie.getTitle()));
    }

    private static boolean isSelectedRight(Movies movies, MovieSelectionCommand selectionCommand) {
        return movies.hasMovieOfCode(selectionCommand.selectedNumber());
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
