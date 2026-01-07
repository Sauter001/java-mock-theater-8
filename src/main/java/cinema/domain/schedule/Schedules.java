package cinema.domain.schedule;

import java.util.Iterator;
import java.util.List;

public class Schedules implements Iterable<Schedule> {
    private final List<Schedule> schedules;

    public Schedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public Iterator<Schedule> iterator() {
        return this.schedules.iterator();
    }

    public Schedule findScheduleOfIndex(int index) {
        return this.schedules.get(index);
    }
}
