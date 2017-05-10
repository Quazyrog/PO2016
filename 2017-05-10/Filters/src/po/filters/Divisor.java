package po.filters;

/**
 * Comparator filter class.
 *
 * It discards given number, if it is divisible by the number hold. In other case the number is passed further in chain.
 */
class Divisor extends SlaveDivisor {
    private boolean filled = false;

    public Divisor() {
        super(0, new DummyDivisor());
        DummyDivisor madDummy = new DummyDivisor();
        plug(madDummy);
        madDummy.prev = this;
    }

    @Override
    public void add(int num) {
        if (!filled) {
            content = num;
            filled = true;
        } else {
            super.add(num);
        }
    }
}

/**
 * SlaveComparator filter class for comparators, that need value in order to exist.
 */
class SlaveDivisor extends CircuitFilter {
    protected int content;

    SlaveDivisor(int number, IFilter next) {
        super(next);
        this.content = number;
    }

    @Override
    public void add(int num) {
        if (num % content != 0) {
            next().add(num);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(content);
    }
}

/**
 * You encountered Dummy.
 */
class DummyDivisor implements IFilter {
    SlaveDivisor prev = null;

    @Override
    public void add(int num) {
        SlaveDivisor c = new SlaveDivisor(num, this);
        if (prev != null) {
            prev.plug(c);
        }
        prev = c;
    }
}
