package po.filters;

public class Main {

    public static void main(String[] args) {
        IGenerator rand = new Random();
        IFilter sorter = (new DummyComparator()).add(rand.next());
        for (int i = 0; i < 10; ++i) {
            sorter.add(rand.next());
        }
        ((IPrintable) sorter).print();
        System.out.println();

        IGenerator nn = new Natural();
        nn.next();
        nn.next();
        IFilter primes = (new DummyDivisor()).add(nn.next());
        for (int i = 0; i < 42; ++i) {
            primes.add(i);
        }
        ((IPrintable) primes).print();
        System.out.println();
    }
}
