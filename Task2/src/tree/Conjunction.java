package tree;

import java.util.Objects;

public class Conjunction implements Expression{

    private int depth=1;
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
    public void setDepth(int d){
        this.depth = d;
        left.setDepth(d+1);
        right.setDepth(d+1);
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void showDepth(){
        System.out.println(this.toString()+" "+depth);
        left.showDepth();
        right.showDepth();
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
        if(this.toString().equals(o.toString())) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
