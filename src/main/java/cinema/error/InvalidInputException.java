package cinema.error;

public class InvalidInputException extends MovieException {
    public InvalidInputException() {
        super("유효하지 않은 입력입니다.");
    }
}
