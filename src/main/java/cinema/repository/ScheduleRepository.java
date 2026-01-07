package cinema.repository;

import cinema.constant.IOConstant;
import cinema.domain.io.Row;
import cinema.domain.io.Rows;
import cinema.domain.schedule.dao.ScheduleDao;
import cinema.error.MovieException;
import cinema.parser.row.RowParser;
import cinema.parser.row.ScheduleRowParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ScheduleRepository {
    private static final String DELIMITER = ",";
    private final RowParser<List<ScheduleDao>> rowParser;
    private final List<ScheduleDao> schedules;

    public ScheduleRepository() {
        this.rowParser = new ScheduleRowParser();
        this.schedules = readSchedules();
    }

    private List<ScheduleDao> readSchedules() {
        try (InputStream is = getClass().getResourceAsStream(IOConstant.SCHEDULE_PATH);
             InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(is));
             BufferedReader reader = new BufferedReader(isr);
        ) {
            Rows rows = createRows(reader);
            return rowParser.parse(rows);
        } catch (IOException e) {
            throw new MovieException("파일 읽기 실패");
        }
    }

    private Rows createRows(BufferedReader reader) throws IOException {
        List<Row> rowList = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            List<String> tokens = Stream.of(line.split(DELIMITER))
                    .map(String::strip)
                    .toList();
            rowList.add(new Row(tokens));
        }
        return new Rows(rowList);
    }

    public List<ScheduleDao> findAll() {
        return List.copyOf(this.schedules);
    }

    public List<ScheduleDao> findSchedulesOfMovieCode(String movieCode) {
        return this.schedules.stream()
                .filter(s -> Objects.equals(s.movieCode(), movieCode))
                .toList();
    }
}
