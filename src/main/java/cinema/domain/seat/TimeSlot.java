package cinema.domain.seat;

import cinema.error.MovieException;

import java.time.LocalTime;
import java.util.stream.Stream;

public enum TimeSlot {
    MORNING("조조",
            new TimeInterval(LocalTime.of(0, 0), LocalTime.of(9, 59)),
            2000),   // 10:00 이전
    REGULAR("일반",
            new TimeInterval(LocalTime.of(10, 0), LocalTime.of(21, 59)),
            0),
    LATE_NIGHT("심야",
            new TimeInterval(LocalTime.of(22, 0), LocalTime.of(23, 59)),
            2000); // 22:00 이후

    private final String name;
    private final TimeInterval interval; // 양 끝점 포함
    private final int discount;

    TimeSlot(String name, TimeInterval interval, int discount) {
        this.name = name;
        this.interval = interval;
        this.discount = discount;
    }

    public static TimeSlot getFromBoundary(LocalTime timeBoundary) {
        return Stream.of(values())
                .filter(t -> t.isInBoundary(timeBoundary))
                .findFirst()
                .orElseThrow(() -> new MovieException("존재하지 않는 시간대입니다."));
    }

    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }

    public boolean isInBoundary(LocalTime time) {
        TimeInterval interval = this.interval;
        return (interval.start().equals(time) || interval.start().isBefore(time))
                && (interval.end().equals(time) || interval.end().isAfter(time));
    }
}
