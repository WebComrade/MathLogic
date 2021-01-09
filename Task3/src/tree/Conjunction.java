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
    public String toString() {
        return "("+left+" & "+right+")";
    }

    @Override
    public boolean equals(Object o) {
        if(this.toString().equals(o.toString())) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
