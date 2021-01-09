package task2;

import tree.Expression;

import java.util.List;

public class Axiom implements Proof {

    private Expression expression;
    private int index;

    public Axiom(Expression expression, int index){
        this.expression = expression;
        this.index = index;
    }

    @Override
    public String getText(List list) {
        return "["+(list.indexOf(expression)+1)+". Ax. sch. "+index+"] "+expression;
    }
}
