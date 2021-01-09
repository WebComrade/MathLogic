package tree;

import java.util.ArrayList;
import java.util.List;

public class Condition {

    private Expression taskProof;
    private List<String> hypos = new ArrayList<>();

    public Condition(Expression task,List<Expression> hypotheses){
        for(Expression s:hypotheses){
            hypos.add(s.toString());
        }
        this.taskProof=task;
    }

    public Condition(Expression task){
        this.hypos.add("");
        this.taskProof=task;
    }

    public Expression getTaskProof() {
        return taskProof;
    }

    public List<String> getHypos() {
        return hypos;
    }
}
