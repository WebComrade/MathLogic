package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Condition {

    private Expression taskProof;
    private HashMap<Expression,Integer> hypos = new HashMap<>();

    public Condition(Expression task,List<Expression> hypotheses){
        for(Expression hyp:hypotheses){
            hypos.putIfAbsent(hyp,hypos.size()+1);
        }
        this.taskProof=task;
    }

    public Condition(Expression task){
        this.hypos= null;
        this.taskProof=task;
    }

    public Expression getTaskProof() {
        return taskProof;
    }

    public HashMap<Expression, Integer> getHypos() {
        return hypos;
    }
}
