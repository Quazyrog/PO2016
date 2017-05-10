package po.filters;


/**
 * Comparator filter class.
 *
 * It compares given content with the hold one, takes greater and passes smaller further. This creates chain of
 * comparators holding input in descending order.
 *
 * In order to get easy-constructable chain of filters here we use following approach: the only exposed class is
 * Comparator, which is indeed 'Master Comparator': first filter in chain. It is responsible for creation of the
 * DummyComparator, which is plugged as next, after the Master. The rest of chain is built using SlaveComparator
 * instances (which is superclass of Comparator). The main difference between Comparator and slave Comparator is
 * that SlaveComparator needs to be initialised with content it holds, while Comparator can get such content later
 * using add method. In this way we achieve the first filter is always the one used to create chain: Comparator.
 */
class Comparator extends SlaveComparator {
    private boolean filled = false;

    public Comparator() {
        super(0, new DummyComparator());
        DummyComparator madDummy = new DummyComparator();
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
 *
 * It compares given content with the hold one, takes greater and passes smaller further.
 */
class SlaveComparator extends CircuitFilter {
    int content;

    SlaveComparator(int content, IFilter next) {
        super(next);
        this.content = content;
    }

    @Override
    public void add(int num) {
        if (content < num) {
            int oldContent = content; //In name of tail recursion?
            content = num;
            next().add(oldContent);
        } else {
            next().add(num);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(content);
    }
}

/**
 * Practice talking to the dummy.
 *
 * He will make chain of comparators for you.
 */
class DummyComparator implements IFilter {
    SlaveComparator prev;

    @Override
    public void add(int num) {
        SlaveComparator c = new SlaveComparator(num, this);
        if (prev != null) {
            prev.plug(c);
        }
        prev = c;
    }
}
