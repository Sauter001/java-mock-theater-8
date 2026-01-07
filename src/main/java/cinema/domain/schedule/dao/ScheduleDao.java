package cinema.domain.schedule.dao;

import java.time.LocalTime;

public record ScheduleDao(
        String code,
        String movieCode,
        int theaterNum,
        LocalTime start) {
}
