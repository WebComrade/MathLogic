import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import task2.*;
import tree.Condition;
import tree.Expression;
import tree.Implication;

import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);


    private static List<Expression> expressions = new ArrayList<>();
    private static List<Expression> hypos = new ArrayList<>();
    private static Map<Expression, Proof> map = new HashMap<>();
    public static List<Expression> proved = new ArrayList<>();
    public static List<Expression> proved1 = new ArrayList<>();
    private static Set<Expression> used = new HashSet<>();

    private static Expression getCurExpr(String str){
        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);
        return parser.expression().expr;
    }

    public static void main(String[] args){
        start();
//        String str = sc.nextLine();
//        Expression expr = getCurExpr(str);
//        System.out.println(expr.toTree());
//
//
//        List<Expression> list = AxiomSchemes.getAxiomsList();
//        System.out.println(TreeComparer.Compare(expr,list.get(8)));
    }

    private static void start(){
        String str=sc.nextLine();

        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);

        Condition condition = parser.condition().cond;
        hypos = condition.getHypos();

        do{
            str = sc.nextLine();
            expressions.add(getCurExpr(str));
        }while(!getCurExpr(str).equals(condition.getTaskProof()));

        for(int i=1;i<=expressions.size();i++){
            Expression curExpr = expressions.get(i-1);
            if(!proved.isEmpty() && proved.contains(curExpr)) continue;
            else if(isHypothesis(curExpr)) continue;
            else if(isMP(curExpr)) continue;
            else if(isAxiom(curExpr)) continue;
            else {
                System.out.println("Proof is incorrect");
                return;
            }
        }

        for(Expression expr:proved){
            if(used.contains(expr) || expr.equals(condition.getTaskProof())){
                proved1.add(expr);
                continue;
            }
        }

        if(hypos!=null)
            for(int i=0;i<hypos.size();i++) {
                System.out.print(hypos.get(i));
                if (i != hypos.size() - 1) System.out.print(", ");
                else System.out.println("|- " + condition.getTaskProof());
            }
        else
            System.out.println("|- "+condition.getTaskProof());

        for(Expression expr:proved1){
            System.out.println(map.get(expr).getText(proved1));
        }



//        System.out.println("HYPOTHESES: "+hypos+"\nTASK: "+condition.getTaskProof());
//        System.out.println("EXPRESSIONS: "+expressions);
//        System.out.println();
//        for(Expression expr:proved){
//            System.out.println(map.get(expr));
//        }


    }

    private static boolean isMP(Expression expr){
        for(int i=0;i<proved.size();i++){
            if(proved.get(i).getClass().equals(Implication.class) && expr.equals(proved.get(i).getRight())){
                for(int j=0;j<proved.size();j++){
                    if(proved.get(j).equals(proved.get(i).getLeft())) {
                        proved.add(expr);
                        used.add(proved.get(j));
                        used.add(proved.get(i));
                        used.add(expr);
                        map.put(expr,new ModusPonens(proved.get(i),proved.get(j),expr));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isAxiom(Expression expr){
        List<Expression> list = AxiomSchemes.getAxiomsList();
        for(int i=1;i<=list.size();i++){
            if(TreeComparer.Compare(expr,list.get(i-1))){
                proved.add(expr);
                map.put(expr,new Axiom(expr,i));
                return true;
            }
        }
        return false;
    }

    private static boolean isHypothesis(Expression expr){
        if(hypos!=null && hypos.contains(expr)){
            proved.add(expr);
            map.put(expr,new Hypothesis(expr,hypos));
            return true;
        }
        return false;
    }

}
