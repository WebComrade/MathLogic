package tree;

import java.util.Objects;

public class Variable implements Expression{

    private String value;

    public Variable(String val){
        value = val;
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
    public int hashCode() {
        return Objects.hash(value);
    }
}
