package po.filters;

/**
 * Created by saphir on 10/05/17.
 */
public class DummyComparator implements IFilter {
    private Comparator prev = null;

    @Override
    public IFilter add(int num) {
        Comparator c = new Comparator(num, this);
        if (prev != null) {
            prev.plug(c);
        }
        prev = c;
        return prev;
    }
}
