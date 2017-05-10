package po.lab;

import po.lab.booleanexpressions.*;

public class Main {
    public static void main(String[] args) {
        IBooleanExpression expr = new BinaryOr(
                new BinaryAnd(
                        new Variable(),
                        new UnaryNot(new Const(true))
                ),
                new BinaryAnd(
                        new UnaryNot(new Variable()),
                        new Const(true)
                )
        );
        IBooleanExpression biggerExpr = new BinaryOr(expr, expr);
        IBooleanExpression biggestExpr = new BinaryAnd(biggerExpr, new BinaryAnd(expr, new Const(true)));

        System.out.println(expr + " :: " + expr.value(true) + " :: " + expr.value(false));
        System.out.println(biggerExpr);
        System.out.println(biggestExpr);
        System.out.println(biggerExpr.simplified());
    }
}

