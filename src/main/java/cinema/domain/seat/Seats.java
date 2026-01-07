package cinema.domain.seat;

import java.util.Iterator;
import java.util.List;

public class Seats implements Iterable<Seat>{
    private final List<Seat> seats;

    public Seats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public Iterator<Seat> iterator() {
        return seats.iterator();
    }
}
