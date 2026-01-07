package cinema.domain.schedule.dto;

import java.time.LocalTime;
import java.util.List;

public record SchedulesOfMovieDto(
        String movieTitle,
        List<ScheduleDto> scheduleDtos
) {
    public record ScheduleDto(
            int theater,
            LocalTime startTime,
            LocalTime endTime,
            String timeSlotName,
            int remainedSeats) {

    }
}
