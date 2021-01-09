grammar Parser;
@header {
import tree.*;
}
expression returns[Expression expr] :VAR{$expr=new Variable($VAR.text);}
                                    | LB exp1=expression RB{$expr=$exp1.expr;}
                                    | NOT exp1=expression{$expr=new Negation($exp1.expr);}
                                    | exp1=expression AND exp2=expression{$expr=new Conjunction($exp1.expr,$exp2.expr);}
                                    | exp1=expression OR exp2=expression{$expr=new Disjunction($exp1.expr,$exp2.expr);}
                                    | <assoc=right> exp1=expression IMPL exp2=expression{$expr = new Implication($exp1.expr,$exp2.expr);} ;


hypotheses returns[List<Expression> hyps]: exp=expression{$hyps=new ArrayList();$hyps.add($exp.expr);}
                                          | hyp=hypotheses COMMA exp=expression{$hyp.hyps.add($exp.expr);$hyps=$hyp.hyps;};

condition returns[Condition cond] : hyp=hypotheses PROVES exp=expression{$cond=new Condition($exp.expr,$hyp.hyps);}
           | PROVES exp=expression{$cond=new Condition($exp.expr);};

treep returns[String treed] : VAR{$treed = $VAR.text;}
      | LB AND COMMA t1=treep COMMA t2=treep RB{$treed = "(&,"+$t1.treed+","+$t2.treed+")";}
      | LB OR COMMA t1=treep COMMA t2=treep RB{$treed = "(|,"+$t1.treed+","+$t2.treed+")";}
      | LB IMPL COMMA t1=treep COMMA t2=treep RB{$treed = "(->,"+$t1.treed+","+$t2.treed+")";}
      | LB NOT t=treep RB{$treed = "(!"+$t.treed+")";};


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

