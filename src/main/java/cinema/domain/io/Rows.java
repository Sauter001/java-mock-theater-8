package cinema.domain.io;

import java.util.Iterator;
import java.util.List;

public class Rows implements Iterable<Row> {
    private final List<Row> rows;

    public Rows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public Iterator<Row> iterator() {
        return this.rows.iterator();
    }
}
