package cinema;

import cinema.controller.CinemaController;
import cinema.domain.seat.SeatMap;
import cinema.repository.MovieRepository;
import cinema.repository.ScheduleRepository;
import cinema.service.MovieService;
import cinema.view.InputView;
import cinema.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        MovieService movieService = createMovieService();
        CinemaController cinemaController = new CinemaController(movieService, inputView, outputView);
        cinemaController.run();
    }

    private static MovieService createMovieService() {
        MovieRepository movieRepository = new MovieRepository();
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        SeatMap seatMap = new SeatMap();
        return new MovieService(movieRepository, scheduleRepository, seatMap);
    }
}
