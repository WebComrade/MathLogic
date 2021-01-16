package tree;

import java.util.Objects;

public class Variable implements Expression{

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
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
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
