package po.lab.booleanexpressions;

public abstract class BinaryOperator extends Operator {
    protected IBooleanExpression larg, rarg;

    BinaryOperator(IBooleanExpression larg, IBooleanExpression rarg) {
        this.larg = larg;
        this.rarg = rarg;
    }

    @Override
    public byte computationPriority() {
        return 1;
    }

    @Override
    public String toString() {
        return prepareString(larg) + " " + operatorName() + " " + prepareString(rarg);
    }

    private String prepareString(IBooleanExpression expr) {
        if (expr.computationPriority() < computationPriority()) {
            return expr.toString();
        }
        if (expr instanceof Operator) {
            Operator oexpr = (Operator)expr;
            if (oexpr.operatorName().equals(operatorName())) {
                return oexpr.toString();
            }
        }
        return "(" + expr.toString() + ")";
    }
}
