package po.filters;

/**
 * Created by saphir on 10/05/17.
 */
public class Natural implements IGenerator {
    private int state = 0;

    @Override
    public int next() {
        return state++;
    }
}
