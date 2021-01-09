package tree;

import java.util.Objects;

public class Variable implements Expression{

    private int depth=1;
    private String value;

    public Variable(String val){
        value = val;
    }

    @Override
    public Expression getRight() {
        return null;
    }

    @Override
    public Expression getLeft() {
        return this;
    }

    @Override
    public void setDepth(int d){
        this.depth = d;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void showDepth(){
        System.out.println(this.toString()+" "+depth);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if(this.toString().equals(o.toString())) return true;
        else return false;
    }

    @Override
    public String toTree() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
