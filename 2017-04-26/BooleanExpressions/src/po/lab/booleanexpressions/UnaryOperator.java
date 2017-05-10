package po.lab.booleanexpressions;

public abstract class UnaryOperator extends Operator {
    protected IBooleanExpression arg;

    public UnaryOperator(IBooleanExpression arg) {
        this.arg = arg;
    }

    @Override
    public byte computationPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return operatorName() + " " + arg.toString();
    }
}
