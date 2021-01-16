import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import task2.*;
import tree.Condition;
import tree.Expression;
import tree.Implication;

import java.util.*;

public class ProofBuilder {
    private static final Scanner sc = new Scanner(System.in);

    private static final HashMap<Expression, Integer> lefts=new HashMap<>();
    private static final HashMap<Expression, List<Integer>> rights=new HashMap<>();
    private static final HashMap<Expression,List<Expression>> implications = new HashMap<>();
    private static final HashMap<Expression,List<Integer>> implicationsIndex = new HashMap<>();

    private static List<Expression> hypos = new ArrayList<>();
    public static  TreeMap<Integer,Dokvo> proved = new TreeMap<>();
    private static TreeMap<Integer,Dokvo> used = new TreeMap<>();

    private static List<Integer> findMP(Expression expr, int exprIndex){
        List<Integer> res = rights.get(expr);
        if(expr.getClass().equals(Implication.class)){
            Expression left = expr.getLeft();
            Expression right = expr.getRight();
            Integer leftIndex = lefts.get(left);
            if(leftIndex!=null){
                rights.putIfAbsent(right,Arrays.asList(leftIndex,exprIndex));
            }
            else{
                if(implications.get(left)==null){
                    implications.put(left,new ArrayList<>());
                    implicationsIndex.put(left, new ArrayList<>());
                }
                implications.get(left).add(right);
                implicationsIndex.get(left).add(exprIndex);
            }
        }
        if(implications.get(expr)!=null){
            List<Expression> implRights = implications.get(expr);
            List<Integer> implRightsIndex = implicationsIndex.get(expr);
            for(int i=0;i<implRights.size();i++){
                rights.putIfAbsent(implRights.get(i),Arrays.asList(exprIndex,implRightsIndex.get(i)));
            }
            implications.put(expr,null);
            implicationsIndex.put(expr,null);
        }
        lefts.putIfAbsent(expr, exprIndex);
        return res;
    }



    public static void start(){
        String str=sc.nextLine();

        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);

        Condition condition = parser.condition().cond;
        hypos = condition.getHypos();

        Expression curExpr;
        int index = 0;

        do{
            str = sc.nextLine();
            lexer.setInputStream(CharStreams.fromString(str));
            parser.setTokenStream(new CommonTokenStream(lexer));
            curExpr = parser.expression().expr;

            if(proved.containsValue(curExpr)){
                continue;}

            List<Integer> mp = findMP(curExpr, index);
            Dokvo dokvo = new Dokvo(curExpr);

            if(isHypothesis(dokvo)){
            }
            else if(isAxiom(dokvo)){
            }
            else if(mp!=null) {
                dokvo.setMP(mp.get(0),mp.get(1));
            }
            else {
                System.out.println("Proof is incorrect");
                return;
            }
            proved.put(index, dokvo);
            index++;
        }while(!curExpr.equals(condition.getTaskProof()));

        if(!hypos.isEmpty())
            for(int i=0;i<hypos.size();i++) {
                System.out.print(hypos.get(i));
                if (i != hypos.size() - 1) System.out.print(", ");
                else System.out.println("|- " + condition.getTaskProof());
            }
        else
            System.out.println("|- "+ condition.getTaskProof());

        used = getUsed();
        HashMap<Integer,Integer> newIndex = new HashMap<>();
        List<Integer> proofsOrder = new ArrayList<>(used.keySet());

        for(int i=0;i<proofsOrder.size();i++){
            Dokvo dokvo = used.get(proofsOrder.get(i));
            Expression expression = dokvo.getExpr();
            newIndex.put(proofsOrder.get(i),i);

            if(dokvo.isMP()){
                int leftIndex = newIndex.get(dokvo.getLeftIndex())+1;
                int implIndex = newIndex.get(dokvo.getImplicationIndex())+1;
                System.out.printf("[%d. M.P. %d, %d] %s\n",i+1, implIndex, leftIndex, expression);
            }

            Integer hypoIndex = dokvo.getHypothesisIndex();
            if(hypoIndex!=null){
                System.out.printf("[%d. Hypothesis %d] %s\n", i+1, hypoIndex, expression);
            }
            Integer axiomIndex = dokvo.getAxiomIndex();
            if(axiomIndex!=null){
                System.out.printf("[%d. Ax. sch. %d] %s\n", i+1, axiomIndex, expression);
            }

        }



    }

    private static boolean isAxiom(Dokvo dokvo){
        List<Expression> list = AxiomSchemes.getAxiomsList();
        for(int i=1;i<=list.size();i++){
            if(TreeComparer.Compare(dokvo.getExpr(),list.get(i-1))){
                dokvo.setAxiom(i);
                return true;
            }
        }
        return false;
    }

    private static boolean isHypothesis(Dokvo dokvo){
        if(hypos!=null)
        for(int i=1;i<=hypos.size();i++){
            if(hypos.get(i-1).equals(dokvo.getExpr())){
            dokvo.setHypothesis(i);
            return true;
            }
        }
        return false;
    }


    private static TreeMap<Integer,Dokvo> getUsed(){
        int dokvoIndex = proved.size()-1;
        TreeMap<Integer,Dokvo> dokvos = new TreeMap<>();
        Dokvo dokvo = proved.get(dokvoIndex);
        if(dokvo.isMP()){
            getUsedFromIndex(dokvoIndex,dokvos);
        }
        dokvos.put(dokvoIndex,dokvo);
        return dokvos;
    }

    private static void getUsedFromIndex(Integer index, TreeMap<Integer,Dokvo> dokvos){
        Dokvo dokvo = proved.get(index);
        Dokvo left = proved.get(dokvo.getLeftIndex());
        Dokvo implication = proved.get(dokvo.getImplicationIndex());
        if(left.isMP()){
            getUsedFromIndex(dokvo.getLeftIndex(), dokvos);
        }
        dokvos.put(dokvo.getLeftIndex(),left);
        if(implication.isMP()){
            getUsedFromIndex(dokvo.getImplicationIndex(),dokvos);
        }
        dokvos.put(dokvo.getImplicationIndex(),implication);
    }
}
