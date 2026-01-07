package cinema.parser.domain;

@FunctionalInterface
public interface DomainParser<T> {
    T parse(String input);
}
