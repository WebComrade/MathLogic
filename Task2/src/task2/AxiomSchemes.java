package task2;

import tree.*;

import java.util.Arrays;
import java.util.List;

public class AxiomSchemes {

    private static Expression Ax1(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication( A ,new Implication(B,A));
    }

    private static Expression Ax2(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        Expression C = new Variable("C");
        return new Implication(new Implication(A,B),new Implication(new Implication(A,new Implication(B,C)), new Implication(A,C)));
    }

    private static Expression Ax3(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(A,new Implication(B,new Conjunction(A,B)));
    }

    private static Expression Ax4(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(new Conjunction(A,B),A);
    }

    private static Expression Ax5(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(new Conjunction(A,B),B);
    }

    private static Expression Ax6(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(A,new Disjunction(A,B));
    }

    private static Expression Ax7(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(B,new Disjunction(A,B));
    }

    private static Expression Ax8(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        Expression C = new Variable("C");
        return new Implication(new Implication(A,C),new Implication(new Implication(B,C),new Implication(new Disjunction(A,B),C)));
    }

    private static Expression Ax9(){
        Expression A = new Variable("A");
        Expression B = new Variable("B");
        return new Implication(new Implication(A,B),new Implication(new Implication(A,new Negation(B)),new Negation(A)));
    }

    private static Expression Ax10(){
        Expression A = new Variable("A");
        return new Implication(new Negation(new Negation(A)),A);
    }

    public static List<Expression> getAxiomsList(){
        return Arrays.asList(
                Ax1(),
                Ax2(),
                Ax3(),
                Ax4(),
                Ax5(),
                Ax6(),
                Ax7(),
                Ax8(),
                Ax9(),
                Ax10());

    }
}
