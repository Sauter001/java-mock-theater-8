package cinema.domain.movie;

public enum TimeSlot {
    MORNING("조조", 2000),   // 10:00 이전
    REGULAR("일반", 0),
    LATE_NIGHT("심야", 2000); // 22:00 이후

    private final String name;
    private final int discount;

    TimeSlot(String name, int discount) {
        this.name = name;
        this.discount = discount;
    }


    public String getName() {
        return name;
    }

    public int getDiscount() {
        return discount;
    }
}
