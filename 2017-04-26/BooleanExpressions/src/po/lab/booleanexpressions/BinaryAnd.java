package po.lab.booleanexpressions;

public class BinaryAnd extends BinaryOperator {
    public BinaryAnd(IBooleanExpression larg, IBooleanExpression ragr) {
        super(larg, ragr);
    }

    @Override
    public boolean value(boolean xValue) {
        return larg.value(xValue) && rarg.value(xValue);
    }

    @Override
    protected String operatorName() {
        return "and";
    }

    @Override
    public boolean isTrue() {
        return larg.isTrue() && rarg.isTrue();
    }

    @Override
    public boolean isFalse() {
        return larg.isFalse() || larg.isFalse();
    }

    @Override
    public IBooleanExpression simplified() {
        IBooleanExpression slarg = larg.simplified();
        IBooleanExpression srarg = rarg.simplified();

        if (slarg.isFalse() || srarg.isFalse())
            return new Const(false);
        if (slarg.isTrue() && srarg.isTrue())
            return new Const(true);
        if (slarg.isTrue())
            return srarg;
        if (srarg.isTrue())
            return slarg;
        return new BinaryAnd(slarg, srarg);
    }
}
