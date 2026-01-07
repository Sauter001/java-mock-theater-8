package cinema.domain.seat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatMap {
    private static final int STANDARD_SEAT = 64;
    private static final int PREMIUM_SEAT = 16;
    private static final int TOTAL_SEAT = STANDARD_SEAT + PREMIUM_SEAT;
    private final Map<SeatKey, List<Seat>> seatMap = new HashMap<>();

    public Seats findOccupiedSeats(SeatKey key) {
        return new Seats(this.seatMap.getOrDefault(key, List.of()));
    }

    public int findRemainedSeat(String movieCode, String scheduleCode) {
        SeatKey key = new SeatKey(movieCode, scheduleCode);
        return findRemainedSeat(key);
    }

    public int findRemainedSeat(SeatKey key) {
        if (!seatMap.containsKey(key)) {
            return TOTAL_SEAT;
        }
        return TOTAL_SEAT - seatMap.get(key).size();
    }

    public record SeatKey(String movieCode, String scheduleCode) {

    }
}
