package cinema.domain.seat;

import cinema.error.MovieException;

import java.time.LocalTime;

public record TimeInterval(LocalTime start, LocalTime end) {
    public TimeInterval {
        if (!start.isBefore(end)) {
            throw new MovieException("시작 시간은 종료 시간보다 뒤여야 합니다.");
        }
    }
}
