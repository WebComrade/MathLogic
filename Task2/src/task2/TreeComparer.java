package task2;

import tree.Expression;
import tree.Variable;

import java.util.HashMap;

public class TreeComparer {

    public static boolean Compare(Expression expr, Expression axiom){
        return Compare(expr,axiom,new HashMap<Expression,Expression>());
    }

    private static boolean Compare(Expression expr, Expression axiom, HashMap<Expression,Expression> map){

        if(expr==null && axiom==null) return true;
        if(axiom.getClass().equals(Variable.class)){
            Expression exprHash = map.get(axiom);
            if(exprHash!=null && !expr.equals(exprHash)){
                return false;
            }
            map.put(axiom,expr);
            return true;
        }
        if(expr.getClass().equals(axiom.getClass())){
            return(Compare(expr.getLeft(),axiom.getLeft(),map)&&Compare(expr.getRight(),axiom.getRight(),map));
        }
        return false;
    }

}
