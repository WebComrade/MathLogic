

import tree.*;
import java.util.stream.Collectors;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ParserParser}.
 */
public interface ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ParserParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ParserParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#hypotheses}.
	 * @param ctx the parse tree
	 */
	void enterHypotheses(ParserParser.HypothesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#hypotheses}.
	 * @param ctx the parse tree
	 */
	void exitHypotheses(ParserParser.HypothesesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ParserParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ParserParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ParserParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ParserParser.ConditionContext ctx);
}