grammar Parser;

@header {
import tree.*;
}


expression returns[Expression expr] :VAR{$expr= new Variable($VAR.text);}
                                    | LB exp1=expression RB{$expr=$exp1.expr;}
                                    | NOT exp1=expression{$expr=new Negation($exp1.expr);}
                                    | exp1=expression AND exp2=expression{$expr=new Conjunction($exp1.expr,$exp2.expr);}
                                    | exp1=expression OR exp2=expression{$expr=new Disjunction($exp1.expr,$exp2.expr);}
                                    | <assoc=right> exp1=expression IMPL exp2=expression{$expr = new Implication($exp1.expr,$exp2.expr);} ;

IMPL : '->';
OR : '|';
AND : '&';
NOT : '!';
LB : '(';
RB : ')';
VAR : [A-Z]([A-Z0-9'])*;
WS: [\t\r] -> skip;

