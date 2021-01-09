package task2;

import tree.Expression;

import java.util.List;

public class ModusPonens implements Proof {

    private Expression A;
    private Expression B;
    private Expression expression;

    public String getText(List list) {
        return "["+(list.indexOf(expression)+1)+". M.P. " + (list.indexOf(A)+1) + ", " + (list.indexOf(B)+1) + "] " + expression;
    }

    public ModusPonens(Expression A, Expression B, Expression expression){
        this.A = A;
        this.B = B;
        this.expression = expression;
    }
}
