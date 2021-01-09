package tree;

public interface Expression {
    public String toTree();
    public void setDepth(int d);
    public int getDepth();
    public void showDepth();

    public Expression getLeft();
    public Expression getRight();

}
