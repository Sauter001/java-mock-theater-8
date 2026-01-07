package cinema.domain.schedule;

import cinema.domain.movie.Movie;
import cinema.domain.movie.dto.MovieListDto;
import cinema.domain.schedule.dto.SchedulesOfMovieDto;
import cinema.domain.seat.TimeSlot;

import java.time.LocalTime;

public class Schedule {
    private final String code;
    private final Movie movie;
    private final int theaterNum;
    private final LocalTime startTime;

    public Schedule(String code, Movie movie, int theaterNum, LocalTime startTime) {
        this.code = code;
        this.movie = movie;
        this.theaterNum = theaterNum;
        this.startTime = startTime;
    }

    public SchedulesOfMovieDto.ScheduleDto toScheduleDto(int remainedSeat) {
        MovieListDto movieListDto = movie.toMovieListDto();
        LocalTime endTime = this.startTime.plusMinutes(movieListDto.runningTime());
        return createScheduleDto(remainedSeat, endTime);
    }

    private SchedulesOfMovieDto.ScheduleDto createScheduleDto(int remainedSeat, LocalTime endTime) {
        return new SchedulesOfMovieDto.ScheduleDto(
                this.theaterNum,
                this.startTime,
                endTime,
                TimeSlot.getFromBoundary(this.startTime).getName(),
                remainedSeat);
    }

    public String getScheduleCode() {
        return code;
    }

    public String getMovieCode() {
        return this.movie.getCode();
    }
}
