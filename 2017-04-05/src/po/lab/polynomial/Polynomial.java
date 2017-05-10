package po.lab.polynomial;

import java.util.LinkedList;

public class Polynomial {
    LinkedList<Monomial> monomials = new LinkedList<>();

    public Polynomial(double ...coefficients) {
        for (int i = 0; i < coefficients.length; ++i) {
            if (coefficients[i] != 0) {
                monomials.add(new Monomial(coefficients[i], i));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Monomial m : monomials) {
            if (m.coefficient() > 0) {
                builder.append('+');
                builder.append(m.toString());
            } else {
                builder.append(m.toString());
            }
        }

        if (builder.length() > 0) {
            if (builder.charAt(0) == '+') {
                builder.deleteCharAt(0);
            }
            return builder.toString();
        }
        return "0";
    }

    public double evaluate(double x) {
        double value = 0;
        for (Monomial m : monomials) {
            value += m.evaluate(x);
        }
        return value;
    }

    public int degree() {
        if (monomials.isEmpty()) {
            return -1;
        }
        return monomials.getLast().exponent();
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        SummationIterator it = new SummationIterator(monomials, other.monomials);
        while (it.hasNext()) {
            Monomial m = it.next();
            if (m.coefficient() != 0) {
                result.monomials.add(m);
            }
        }
        return result;
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        for (Monomial m : other.monomials) {
            result = result.add(multiply(m));
        }
        return result;
    }

    public Polynomial derivative() {
        Polynomial poly = new Polynomial();
        for (Monomial m : monomials) {
            if (m.exponent() > 0) {
                poly.monomials.add(m.derrivative());
            }
        }
        return poly;
    }

    protected Polynomial multiply(Monomial monmial) {
        Polynomial result = new Polynomial();
        for (Monomial m : monomials) {
            result.monomials.add(m.multiply(monmial));
        }
        return result;
    }
}
