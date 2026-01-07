package cinema.view;

import cinema.domain.movie.dto.MovieListDto;
import cinema.domain.schedule.dto.SchedulesOfMovieDto;
import cinema.error.MovieException;

import java.util.List;

public class OutputView {
    public void printError(MovieException e) {
        System.out.println(e.getMessage());
    }

    public void displayMovies(List<MovieListDto> movieListDtos) {
        System.out.println("[영화 목록]");
        System.out.println();
        System.out.printf("%3s %-20s %-15s %-10s\n", "No", "제목", "상영시간", "등급");
        displayGuideline();
        for (MovieListDto dto : movieListDtos) {
            displaySingleMovie(dto);
        }
        displayGuideline();
        System.out.println();
    }

    private void displaySingleMovie(MovieListDto dto) {
        System.out.printf("%3d %-20s %-15s %-10s\n", dto.number(), dto.title(), dto.runningTime() + "분", dto.rating());
    }

    private void displayGuideline() {
        System.out.println("===========================================================");
    }

    public void displaySchedules(SchedulesOfMovieDto schedulesOfMovieDtos) {
        System.out.printf("[%s 상영 시간표]\n", schedulesOfMovieDtos.movieTitle());
        System.out.println();
        System.out.printf("%-3s %-4s %-6s %-6s %-3s %-6s\n", "No", "상영관", "시작시간", "종료시간", "시간대", "잔여좌석");
        displayGuideline();
        int num = 1;
        for (SchedulesOfMovieDto.ScheduleDto scheduleDto : schedulesOfMovieDtos.scheduleDtos()) {
            displaySingleMovieSchedule(scheduleDto, num);
            num++;
        }
        displayGuideline();
        System.out.println();
    }

    private void displaySingleMovieSchedule(SchedulesOfMovieDto.ScheduleDto scheduleDto, int num) {
        System.out.printf("%-3d %-4s %-8s %-8s %-6s %-6s\n",
                num,
                scheduleDto.theater() + "관",
                scheduleDto.startTime(),
                scheduleDto.endTime(),
                scheduleDto.timeSlotName(),
                scheduleDto.remainedSeats() + "석");
    }
}