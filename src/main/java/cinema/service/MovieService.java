package cinema.service;

import cinema.domain.movie.Movie;
import cinema.domain.movie.Movies;
import cinema.domain.schedule.Schedule;
import cinema.domain.schedule.Schedules;
import cinema.domain.schedule.dao.ScheduleDao;
import cinema.domain.schedule.dto.SchedulesOfMovieDto;
import cinema.domain.seat.SeatMap;
import cinema.error.MovieNotExistsException;
import cinema.repository.MovieRepository;
import cinema.repository.ScheduleRepository;
import cinema.util.MovieUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieService {
    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;
    private final SeatMap seatMap;

    public MovieService(MovieRepository movieRepository, ScheduleRepository scheduleRepository, SeatMap seatMap) {
        this.movieRepository = movieRepository;
        this.scheduleRepository = scheduleRepository;
        this.seatMap = seatMap;
    }


    private List<Schedule> convertToScheduleList(List<ScheduleDao> scheduleDaos) {
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleDao dao : scheduleDaos) {
            schedules.add(createSingleScheduleFrom(dao));
        }
        return schedules;
    }

    private Schedule createSingleScheduleFrom(ScheduleDao scheduleDao) {
        Optional<Movie> movieOptional = movieRepository.findWithCode(scheduleDao.movieCode());
        if (movieOptional.isEmpty()) {
            throw new MovieNotExistsException();
        }
        Movie movie = movieOptional.get();
        return new Schedule(scheduleDao.movieCode(), movie, scheduleDao.theaterNum(), scheduleDao.start());
    }

    public Movies findAllMovies() {
        return new Movies(this.movieRepository.findAll());
    }

    public Schedules findScheduleOf(int movieCodeNumber) {
        String movieCode = MovieUtil.createMovieId(movieCodeNumber);
        List<ScheduleDao> scheduleDaos = this.scheduleRepository.findSchedulesOfMovieCode(movieCode);
        return new Schedules(this.convertToScheduleList(scheduleDaos));
    }

    private List<ScheduleDao> findAllSchedule() {
        return this.scheduleRepository.findAll();
    }

    public SchedulesOfMovieDto convertToScheduleDisplayDto(Schedules schedules, String movieTitle) {
        List<SchedulesOfMovieDto.ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            int remainedSeat = seatMap.findRemainedSeat(schedule.getMovieCode(), schedule.getScheduleCode());
            scheduleDtos.add(schedule.toScheduleDto(remainedSeat));
        }
        return new SchedulesOfMovieDto(movieTitle, scheduleDtos);
    }

    public Movie findMovieOf(int movieId) {
        Optional<Movie> movieOptional = this.movieRepository.findWithCode(MovieUtil.createMovieId(movieId));
        if (movieOptional.isEmpty()) {
            throw new MovieNotExistsException();
        }
        return movieOptional.get();
    }
}
