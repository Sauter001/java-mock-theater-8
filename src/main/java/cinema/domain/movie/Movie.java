package cinema.domain.movie;

public class Movie {
    private final String code;
    private final String title;
    private final int runningTime;
    private final MovieRating rating;

    public Movie(String code, String title, int runningTime, MovieRating rating) {
        this.code = code;
        this.title = title;
        this.runningTime = runningTime;
        this.rating = rating;
    }
}
