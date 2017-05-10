package po.filters;

/**
 * Created by saphir on 10/05/17.
 */
public class Random implements IGenerator {
    java.util.Random rng = new java.util.Random();

    @Override
    public int next() {
        return rng.nextInt();
    }
}
