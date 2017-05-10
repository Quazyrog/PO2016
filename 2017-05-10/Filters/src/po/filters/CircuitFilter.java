package po.filters;

/**
 * Created by saphir on 10/05/17.
 */
abstract class CircuitFilter implements IPrintable, IFilter {
    private IFilter next;

    CircuitFilter(IFilter next) {
        plug(next);
    }

    @Override
    public void print() {
        System.out.print("[" + this + "] ");
        if (next instanceof IPrintable) {
            ((IPrintable) next).print();
        }
    }

    void plug(IFilter next) {
        if (next == null) {
            throw new NullPointerException();
        }
        this.next = next;
    }

    protected IFilter next() {
        return next;
    }
}
