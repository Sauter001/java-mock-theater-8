package cinema.domain.seat;

public enum SeatGrade {
    STANDARD("일반", 0),
    PREMIUM("프리미엄", 2000);

    private final String name;
    private final int additionalPrice;

    SeatGrade(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public String getName() {
        return name;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }

    // 추가 기능 구현
}
