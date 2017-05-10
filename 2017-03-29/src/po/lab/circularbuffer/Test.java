package po.lab.circularbuffer;

import java.util.function.Function;

public class Test {
    public static void main(String[] args) {
        CircularIndexSpace indexSpace = new CircularIndexSpace(17);
        assert indexSpace.length() == 0;
        indexSpace.decFront();
        assert indexSpace.offsetIndex(-1) == indexSpace.offsetIndex(0);
        indexSpace.decFront();
        assert indexSpace.offsetIndex(-2) == indexSpace.offsetIndex(0);
        assert indexSpace.offsetIndex(-1) == indexSpace.offsetIndex(1);

        CircularBuffer<String> buffer = new CircularBuffer<>(4);
        Function<String, Function<String, String>> catLeft = s -> s::concat;
        buffer.pushBack("a");
        buffer.pushBack("d");
        buffer.pushBack("a");
        assert buffer.reduceLeft(catLeft, "").equals("ada");
        buffer.pushFront("r");
        buffer.pushBack("r");
        assert buffer.reduceLeft(catLeft, "").equals("radar");
        assert buffer.reduceRight(catLeft, "").equals("radar");
        buffer.pushFront("e");
        buffer.pushFront("m");
        assert buffer.reduceRight(catLeft, "").equals("radarem");
    }
}
