package task2;

import tree.Expression;

import java.util.List;

public class Hypothesis implements Proof {

    private Expression expression;
    private List<Expression> hypos;

    public Hypothesis(Expression expression, List hypos){
        this.expression = expression;
        this.hypos = hypos;
    }
    @Override
    public String getText(List list) {
        return "["+(list.indexOf(expression)+1)+". Hypothesis "+(hypos.indexOf(expression)+1)+"] "+expression;
    }
}
