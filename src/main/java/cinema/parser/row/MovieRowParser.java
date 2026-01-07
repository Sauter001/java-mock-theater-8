package cinema.parser.row;

import cinema.domain.io.Row;
import cinema.domain.io.Rows;
import cinema.domain.movie.Movie;
import cinema.domain.movie.MovieRating;

import java.util.ArrayList;
import java.util.List;

public class MovieRowParser implements RowParser<List<Movie>> {
    @Override
    public List<Movie> parse(Rows rows) {
        List<Movie> movies = new ArrayList<>();
        for (Row row : rows) {
            movies.add(convertToMovie(row));
        }
        return movies;
    }

    private Movie convertToMovie(Row row) {
        String code = row.getValue(Column.CODE.order);
        String title = row.getValue(Column.TITLE.order);
        int runningTime = Integer.parseInt(row.getValue(Column.RUNNING_TIME.order));
        MovieRating rating = MovieRating.fromDescription(row.getValue(Column.RATING.order));
        return new Movie(code, title, runningTime, rating);
    }

    private enum Column {
        CODE(0), TITLE(1), RUNNING_TIME(2), RATING(3);
        private final int order;

        Column(int order) {
            this.order = order;
        }
    }
}
