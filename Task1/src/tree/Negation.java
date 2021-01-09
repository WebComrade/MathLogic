package tree;

public class Negation implements Expression {
    private Expression expr;

    public Negation(Expression expression){
        expr = expression;
    }

    @Override
    public String toString() {
        return "(!"+expr+")";
    }
}
