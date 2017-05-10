package po.lab;

import po.lab.polynomial.Polynomial;

public class Main {

    public static void main(String[] args) {
        Polynomial p0 = new Polynomial(1, -2, 1);
        assert p0.evaluate(1) == 0;
        assert p0.toString().equals("1.0-2.0x+1.0x^2");
        assert p0.add(p0).toString().equals("2.0-4.0x+2.0x^2");
        assert p0.degree() == 2;
        assert p0.derivative().toString().equals("-2.0+2.0x");

        Polynomial p1 = new Polynomial(1, -3, 3, -1);
        assert p1.evaluate(1) == 0;
        assert p1.toString().equals("1.0-3.0x+3.0x^2-1.0x^3");
        assert p1.add(p0).toString().equals("2.0-5.0x+4.0x^2-1.0x^3");
        assert p1.degree() == 3;

        Polynomial none = new Polynomial(0);
        assert none.toString().equals("0");
        assert none.degree() == -1;
        assert none.derivative().toString().equals("0");

        Polynomial cons = new Polynomial(1);
        assert cons.toString().equals("1.0");
        assert cons.degree() == 0;
        assert cons.derivative().toString().equals("0");

        Polynomial mulmul = p0.multiply(p1);
        double x;
        x = 0; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 2; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 4; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 8; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 16; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 18; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = -6; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
        x = 42; assert mulmul.evaluate(x) == p0.evaluate(x) * p1.evaluate(x);
    }
}
