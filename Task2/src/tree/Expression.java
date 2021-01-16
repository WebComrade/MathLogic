package tree;

public interface Expression {
    public String toTree();

    public Expression getLeft();
    public Expression getRight();

}
