

import tree.*;
import java.util.stream.Collectors;
import java.util.List;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PROVES=1, COMMA=2, IMPL=3, OR=4, AND=5, NOT=6, LB=7, RB=8, VAR=9, WS=10;
	public static final int
		RULE_expression = 0, RULE_hypotheses = 1, RULE_condition = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"expression", "hypotheses", "condition"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|-'", "','", "'->'", "'|'", "'&'", "'!'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PROVES", "COMMA", "IMPL", "OR", "AND", "NOT", "LB", "RB", "VAR", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionContext exp1;
		public Token VAR;
		public ExpressionContext exp2;
		public TerminalNode VAR() { return getToken(ParserParser.VAR, 0); }
		public TerminalNode LB() { return getToken(ParserParser.LB, 0); }
		public TerminalNode RB() { return getToken(ParserParser.RB, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NOT() { return getToken(ParserParser.NOT, 0); }
		public TerminalNode AND() { return getToken(ParserParser.AND, 0); }
		public TerminalNode OR() { return getToken(ParserParser.OR, 0); }
		public TerminalNode IMPL() { return getToken(ParserParser.IMPL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParserVisitor ) return ((ParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(7);
				((ExpressionContext)_localctx).VAR = match(VAR);
				((ExpressionContext)_localctx).expr = new Variable((((ExpressionContext)_localctx).VAR!=null?((ExpressionContext)_localctx).VAR.getText():null));
				}
				break;
			case LB:
				{
				setState(9);
				match(LB);
				setState(10);
				((ExpressionContext)_localctx).exp1 = expression(0);
				setState(11);
				match(RB);
				((ExpressionContext)_localctx).expr = ((ExpressionContext)_localctx).exp1.expr;
				}
				break;
			case NOT:
				{
				setState(14);
				match(NOT);
				setState(15);
				((ExpressionContext)_localctx).exp1 = expression(4);
				((ExpressionContext)_localctx).expr = new Negation(((ExpressionContext)_localctx).exp1.expr);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(37);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(35);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(20);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(21);
						match(AND);
						setState(22);
						((ExpressionContext)_localctx).exp2 = expression(4);
						((ExpressionContext)_localctx).expr = new Conjunction(((ExpressionContext)_localctx).exp1.expr,((ExpressionContext)_localctx).exp2.expr);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(25);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(26);
						match(OR);
						setState(27);
						((ExpressionContext)_localctx).exp2 = expression(3);
						((ExpressionContext)_localctx).expr = new Disjunction(((ExpressionContext)_localctx).exp1.expr,((ExpressionContext)_localctx).exp2.expr);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(30);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(31);
						match(IMPL);
						setState(32);
						((ExpressionContext)_localctx).exp2 = expression(1);
						((ExpressionContext)_localctx).expr =  new Implication(((ExpressionContext)_localctx).exp1.expr,((ExpressionContext)_localctx).exp2.expr);
						}
						break;
					}
					} 
				}
				setState(39);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class HypothesesContext extends ParserRuleContext {
		public List<Expression> hyps;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ParserParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ParserParser.COMMA, i);
		}
		public HypothesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hypotheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).enterHypotheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).exitHypotheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParserVisitor ) return ((ParserVisitor<? extends T>)visitor).visitHypotheses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HypothesesContext hypotheses() throws RecognitionException {
		HypothesesContext _localctx = new HypothesesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_hypotheses);
		int _la;
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case LB:
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				expression(0);
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(41);
					match(COMMA);
					setState(42);
					expression(0);
					}
					}
					setState(47);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}

				    ((HypothesesContext)_localctx).hyps =  _localctx.expression().stream().map(expression->expression.expr).collect(Collectors.toList());

				}
				break;
			case PROVES:
				enterOuterAlt(_localctx, 2);
				{
				((HypothesesContext)_localctx).hyps =  new ArrayList<Expression>();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public Condition cond;
		public HypothesesContext hyp;
		public ExpressionContext exp;
		public TerminalNode PROVES() { return getToken(ParserParser.PROVES, 0); }
		public HypothesesContext hypotheses() {
			return getRuleContext(HypothesesContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ParserListener ) ((ParserListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ParserVisitor ) return ((ParserVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_condition);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				((ConditionContext)_localctx).hyp = hypotheses();
				setState(54);
				match(PROVES);
				setState(55);
				((ConditionContext)_localctx).exp = expression(0);
				((ConditionContext)_localctx).cond = new Condition(((ConditionContext)_localctx).exp.expr,((ConditionContext)_localctx).hyp.hyps);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				match(PROVES);
				setState(59);
				((ConditionContext)_localctx).exp = expression(0);
				((ConditionContext)_localctx).cond = new Condition(((ConditionContext)_localctx).exp.expr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\fC\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\25\n\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2&\n\2"+
		"\f\2\16\2)\13\2\3\3\3\3\3\3\7\3.\n\3\f\3\16\3\61\13\3\3\3\3\3\3\3\5\3"+
		"\66\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\3\4\2\3\2\5\2\4\6"+
		"\2\2\2G\2\24\3\2\2\2\4\65\3\2\2\2\6@\3\2\2\2\b\t\b\2\1\2\t\n\7\13\2\2"+
		"\n\25\b\2\1\2\13\f\7\t\2\2\f\r\5\2\2\2\r\16\7\n\2\2\16\17\b\2\1\2\17\25"+
		"\3\2\2\2\20\21\7\b\2\2\21\22\5\2\2\6\22\23\b\2\1\2\23\25\3\2\2\2\24\b"+
		"\3\2\2\2\24\13\3\2\2\2\24\20\3\2\2\2\25\'\3\2\2\2\26\27\f\5\2\2\27\30"+
		"\7\7\2\2\30\31\5\2\2\6\31\32\b\2\1\2\32&\3\2\2\2\33\34\f\4\2\2\34\35\7"+
		"\6\2\2\35\36\5\2\2\5\36\37\b\2\1\2\37&\3\2\2\2 !\f\3\2\2!\"\7\5\2\2\""+
		"#\5\2\2\3#$\b\2\1\2$&\3\2\2\2%\26\3\2\2\2%\33\3\2\2\2% \3\2\2\2&)\3\2"+
		"\2\2\'%\3\2\2\2\'(\3\2\2\2(\3\3\2\2\2)\'\3\2\2\2*/\5\2\2\2+,\7\4\2\2,"+
		".\5\2\2\2-+\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61"+
		"/\3\2\2\2\62\63\b\3\1\2\63\66\3\2\2\2\64\66\b\3\1\2\65*\3\2\2\2\65\64"+
		"\3\2\2\2\66\5\3\2\2\2\678\5\4\3\289\7\3\2\29:\5\2\2\2:;\b\4\1\2;A\3\2"+
		"\2\2<=\7\3\2\2=>\5\2\2\2>?\b\4\1\2?A\3\2\2\2@\67\3\2\2\2@<\3\2\2\2A\7"+
		"\3\2\2\2\b\24%\'/\65@";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}