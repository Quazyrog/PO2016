package po.lab.booleanexpressions;

public final class Const implements IBooleanExpression {
    private final boolean value;

    public Const(boolean value) {
        this.value = value;
    }

    @Override
    public boolean value(boolean xValue) {
        return value;
    }

    @Override
    public String toString() {
        if (value)
            return "1";
        else
            return "0";
    }

    @Override
    public byte computationPriority() {
        return 0;
    }

    @Override
    public boolean isTrue() {
        return value;
    }

    @Override
    public boolean isFalse() {
        return !value;
    }

    @Override
    public IBooleanExpression simplified() {
        return this;
    }
}
