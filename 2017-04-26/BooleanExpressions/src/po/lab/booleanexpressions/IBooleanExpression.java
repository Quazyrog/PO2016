package po.lab.booleanexpressions;


public interface IBooleanExpression {
    String toString();
    boolean value(boolean xValue);
    byte computationPriority();
    boolean isTrue();
    boolean isFalse();

    IBooleanExpression simplified();
}
