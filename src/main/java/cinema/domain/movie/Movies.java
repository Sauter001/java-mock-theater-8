package cinema.domain.movie;

import cinema.domain.movie.dto.MovieListDto;

import java.util.List;

public class Movies {
    private final List<Movie> movies;

    public Movies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<MovieListDto> toMovieListDtos() {
        return this.movies.stream()
                .map(Movie::toMovieListDto)
                .toList();
    }

    public boolean hasMovieOfCode(int code) {
        return this.movies.stream().anyMatch(m -> m.equalsCode(code));
    }
}
