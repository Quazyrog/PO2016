package po.filters;

public class Main {

    public static void main(String[] args) {
        IGenerator rand = new Random();
        Comparator sorter = new Comparator();
        for (int i = 0; i < 10; ++i) {
            sorter.add(rand.next());
        }
        sorter.print();
        System.out.println();

        IGenerator nn = new Natural();
        nn.next();
        nn.next();
        Divisor primes = new Divisor();
        for (int i = 0; i < 42; ++i) {
            primes.add(nn.next());
        }
        primes.print();
        System.out.println();
    }
}
