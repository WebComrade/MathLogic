import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import tree.Condition;
import tree.Expression;

import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);


    private static List<String> expressions = new ArrayList<>();
    private static List<String> hypos = new ArrayList<>();
    private static Map<Integer, String> map = new HashMap<>();

    private static Expression getCurExpr(String str){
        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);
        return parser.expression().expr;
    }

    public static void main(String[] args){
        String str="";
        Condition condition;
        str = sc.nextLine();
        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);
        condition = parser.condition().cond;

        hypos = condition.getHypos();
        map.put(1,condition.getTaskProof().toString());
        Expression curExpr;
        int i=1;
        do{
            str = sc.nextLine();
            curExpr = getCurExpr(str);
            expressions.add(curExpr.toString());
            map.put(i,curExpr.toString());
            i++;
        }while(!curExpr.equals(condition.getTaskProof()));
//        while(!curExpr.equals(condition.getTaskProof())) {
//            expressions.add(curExpr.toString());
//            map.put(i,curExpr.toString());
//            i++;
//            str = sc.nextLine();
//            curExpr = getCurExpr(str);
//        }

        if(!condition.getTaskProof().toString().equals("")){
            System.out.println(hypos.toString().substring(1,hypos.toString().length()-1)+"|-!!"+condition.getTaskProof().toString());
        }
        new Glivenko(expressions,map,hypos);
