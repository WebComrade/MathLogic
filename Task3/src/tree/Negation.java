package tree;

import java.util.Objects;

public class Negation implements Expression {
    private Expression expr;

    public Negation(Expression expression){
        expr = expression;
    }

    @Override
    public String toString() {
        return "!"+expr;
    }

    @Override
    public boolean equals(Object o) {
        if(this.toString().equals(o.toString())) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(expr);
    }
}
