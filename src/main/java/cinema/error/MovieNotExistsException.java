package cinema.error;

public class MovieNotExistsException extends  MovieException{
    public MovieNotExistsException() {
        super("존재하지 않는 영화입니다.");
    }
}