//        System.out.println("HYPOTHESES: "+hypos+"\nTASK: "+condition.getTaskProof());
//        System.out.println("EXPRESSIONS: "+expressions);


    }



    static class Glivenko{

        static class Axioms{
            public static String firstAx(String a, String b) {
                return "(" + a + " -> (" + b + " -> " + a + "))";
            }
            public static String secondAx(String a, String b, String c) {
                return "((" + a + " -> " + b + ") -> ((" + a + " -> (" + b + " -> " + c + "))" + " -> " + "(" + a + " -> " + c + ")))";
            }
            public static String thirdAx(String a, String b) {
                return "(" + a + " -> " + "(" + b + " -> " + "(" + a + " & " + b + "))";
            }
            public static String forthAx(String a, String b) {
                return "((" + a + " & " + b + ") -> " + a + ")";
            }
            public static String fifthAx(String a, String b) {
                return "((" + a + " & " + b + ") -> " + b + ")";
            }
            public static String sixthAx(String a, String b) {
                return "(" + a + " -> (" + a + " | " + b + "))";
            }
            public static String seventhAx(String a, String b) {
                return "(" + b + " -> (" + a + " | " + b + "))";
            }
            public static String eighthAx(String a, String b, String c) {
                return "((" + a + " -> " + c + ") -> ((" + b + " -> " + c + ")" + " -> " + "((" + a + " | " + c + ") -> " + c + ")))";
            }
            public static String ninthAx(String a, String b) {
                return "((" + a + " -> " + b + ") -> ((" + a + " -> !" + b + ") -> !" + a + "))";
            }
            public static String tenthAx(String a, String b) {
                return "(" + a + " -> (!" + a + " -> " + b + "))";
            }
        }

        private static List<String> expressions;
        private static List<String> hypos;
        private static Map<Integer, String> hash;
        private static String tenth="";

        public Glivenko(List<String> exprs, Map<Integer, String> hash, List<String> hypos){
            this.expressions = exprs;
            this.hash = hash;
            this.hypos = hypos;
            run();
        }

        private void run(){
            for(int i=0;i<expressions.size();i++){
                String expr = expressions.get(i);
                if(hypos.contains(expr)){
                    template(expr);
                }
                else if(!isModusPonens(expr,i)){
                    if(isTenthAx(expr)){
                        template_tenthax(getTenthV());
                    }
                    else{
                        template(expr);
                    }
                }
            }
        }

        public List<String> output(String str, List<String> expressions) {
            System.out.println(str);
            expressions.add(str);
            return expressions;
        }

        private void template(String a) {
            List<String> expressions = new ArrayList<>();
            expressions = output(a, expressions);
            String b = "!" + a;
            String curExpr = Axioms.firstAx(expressions.get(0), b);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(0), curExpr);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(b, b);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(b, "(" + b + " -> " + b + ")", b);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(3), curExpr);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(b, "(" + b + " -> " + b + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(curExpr, expressions.get(5));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(b, a);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(2), curExpr);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(7), curExpr);
            output(curExpr, expressions);
        }

        private void template_tenthax(String a) {
            List<String> expressions = new ArrayList<>();
            expressions.add(a);
            String b = "!" + a;
            String curExpr = Axioms.firstAx(expressions.get(0), "!" + b);
            expressions = output(curExpr, expressions);
            String element = "!(!" + b + " -> " + expressions.get(0) + ")";
            curExpr = Axioms.firstAx(expressions.get(1), element);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(1), expressions.get(2));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(element, expressions.get(0));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(element, element);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(element, "(" + element + " -> " + element + ")");
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(element, "(" + element + " -> " + element + ")", element);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(5), expressions.get(7));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(6), expressions.get(8));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(expressions.get(0), element.substring(1));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(10), element);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(10), expressions.get(11));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(element, expressions.get(1), "((" + expressions.get(0) + " -> " + element + ") -> " + b + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(3), expressions.get(13));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(12), expressions.get(14));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(element, "(" + expressions.get(0) + " -> " + element + ")", b);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(4), expressions.get(16));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(15), expressions.get(17));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.tenthAx(b, expressions.get(0));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(19), element);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(19), expressions.get(20));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(element, b, element.substring(1));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(18), expressions.get(22));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(21), expressions.get(23));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(element, element.substring(1));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(24), expressions.get(25));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(9), expressions.get(26));
            output(curExpr, expressions);
        }

        private void templateModusPonens(String a, String b) {
            String impl = "(" + a + " -> " + b + ")";
            String negA = "!" + a;
            String negB = "!" + b;
            List<String> expressions = new ArrayList<>();
            String curExpr = Axioms.firstAx(negB, impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(negB, a);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(1), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(1), expressions.get(2));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(1), impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(4), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(4), expressions.get(5));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, expressions.get(1), "(" + impl + " -> " + expressions.get(1) + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(3), expressions.get(7));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(6), expressions.get(8));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(impl, negB, "(" + a + " -> " + negB + ")");
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(10), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(10), expressions.get(11));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + negB + ")", "((" + impl + " -> (" + negB + " -> (" + a + " -> " + negB + "))) -> (" + impl + " -> " + "(" + a + " -> " + negB + ")))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(0), expressions.get(13));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(12), expressions.get(14));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> (" + negB + " -> (" + a + " -> " + negB + ")))", "(" + impl + " -> " + "(" + a + " -> " + negB + "))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(9), expressions.get(16));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(15), expressions.get(17));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(a, b);//20
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(19), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(19), expressions.get(20));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(impl, "(" + a + " -> " + negB + ")", negA);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(22), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(22), expressions.get(23));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + "(" + a + " -> " + negB + "))", "((" + impl + " -> " + "((" + a + " -> " + negB + ") -> " + negA + ")) -> (" + impl + " -> " + negA + "))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(18), expressions.get(25));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(24), expressions.get(26));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + "((" + a + " -> " + negB + ") -> " + negA + "))", "((" + a + " -> " + b + ") -> " + negA + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(21), expressions.get(28));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(27), expressions.get(29));
            expressions = output(curExpr, expressions);
            curExpr = "!" + negA;
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx("!" + negA, negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(31), expressions.get(32));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(31), impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(34), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(34), expressions.get(35));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "!" + negA, "(" + impl + " -> !" + negA + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(33), expressions.get(37));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(36), expressions.get(38));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.tenthAx(negA, "!" + impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(40), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(40), expressions.get(41));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(40), impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(43), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(43), expressions.get(44));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, expressions.get(40), "(" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + ")))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(42), expressions.get(46));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(45), expressions.get(47));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(impl, negA, "(!" + negA + " -> !" + impl + ")");
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(49), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(49), expressions.get(50));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + negA + ")", "((" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + "))) -> (" + impl + " -> (!" + negA + " -> !" + impl + ")))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(30), expressions.get(52));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(51), expressions.get(53));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> (" + negA + " -> (!" + negA + " -> !" + impl + ")))", "(" + impl + " -> (!" + negA + " -> !" + impl + "))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(48), expressions.get(55));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(54), expressions.get(56));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(impl, "!" + negA, "!" + impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(58), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(58), expressions.get(59));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> !!" + a + ")", "((" + impl + " -> (!" + negA + " -> !" + impl + ")) -> (" + impl + " -> !" + impl + "))");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(39), expressions.get(61));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(60), expressions.get(62));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> (!" + negA + " -> !" + impl + "))", "(" + impl + " -> !" + impl + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(57), expressions.get(64));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(63), expressions.get(65));
            expressions = output(curExpr, expressions);
            curExpr = "!!" + impl;
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(67), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(67), expressions.get(68));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(67), impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(70), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(70), expressions.get(71));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, expressions.get(67), "(" + impl + " -> " + expressions.get(67) + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(69), expressions.get(73));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(72), expressions.get(74));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(impl, "!" + impl);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(76), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(76), expressions.get(77));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + "!" + impl + ")", "((" + impl + " -> " + expressions.get(67) + ") -> !" + impl + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(66), expressions.get(79));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(78), expressions.get(80));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "(" + impl + " -> " + expressions.get(67) + ")", "!" + impl);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(75), expressions.get(82));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(81), expressions.get(83));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.tenthAx("!" + impl, negA);
            expressions = output(curExpr, expressions);
            curExpr = Axioms.firstAx(expressions.get(85), negB);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(85), expressions.get(86));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, "!" + impl, "(" + expressions.get(67) + " -> " + negA + ")");
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(84), expressions.get(88));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(87), expressions.get(89));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.secondAx(negB, expressions.get(67), negA);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(69), expressions.get(91));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(90), expressions.get(92));
            expressions = output(curExpr, expressions);
            curExpr = Axioms.ninthAx(negB, negA);
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(93), expressions.get(94));
            expressions = output(curExpr, expressions);
            curExpr = ModusPonens(expressions.get(33), expressions.get(95));
            output(curExpr, expressions);
        }

        private String ModusPonens(String a, String result) {
            String musor = "(" +a+" -> ";
            String toReturn;
            if (result.startsWith(musor)) {
                toReturn = result.substring(a.length() + 5, result.length() - 1);
            } else {
                toReturn = result;
            }
            return toReturn;
        }

        private boolean isModusPonens(String expr, int index){
            for(int i =0;i<index;i++){
                String assumption = "("+expressions.get(i)+" -> "+expr+")";
                if(hash.containsValue(assumption)){
                    templateModusPonens(expressions.get(i),expr);
                    return true;
                }
            }
            return false;
        }

        private boolean isTenthAx(String str) {
            if (str.startsWith("(!!")) {
                String newLine = str.substring(3, str.length() - 1);
                String[] elements = newLine.split(" -> ");
                if (elements.length % 2 == 0) {
                    int lastIndex = elements.length / 2;
                    StringBuilder left = new StringBuilder(elements[0]);
                    StringBuilder right = new StringBuilder(elements[lastIndex]);
                    for (int i = 1; i < lastIndex; i++) {
                        left.append(" -> ").append(elements[i]);
                    }
                    for (int i = lastIndex + 1; i < elements.length; i++) {
                        right.append(" -> ").append(elements[i]);
                    }
                    setTenthV(left.toString());
                    return ((left.toString()).equals(right.toString()));
                }
                return false;
            }
            return false;
        }

        private void setTenthV(String tenth) {
            this.tenth = tenth;
        }

        private String getTenthV() {
            return tenth;
        }
    }
}
