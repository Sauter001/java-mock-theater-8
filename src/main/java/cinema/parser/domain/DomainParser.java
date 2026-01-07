package cinema.parser.domain;

public interface DomainParser<T> {
    T parse(String input);
}
