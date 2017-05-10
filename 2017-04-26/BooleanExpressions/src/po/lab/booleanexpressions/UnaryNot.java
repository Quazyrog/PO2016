package po.lab.booleanexpressions;

public class UnaryNot extends UnaryOperator {
    public UnaryNot(IBooleanExpression arg) {
        super(arg);
    }

    @Override
    public boolean value(boolean xValue) {
        return !arg.value(xValue);
    }

    @Override
    protected String operatorName() {
        return "not";
    }

    @Override
    public boolean isTrue() {
        return arg.isFalse();
    }

    @Override
    public boolean isFalse() {
        return arg.isTrue();
    }

    @Override
    public IBooleanExpression simplified() {
        if (arg.isFalse())
            return new Const(true);
        if (arg.isTrue())
            return new Const(false);
        return this;
    }
}
