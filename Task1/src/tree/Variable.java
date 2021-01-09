package tree;

public class Variable implements Expression{

    private String value;

    public Variable(String val){
        value = val;
    }

    @Override
    public String toString() {
        return value;
    }
}
