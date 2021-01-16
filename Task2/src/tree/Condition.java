package tree;

import java.util.ArrayList;
import java.util.List;

public class Condition {

    private Expression taskProof;
    private List<Expression> hypos;

    public Condition(Expression task,List<Expression> hypotheses){
        this.hypos = hypotheses;
        this.taskProof=task;
    }

    public Condition(Expression task){
        this.hypos= null;
        this.taskProof=task;
    }

    public Expression getTaskProof() {
        return taskProof;
    }

    public List<Expression> getHypos() {
        return hypos;
    }
}
