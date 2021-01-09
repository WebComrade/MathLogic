package tree;

public class Implication implements Expression {
    private Expression left;
    private Expression right;

    public Implication(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(->,"+left+","+right+")";
    }
}
