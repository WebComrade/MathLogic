import tree.Expression;

import java.util.Objects;

public class Dokvo {
    private Expression expr;
    private boolean modusPonens = false;
    private boolean isHypothesis = false;
    private boolean isAxiom = false;
    private Integer hypothesisIndex;
    private Integer axiomIndex;
    private Integer leftIndex;
    private Integer implicationIndex;

    public Dokvo(Expression expr){
        this.expr =expr;
    }

    public void setMP(int leftIndex, int implicationIndex){
        modusPonens=true;
        this.leftIndex = leftIndex;
        this.implicationIndex = implicationIndex;
    }

    public void setHypothesis(int index){
        isHypothesis = true;
        hypothesisIndex = index;
    }

    public void setAxiom(int axiomNum) {
        isAxiom = true;
        axiomIndex = axiomNum;
    }

    public boolean isMP(){
        return modusPonens;
    }

    public Expression getExpr(){
        return expr;
    }

    public Integer getLeftIndex() {
        return leftIndex;
    }

    public Integer getImplicationIndex() {
        return implicationIndex;
    }

    public Integer getHypothesisIndex() {
        return hypothesisIndex;
    }

    public Integer getAxiomIndex() {
        return axiomIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dokvo dokvo = (Dokvo) o;
        return this.expr.equals(dokvo.getExpr());
    }

}
