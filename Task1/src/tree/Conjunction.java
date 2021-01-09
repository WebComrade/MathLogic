package tree;

public class Conjunction implements Expression{

    private Expression left;
    private Expression right;

    public Conjunction(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(&,"+left+","+right+")";
    }
}
