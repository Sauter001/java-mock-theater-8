package cinema.domain.movie;

public enum AudienceType {
    ADULT("성인", 0),
    TEENAGER("청소년", 20),    // 만 13~18세
    SENIOR("경로", 30),        // 만 65세 이상
    DISABLED("장애인", 50);

    private final String name;
    private final int discountPercent;

    AudienceType(String name, int discountPercent) {
        this.name = name;
        this.discountPercent = discountPercent;
    }

    // 추가 기능 구현
}
