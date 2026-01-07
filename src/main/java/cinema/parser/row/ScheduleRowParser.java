package cinema.parser.row;

import cinema.domain.io.Row;
import cinema.domain.io.Rows;
import cinema.domain.movie.dao.ScheduleDao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRowParser implements RowParser<List<ScheduleDao>> {
    @Override
    public List<ScheduleDao> parse(Rows rows) {
        List<ScheduleDao> schedules = new ArrayList<>();
        for (Row row : rows) {
            schedules.add(convertToSchedule(row));
        }
        return schedules;
    }

    private ScheduleDao convertToSchedule(Row row) {
        String code = row.getValue(Column.CODE.order);
        String movieCode = row.getValue(Column.MOVIE_CODE.order);
        int theater = Integer.parseInt(row.getValue(Column.THEATER.order));
        LocalTime time = createStartTime(row.getValue(Column.START_TIME.order));
        return new ScheduleDao(code, movieCode, theater, time);
    }

    private LocalTime createStartTime(String timeExpression) {
        return LocalTime.parse(timeExpression);
    }

    private enum Column {
        CODE(0), MOVIE_CODE(1), THEATER(2), START_TIME(3);
        private final int order;

        Column(int order) {
            this.order = order;
        }
    }
}
