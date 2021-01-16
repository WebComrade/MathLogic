

import tree.*;
import java.util.stream.Collectors;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ParserParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ParserParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#hypotheses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHypotheses(ParserParser.HypothesesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ParserParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ParserParser.ConditionContext ctx);
}