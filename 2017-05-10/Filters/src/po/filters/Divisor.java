package po.filters;

import java.util.logging.Filter;

/**
 * Created by saphir on 10/05/17.
 */
public class Divisor extends CircuitFilter {
    int number;

    public Divisor(int number, IFilter next) {
        super(next);
        this.number = number;
    }

    @Override
    public IFilter add(int num) {
        if (num % number != 0) {
            next().add(num);
        }
        return this;
    }
}
