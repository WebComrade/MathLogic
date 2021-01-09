package tree;

public class Disjunction implements Expression {

    private Expression left;
    private Expression right;

    public Disjunction(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(|,"+left+","+right+")";
    }
}
