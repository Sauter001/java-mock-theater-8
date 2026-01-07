package cinema.service;

import cinema.repository.MovieRepository;
import cinema.repository.ScheduleRepository;

public class MovieService {
    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;

    public MovieService(MovieRepository movieRepository, ScheduleRepository scheduleRepository) {
        this.movieRepository = movieRepository;
        this.scheduleRepository = scheduleRepository;
    }

}
