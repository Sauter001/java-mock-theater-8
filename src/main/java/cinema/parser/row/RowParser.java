package cinema.parser.row;

import cinema.domain.io.Row;
import cinema.domain.io.Rows;

import java.util.List;

@FunctionalInterface
public interface RowParser<T> {
    T parse(Rows rows);
}
