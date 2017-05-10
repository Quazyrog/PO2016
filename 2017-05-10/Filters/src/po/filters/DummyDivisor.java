package po.filters;

/**
* Created by saphir on 10/05/17.
*/
public class DummyDivisor implements IFilter {
    private Divisor prev = null;

    @Override
    public IFilter add(int num) {
        Divisor c = new Divisor(num, this);
        if (prev != null) {
            prev.plug(c);
        }
        prev = c;
        return prev;
    }
}
