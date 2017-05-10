package po.filters;

import java.util.Scanner;

/**
 * Created by saphir on 10/05/17.
 */
class Reader implements IGenerator {
    static private Scanner scanner = new Scanner(System.in);

    @Override
    public int next() {
        return scanner.nextInt();
    }
}
