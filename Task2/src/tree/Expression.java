package tree;

public interface Expression {
//    public String toTree();

    public Expression getLeft();
    public Expression getRight();

    default String toText(){
        StringBuilder builder = new StringBuilder();
        toText(builder);
        return builder.toString();
    }

    public void toText(StringBuilder builder);

}
