package po.lab.polynomial;

public class Monomial {
    private int exponent;
    private double coefficient;

    Monomial(double coefficient, int exponent) {
        assert exponent >= 0;
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    double coefficient() {
        return coefficient;
    }

    int exponent() {
        return exponent;
    }

    @Override
    public String toString() {
        if (coefficient != 0) {
            if (exponent > 1) {
                return coefficient + "x^" + exponent;
            } else if (exponent == 1) {
                return coefficient + "x";
            } else {
                return ((Double)coefficient).toString();
            }
        }
        return "0";
    }

    double evaluate(double xValue) {
        return coefficient * Math.pow(xValue, exponent);
    }

    Monomial derrivative() {
        return new Monomial(coefficient * exponent, exponent - 1);
    }

    Monomial add(Monomial other) {
        assert exponent == other.exponent;
        return new Monomial(coefficient + other.coefficient, exponent);
    }

    Monomial multiply(Monomial other) {
        return new Monomial(coefficient * other.coefficient, exponent + other.exponent);
    }
}
