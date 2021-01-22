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
    public void toText(StringBuilder builder) {
        builder.append(value);
    }

    @Override
    public String toString() {
        return toText();
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

//    @Override
//    public String toTree() {
//        return value;
//    }


