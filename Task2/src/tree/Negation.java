package tree;

import java.util.Objects;

public class Negation implements Expression {

    private Expression expr;

    public Negation(Expression expression){
        expr = expression;
    }

    @Override
    public void toText(StringBuilder builder) {
        builder.append("!");
        expr.toText(builder);
    }

    @Override
    public Expression getRight() {
        return null;
    }

    @Override
    public Expression getLeft() {
        return expr;
    }


    @Override
    public String toString() {
        return toText();
    }

//    @Override
//    public String toTree() {
//        return "(!"+expr.toTree()+")";
//    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }
}
