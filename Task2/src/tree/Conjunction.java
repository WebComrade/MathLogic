package tree;

import java.util.Objects;

public class Conjunction implements Expression{

    private Expression left;
    private Expression right;

    public Conjunction(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression getRight() {
        return right;
    }

    @Override
    public Expression getLeft() {
        return left;
    }


    @Override
    public String toString() {
        return "("+left+" & "+right+")";
    }

    @Override
    public String toTree() {
        return "(&,"+left.toTree()+","+right.toTree()+")";
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
