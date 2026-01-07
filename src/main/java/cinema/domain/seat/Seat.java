package cinema.domain.seat;

public class Seat {
    private final char row;        // A~H
    private final int column;      // 1~10
    private final SeatGrade grade;
    private boolean reserved;

    private Seat(char row, int column, SeatGrade grade) {
        this.row = row;
        this.column = column;
        this.grade = grade;
    }

    public static Seat positionOf(char row, int column) {
        SeatGrade seatGrade = getGradeFromRow(row);
        return new Seat(row, column, seatGrade);
    }

    private static SeatGrade getGradeFromRow(char row) {
        if (isPremiumRow(row)) {
            return SeatGrade.PREMIUM;
        }
        return SeatGrade.STANDARD;
    }

    private static boolean isPremiumRow(char row) {
        return row == 'G' || row == 'H';
    }

    public String getPosition() {
        return String.valueOf(row) + column; // "A1", "G5" 등
    }

    // 추가 기능 구현
}
