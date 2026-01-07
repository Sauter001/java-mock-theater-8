package cinema.domain.movie;

import cinema.domain.movie.dto.MovieListDto;
import cinema.error.MovieException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {
    private static final Pattern codeNumberPattern = Pattern.compile("MV(\\d{3})");
    private static final String MOVIE_CODE_PREFIX = "MV";
    private final String code;
    private final String title;
    private final int runningTime;
    private final MovieRating rating;

    public Movie(String code, String title, int runningTime, MovieRating rating) {
        validateMovieCode(code);
        this.code = code;
        this.title = title;
        this.runningTime = runningTime;
        this.rating = rating;
    }

    private void validateMovieCode(String code) {
        Matcher matcher = codeNumberPattern.matcher(code);
        if (!matcher.matches()) {
            throw new MovieException("유효하지 않은 제목: MV + 숫자3개여야함");
        }
    }

    public MovieListDto toMovieListDto() {
        return new MovieListDto(getMovieNumber(), title, runningTime, rating.getDescription());
    }

    public int getMovieNumber() {
        return Integer.parseInt(code.substring(MOVIE_CODE_PREFIX.length()));
    }

    public boolean equalsCode(int numberCode) {
        return getMovieNumber() == numberCode;
    }

    public boolean equalsCode(String code) {
        return this.code.equals(code);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
