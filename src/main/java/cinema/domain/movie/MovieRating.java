package cinema.domain.movie;

import cinema.error.InvalidInputException;

import java.util.stream.Stream;

public enum MovieRating {
    ALL("전체 관람가", 0),
    TWELVE("12세 이상", 12),
    FIFTEEN("15세 이상", 15),
    ADULT("청소년 관람불가", 19);

    private final String description;
    private final int minimumAge;

    MovieRating(String description, int minimumAge) {
        this.description = description;
        this.minimumAge = minimumAge;
    }

    public boolean canWatch(int age) {
        return age >= minimumAge;
    }

    public String getDescription() {
        return description;
    }

    public static MovieRating fromDescription(String description) {
        return Stream.of(values())
                .filter(r -> r.description.equals(description))
                .findFirst()
                .orElseThrow(InvalidInputException::new);
    }
}
