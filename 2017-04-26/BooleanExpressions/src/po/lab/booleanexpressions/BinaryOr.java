package po.lab.booleanexpressions;

public class BinaryOr extends BinaryOperator {
    public BinaryOr(IBooleanExpression larg, IBooleanExpression ragr) {
        super(larg, ragr);
    }

    @Override
    public boolean value(boolean xValue) {
        return larg.value(xValue) || rarg.value(xValue);
    }

    @Override
    protected String operatorName() {
        return "or";
    }

    @Override
    public boolean isTrue() {
        return larg.isTrue() || rarg.isTrue();
    }

    @Override
    public boolean isFalse() {
        return larg.isFalse() && rarg.isFalse();
    }

    @Override
    public IBooleanExpression simplified() {
        IBooleanExpression slarg = larg.simplified();
        IBooleanExpression srarg = rarg.simplified();

        if (slarg.isTrue() || srarg.isTrue())
            return new Const(true);
        if (slarg.isFalse() && srarg.isFalse())
            return new Const(false);
        if (slarg.isFalse())
            return srarg;
        if (srarg.isFalse())
            return slarg;
        return new BinaryOr(slarg, srarg);
    }
}
