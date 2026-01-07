package cinema.domain.seat;

public class Seat {
    private final char row;        // A~H
    private final int column;      // 1~10
    private final SeatGrade grade;
    private boolean reserved;

    public Seat(char row, int column, SeatGrade grade) {
        this.row = row;
        this.column = column;
        this.grade = grade;
    }

    public String getPosition() {
        return String.valueOf(row) + column; // "A1", "G5" 등
    }

    // 추가 기능 구현
}
