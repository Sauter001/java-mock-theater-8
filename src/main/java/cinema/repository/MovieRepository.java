package cinema.repository;

import cinema.constant.IOConstant;
import cinema.domain.io.Row;
import cinema.domain.io.Rows;
import cinema.domain.movie.Movie;
import cinema.domain.movie.Movies;
import cinema.error.MovieException;
import cinema.parser.row.MovieRowParser;
import cinema.parser.row.RowParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class MovieRepository {
    public static final String DELIMITER = ",";
    private final RowParser<List<Movie>> movieRowParser;
    private final List<Movie> movies;

    public MovieRepository() {
        this.movieRowParser = new MovieRowParser();
        this.movies = readRows();
    }

    private List<Movie> readRows() {
        try (InputStream is = getClass().getResourceAsStream(IOConstant.MOVIE_PATH);
             InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(is));
             BufferedReader reader = new BufferedReader(isr);
        ) {
            Rows rows = createRows(reader);
            return movieRowParser.parse(rows);
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

    public Optional<Movie> findWithCode(String movieCode) {
        return this.movies.stream()
                .filter(m -> m.equalsCode(movieCode))
                .findFirst();
    }

    public List<Movie> findAll() {
        return this.movies;
    }
}
