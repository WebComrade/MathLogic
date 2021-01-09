package task2;

import tree.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeBuilder {

//    private Expression expr;

    private HashMap<Expression,String> map = new HashMap<>();
    private String[] charList = {"A","B","C","D","E","F","G","H"};

    public TreeBuilder(){
//        this.expr = expr;
    }
    public Expression build(Expression expr,int depth){
        if(expr.getDepth()==depth){
            if(!map.containsKey(expr.getLeft())){
                String ch = charList[0];
                map.put(expr.getLeft(),ch);
                String[] newList = new String[charList.length];
                for(int i=1;i<charList.length;i++){
                    newList[i-1] = charList[i];
                }
                charList = newList;
                return new Variable(ch);
            }
            else{
                return new Variable(map.get(expr));
            }
        }
        if(expr.getDepth()==depth && expr.getRight()!=null) {
            if (!map.containsKey(expr.getRight())) {
                String ch = charList[0];
                map.put(expr.getRight(), ch);
                String[] newList = new String[charList.length];
                for (int i = 1; i < charList.length; i++) {
                    newList[i - 1] = charList[i];
                }
                charList = newList;
                return new Variable(ch);
            } else {
                return new Variable(map.get(expr));
            }
        }
            if (expr.getClass().equals(Conjunction.class)) {
                return new Conjunction(build(expr.getLeft(),depth), build(expr.getRight(),depth));
            }
            if (expr.getClass().equals(Disjunction.class)) {
                return new Disjunction(build(expr.getLeft(),depth), build(expr.getRight(),depth));
            }
            if (expr.getClass().equals(Implication.class)) {
                return new Implication(build(expr.getLeft(),depth), build(expr.getRight(),depth));
            }
            if (expr.getClass().equals(Negation.class)) {
                return new Negation(build(expr.getLeft(), depth));
            }
            if (expr.getClass().equals(Variable.class)) {
                if(!map.containsKey(expr)){
                    String ch = charList[0];
                    map.put(expr,ch);
                    String[] newList = new String[charList.length];
                    for(int i=1;i<charList.length;i++){
                        newList[i-1] = charList[i];
                    }
                    charList = newList;
                    return new Variable(ch);
                }
                else{
                    return new Variable(map.get(expr));
                }
            }
            else return null;
    }
}
