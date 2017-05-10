package po.lab.booleanexpressions;

final public class Variable implements IBooleanExpression {
    @Override
    public boolean value(boolean xValue) {
        return xValue;
    }

    @Override
    public String toString() {
        return "x";
    }

    @Override
    public byte computationPriority() {
        return 0;
    }

    @Override
    public boolean isTrue() {
        return false;
    }

    @Override
    public boolean isFalse() {
        return false;
    }

    @Override
    public IBooleanExpression simplified() {
        return this;
    }
}
