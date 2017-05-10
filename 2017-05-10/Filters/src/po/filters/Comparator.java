package po.filters;

import java.awt.print.Printable;

/**
 * Created by saphir on 10/05/17.
 */
public class Comparator extends CircuitFilter {
    private int content;

    public Comparator(int content, IFilter next) {
        super(next);
        this.content = content;
    }

    @Override
    public IFilter add(int num) {
        if (content < num) {
            int oldContent = content; //In name of tail recursion?
            content = num;
            next().add(oldContent);
        } else {
            next().add(num);
        }
        return this;
    }
}
