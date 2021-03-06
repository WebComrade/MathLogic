grammar Parser;
@header {
import tree.*;
import java.util.stream.Collectors;
import java.util.List;
}
expression returns[Expression expr] : NOT exp1=expression{$expr=new Negation($exp1.expr);}
                                    | exp1=expression AND exp2=expression{$expr=new Conjunction($exp1.expr,$exp2.expr);}
                                    | exp1=expression OR exp2=expression{$expr=new Disjunction($exp1.expr,$exp2.expr);}
                                    | <assoc=right> exp1=expression IMPL exp2=expression{$expr = new Implication($exp1.expr,$exp2.expr);}
                                    | LB exp1=expression RB{$expr=$exp1.expr;}
                                    | VAR{$expr=new Variable($VAR.text);};



hypotheses returns[List<Expression> hyps]: expression (COMMA expression)*{
    $hyps = $hyps.expression().stream().map(expression->expression.expr).collect(Collectors.toList());
}
|{$hyps = new ArrayList<Expression>();};
condition returns[Condition cond] : hyp=hypotheses PROVES exp=expression{$cond=new Condition($exp.expr,$hyp.hyps);}
           | PROVES exp=expression{$cond=new Condition($exp.expr);};


PROVES : '|-';
COMMA : ',';
IMPL : '->';
OR : '|';
AND : '&';
NOT : '!';
LB : '(';
RB : ')';
VAR : [A-Z]([A-Z0-9'])*;
WS: [' '\t\r] -> skip;

