package tree;

import java.util.Objects;

public class Negation implements Expression {

    private int depth=1;
    private Expression expr;

    public Negation(Expression expression){
        expr = expression;
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
    public void setDepth(int d){
        this.depth = d;
        expr.setDepth(d);
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void showDepth(){
        System.out.println(this.toString()+" "+depth);
        expr.showDepth();
    }

    @Override
    public String toString() {
        return "!"+expr;
    }

    @Override
    public String toTree() {
        return "(!"+expr.toTree()+")";
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
