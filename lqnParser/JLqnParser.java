// Generated from /Users/emilio-imt/eclipse-workspace/jLQN/src/main/resources/grammar/JLqn.g by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JLqnParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, RULE_SL_COMMENT=68, RULE_ENDLIST=69, RULE_REAL=70, RULE_NAT=71, 
		RULE_ZERO=72, RULE_ID=73, RULE_STRING=74, RULE_ML_COMMENT=75, RULE_WS=76, 
		RULE_ANY_OTHER=77;
	public static final int
		RULE_ruleModel = 0, RULE_ruleNUMBER = 1, RULE_rulePOSITIVEINTORREAL = 2, 
		RULE_ruleNAT0 = 3, RULE_ruleProcessor = 4, RULE_ruleTask = 5, RULE_ruleTaskId = 6, 
		RULE_ruleEntry = 7, RULE_ruleEntryDemand = 8, RULE_ruleSynchronousCall = 9, 
		RULE_ruleAsynchronousCall = 10, RULE_ruleActivityDiagram = 11, RULE_ruleEntryArrivalrate = 12, 
		RULE_ruleEntryCoeffs = 13, RULE_ruleEntryPhases = 14, RULE_ruleFanInEntry = 15, 
		RULE_ruleFanOutEntry = 16, RULE_ruleEntryPriority = 17, RULE_ruleEntryTimeLimit = 18, 
		RULE_ruleForwardEntry = 19, RULE_ruleEntryThinkTime = 20, RULE_ruleActivity = 21, 
		RULE_ruleActivityInfo = 22, RULE_ruleActConnections = 23, RULE_ruleActivityDemand = 24, 
		RULE_ruleActSyncCall = 25, RULE_ruleActivityCoeff = 26, RULE_ruleActivityPhase = 27, 
		RULE_ruleFanInAct = 28, RULE_ruleFanOutAct = 29, RULE_ruleActTimeLimit = 30, 
		RULE_ruleActAsyncCall = 31, RULE_ruleActThinkTime = 32, RULE_ruleTransition = 33, 
		RULE_ruleJoinList = 34, RULE_ruleListEntry = 35, RULE_ruleReply = 36, 
		RULE_ruleAndRepl = 37, RULE_ruleOrRepl = 38, RULE_ruleAndJoin = 39, RULE_ruleOrJoin = 40, 
		RULE_ruleForkList = 41, RULE_ruleAndFork = 42, RULE_ruleAndAct = 43, RULE_ruleOrFork = 44, 
		RULE_ruleOrAct = 45, RULE_ruleLoop = 46, RULE_ruleLoopAct = 47, RULE_ruleInfinityRate = 48, 
		RULE_ruleSolver = 49, RULE_ruleSolVar = 50, RULE_ruleSolSetting = 51, 
		RULE_ruleOutputReq = 52, RULE_ruleExportSetting = 53, RULE_ruleExportKind = 54, 
		RULE_ruleSolverKind = 55, RULE_ruleSolVarKind = 56, RULE_ruleSolBoolKind = 57, 
		RULE_ruleTaskKind = 58, RULE_ruleScheduleKind = 59;
	private static String[] makeRuleNames() {
		return new String[] {
			"ruleModel", "ruleNUMBER", "rulePOSITIVEINTORREAL", "ruleNAT0", "ruleProcessor", 
			"ruleTask", "ruleTaskId", "ruleEntry", "ruleEntryDemand", "ruleSynchronousCall", 
			"ruleAsynchronousCall", "ruleActivityDiagram", "ruleEntryArrivalrate", 
			"ruleEntryCoeffs", "ruleEntryPhases", "ruleFanInEntry", "ruleFanOutEntry", 
			"ruleEntryPriority", "ruleEntryTimeLimit", "ruleForwardEntry", "ruleEntryThinkTime", 
			"ruleActivity", "ruleActivityInfo", "ruleActConnections", "ruleActivityDemand", 
			"ruleActSyncCall", "ruleActivityCoeff", "ruleActivityPhase", "ruleFanInAct", 
			"ruleFanOutAct", "ruleActTimeLimit", "ruleActAsyncCall", "ruleActThinkTime", 
			"ruleTransition", "ruleJoinList", "ruleListEntry", "ruleReply", "ruleAndRepl", 
			"ruleOrRepl", "ruleAndJoin", "ruleOrJoin", "ruleForkList", "ruleAndFork", 
			"ruleAndAct", "ruleOrFork", "ruleOrAct", "ruleLoop", "ruleLoopAct", "ruleInfinityRate", 
			"ruleSolver", "ruleSolVar", "ruleSolSetting", "ruleOutputReq", "ruleExportSetting", 
			"ruleExportKind", "ruleSolverKind", "ruleSolVarKind", "ruleSolBoolKind", 
			"ruleTaskKind", "ruleScheduleKind"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'G'", "'P'", "'T'", "'E'", "'p'", "'m'", "'i'", "'r'", "'R'", 
			"'t'", "'q'", "'z'", "'s'", "'y'", "'A'", "'a'", "'c'", "'f'", "'o'", 
			"'M'", "'F'", "'Z'", "':'", "'->'", "';'", "','", "'['", "']'", "'&'", 
			"'+'", "'('", "')'", "'*'", "'#!'", "'infinity rate'", "'v'", "'solver'", 
			"'simple'", "'throughput:'", "'utilisation:'", "'utilization:'", "'responsetime:'", 
			"'response time:'", "'export'", "'path'", "'pepa'", "'matlab'", "'java_ode'", 
			"'csv'", "'result_list'", "'sim'", "'old_ode'", "'ode'", "'stoptime'", 
			"'solver_abs_tol'", "'solver_rel_tol'", "'steady_abs_tol'", "'steady_rel_tol'", 
			"'batch_length_factor'", "'confidence_level'", "'confidence_percent_error'", 
			"'relative steady state'", "'absolute steady state'", "'L1-norm steady state'", 
			"'n'", "'h'", "'b'", null, "'-1'", null, null, "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "RULE_SL_COMMENT", "RULE_ENDLIST", 
			"RULE_REAL", "RULE_NAT", "RULE_ZERO", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", 
			"RULE_WS", "RULE_ANY_OTHER"
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
	public String getGrammarFileName() { return "JLqn.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JLqnParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RuleModelContext extends ParserRuleContext {
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public List<TerminalNode> RULE_ENDLIST() { return getTokens(JLqnParser.RULE_ENDLIST); }
		public TerminalNode RULE_ENDLIST(int i) {
			return getToken(JLqnParser.RULE_ENDLIST, i);
		}
		public TerminalNode RULE_STRING() { return getToken(JLqnParser.RULE_STRING, 0); }
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public List<RuleProcessorContext> ruleProcessor() {
			return getRuleContexts(RuleProcessorContext.class);
		}
		public RuleProcessorContext ruleProcessor(int i) {
			return getRuleContext(RuleProcessorContext.class,i);
		}
		public List<RuleTaskContext> ruleTask() {
			return getRuleContexts(RuleTaskContext.class);
		}
		public RuleTaskContext ruleTask(int i) {
			return getRuleContext(RuleTaskContext.class,i);
		}
		public List<RuleEntryDemandContext> ruleEntryDemand() {
			return getRuleContexts(RuleEntryDemandContext.class);
		}
		public RuleEntryDemandContext ruleEntryDemand(int i) {
			return getRuleContext(RuleEntryDemandContext.class,i);
		}
		public List<RuleSynchronousCallContext> ruleSynchronousCall() {
			return getRuleContexts(RuleSynchronousCallContext.class);
		}
		public RuleSynchronousCallContext ruleSynchronousCall(int i) {
			return getRuleContext(RuleSynchronousCallContext.class,i);
		}
		public List<RuleAsynchronousCallContext> ruleAsynchronousCall() {
			return getRuleContexts(RuleAsynchronousCallContext.class);
		}
		public RuleAsynchronousCallContext ruleAsynchronousCall(int i) {
			return getRuleContext(RuleAsynchronousCallContext.class,i);
		}
		public List<RuleActivityDiagramContext> ruleActivityDiagram() {
			return getRuleContexts(RuleActivityDiagramContext.class);
		}
		public RuleActivityDiagramContext ruleActivityDiagram(int i) {
			return getRuleContext(RuleActivityDiagramContext.class,i);
		}
		public List<RuleEntryArrivalrateContext> ruleEntryArrivalrate() {
			return getRuleContexts(RuleEntryArrivalrateContext.class);
		}
		public RuleEntryArrivalrateContext ruleEntryArrivalrate(int i) {
			return getRuleContext(RuleEntryArrivalrateContext.class,i);
		}
		public List<RuleEntryCoeffsContext> ruleEntryCoeffs() {
			return getRuleContexts(RuleEntryCoeffsContext.class);
		}
		public RuleEntryCoeffsContext ruleEntryCoeffs(int i) {
			return getRuleContext(RuleEntryCoeffsContext.class,i);
		}
		public List<RuleEntryPhasesContext> ruleEntryPhases() {
			return getRuleContexts(RuleEntryPhasesContext.class);
		}
		public RuleEntryPhasesContext ruleEntryPhases(int i) {
			return getRuleContext(RuleEntryPhasesContext.class,i);
		}
		public List<RuleFanInEntryContext> ruleFanInEntry() {
			return getRuleContexts(RuleFanInEntryContext.class);
		}
		public RuleFanInEntryContext ruleFanInEntry(int i) {
			return getRuleContext(RuleFanInEntryContext.class,i);
		}
		public List<RuleFanOutEntryContext> ruleFanOutEntry() {
			return getRuleContexts(RuleFanOutEntryContext.class);
		}
		public RuleFanOutEntryContext ruleFanOutEntry(int i) {
			return getRuleContext(RuleFanOutEntryContext.class,i);
		}
		public List<RuleEntryPriorityContext> ruleEntryPriority() {
			return getRuleContexts(RuleEntryPriorityContext.class);
		}
		public RuleEntryPriorityContext ruleEntryPriority(int i) {
			return getRuleContext(RuleEntryPriorityContext.class,i);
		}
		public List<RuleEntryTimeLimitContext> ruleEntryTimeLimit() {
			return getRuleContexts(RuleEntryTimeLimitContext.class);
		}
		public RuleEntryTimeLimitContext ruleEntryTimeLimit(int i) {
			return getRuleContext(RuleEntryTimeLimitContext.class,i);
		}
		public List<RuleForwardEntryContext> ruleForwardEntry() {
			return getRuleContexts(RuleForwardEntryContext.class);
		}
		public RuleForwardEntryContext ruleForwardEntry(int i) {
			return getRuleContext(RuleForwardEntryContext.class,i);
		}
		public List<RuleEntryThinkTimeContext> ruleEntryThinkTime() {
			return getRuleContexts(RuleEntryThinkTimeContext.class);
		}
		public RuleEntryThinkTimeContext ruleEntryThinkTime(int i) {
			return getRuleContext(RuleEntryThinkTimeContext.class,i);
		}
		public List<RuleActivityInfoContext> ruleActivityInfo() {
			return getRuleContexts(RuleActivityInfoContext.class);
		}
		public RuleActivityInfoContext ruleActivityInfo(int i) {
			return getRuleContext(RuleActivityInfoContext.class,i);
		}
		public RuleInfinityRateContext ruleInfinityRate() {
			return getRuleContext(RuleInfinityRateContext.class,0);
		}
		public RuleSolverContext ruleSolver() {
			return getRuleContext(RuleSolverContext.class,0);
		}
		public List<RuleSolVarContext> ruleSolVar() {
			return getRuleContexts(RuleSolVarContext.class);
		}
		public RuleSolVarContext ruleSolVar(int i) {
			return getRuleContext(RuleSolVarContext.class,i);
		}
		public List<RuleSolSettingContext> ruleSolSetting() {
			return getRuleContexts(RuleSolSettingContext.class);
		}
		public RuleSolSettingContext ruleSolSetting(int i) {
			return getRuleContext(RuleSolSettingContext.class,i);
		}
		public RuleOutputReqContext ruleOutputReq() {
			return getRuleContext(RuleOutputReqContext.class,0);
		}
		public List<RuleExportSettingContext> ruleExportSetting() {
			return getRuleContexts(RuleExportSettingContext.class);
		}
		public RuleExportSettingContext ruleExportSetting(int i) {
			return getRuleContext(RuleExportSettingContext.class,i);
		}
		public TerminalNode RULE_REAL() { return getToken(JLqnParser.RULE_REAL, 0); }
		public RuleModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleModel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleModel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleModel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleModelContext ruleModel() throws RecognitionException {
		RuleModelContext _localctx = new RuleModelContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ruleModel);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(120);
				match(T__0);
				setState(121);
				match(RULE_STRING);
				setState(122);
				ruleNUMBER();
				setState(123);
				ruleNAT0();
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RULE_NAT || _la==RULE_ZERO) {
					{
					setState(124);
					ruleNAT0();
					}
				}

				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RULE_REAL) {
					{
					setState(127);
					match(RULE_REAL);
					}
				}

				setState(130);
				match(RULE_ENDLIST);
				}
			}

			setState(134);
			match(T__1);
			setState(135);
			ruleNAT0();
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(136);
				ruleProcessor();
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(142);
			match(RULE_ENDLIST);
			setState(143);
			match(T__2);
			setState(144);
			ruleNAT0();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(145);
				ruleTask();
				}
				}
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(151);
			match(RULE_ENDLIST);
			setState(152);
			match(T__3);
			setState(153);
			ruleNAT0();
			setState(167); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(167);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__12:
					{
					setState(154);
					ruleEntryDemand();
					}
					break;
				case T__13:
					{
					setState(155);
					ruleSynchronousCall();
					}
					break;
				case T__11:
					{
					setState(156);
					ruleAsynchronousCall();
					}
					break;
				case T__14:
					{
					setState(157);
					ruleActivityDiagram();
					}
					break;
				case T__15:
					{
					setState(158);
					ruleEntryArrivalrate();
					}
					break;
				case T__16:
					{
					setState(159);
					ruleEntryCoeffs();
					}
					break;
				case T__17:
					{
					setState(160);
					ruleEntryPhases();
					}
					break;
				case T__6:
					{
					setState(161);
					ruleFanInEntry();
					}
					break;
				case T__18:
					{
					setState(162);
					ruleFanOutEntry();
					}
					break;
				case T__4:
					{
					setState(163);
					ruleEntryPriority();
					}
					break;
				case T__19:
					{
					setState(164);
					ruleEntryTimeLimit();
					}
					break;
				case T__20:
					{
					setState(165);
					ruleForwardEntry();
					}
					break;
				case T__21:
					{
					setState(166);
					ruleEntryThinkTime();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(169); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8384672L) != 0) );
			setState(171);
			match(RULE_ENDLIST);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6221952L) != 0)) {
				{
				{
				setState(172);
				ruleActivityInfo();
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(178);
				ruleInfinityRate();
				}
				break;
			}
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(181);
				ruleSolver();
				}
				break;
			}
			setState(187);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(184);
					ruleSolVar();
					}
					} 
				}
				setState(189);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(190);
					ruleSolSetting();
					}
					} 
				}
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(196);
				ruleOutputReq();
				}
				break;
			}
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__33) {
				{
				{
				setState(199);
				ruleExportSetting();
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleNUMBERContext extends ParserRuleContext {
		public TerminalNode RULE_ZERO() { return getToken(JLqnParser.RULE_ZERO, 0); }
		public RulePOSITIVEINTORREALContext rulePOSITIVEINTORREAL() {
			return getRuleContext(RulePOSITIVEINTORREALContext.class,0);
		}
		public RuleNUMBERContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleNUMBER; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleNUMBER(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleNUMBER(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleNUMBER(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleNUMBERContext ruleNUMBER() throws RecognitionException {
		RuleNUMBERContext _localctx = new RuleNUMBERContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ruleNUMBER);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE_ZERO:
				{
				setState(205);
				match(RULE_ZERO);
				}
				break;
			case RULE_REAL:
			case RULE_NAT:
				{
				setState(206);
				rulePOSITIVEINTORREAL();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RulePOSITIVEINTORREALContext extends ParserRuleContext {
		public TerminalNode RULE_NAT() { return getToken(JLqnParser.RULE_NAT, 0); }
		public TerminalNode RULE_REAL() { return getToken(JLqnParser.RULE_REAL, 0); }
		public RulePOSITIVEINTORREALContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rulePOSITIVEINTORREAL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRulePOSITIVEINTORREAL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRulePOSITIVEINTORREAL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRulePOSITIVEINTORREAL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulePOSITIVEINTORREALContext rulePOSITIVEINTORREAL() throws RecognitionException {
		RulePOSITIVEINTORREALContext _localctx = new RulePOSITIVEINTORREALContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rulePOSITIVEINTORREAL);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_la = _input.LA(1);
			if ( !(_la==RULE_REAL || _la==RULE_NAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleNAT0Context extends ParserRuleContext {
		public TerminalNode RULE_NAT() { return getToken(JLqnParser.RULE_NAT, 0); }
		public TerminalNode RULE_ZERO() { return getToken(JLqnParser.RULE_ZERO, 0); }
		public RuleNAT0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleNAT0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleNAT0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleNAT0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleNAT0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleNAT0Context ruleNAT0() throws RecognitionException {
		RuleNAT0Context _localctx = new RuleNAT0Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_ruleNAT0);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_la = _input.LA(1);
			if ( !(_la==RULE_NAT || _la==RULE_ZERO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleProcessorContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleScheduleKindContext ruleScheduleKind() {
			return getRuleContext(RuleScheduleKindContext.class,0);
		}
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public RuleProcessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleProcessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleProcessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleProcessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleProcessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleProcessorContext ruleProcessor() throws RecognitionException {
		RuleProcessorContext _localctx = new RuleProcessorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ruleProcessor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(T__4);
			setState(214);
			match(RULE_ID);
			setState(215);
			ruleScheduleKind();
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0)) {
				{
				setState(216);
				ruleNUMBER();
				}
			}

			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(219);
				match(T__5);
				setState(220);
				ruleNAT0();
				}
				break;
			case T__6:
				{
				setState(221);
				match(T__6);
				}
				break;
			case T__4:
			case T__7:
			case T__8:
			case RULE_ENDLIST:
				break;
			default:
				break;
			}
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(224);
				match(T__7);
				setState(225);
				ruleNAT0();
				}
			}

			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(228);
				match(T__8);
				setState(229);
				ruleNUMBER();
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleTaskContext extends ParserRuleContext {
		public RuleTaskIdContext ruleTaskId() {
			return getRuleContext(RuleTaskIdContext.class,0);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public RuleTaskKindContext ruleTaskKind() {
			return getRuleContext(RuleTaskKindContext.class,0);
		}
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleTaskContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleTask; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleTask(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleTask(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleTask(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleTaskContext ruleTask() throws RecognitionException {
		RuleTaskContext _localctx = new RuleTaskContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ruleTask);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__9);
			setState(233);
			ruleTaskId();
			setState(265);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				{
				setState(234);
				match(T__6);
				setState(236); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(235);
					match(RULE_ID);
					}
					}
					setState(238); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==RULE_ID );
				setState(240);
				match(RULE_ENDLIST);
				}
				break;
			case T__4:
			case T__7:
			case T__17:
			case T__64:
			case T__65:
			case T__66:
				{
				setState(241);
				ruleTaskKind();
				setState(243); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(242);
					match(RULE_ID);
					}
					}
					setState(245); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==RULE_ID );
				setState(247);
				match(RULE_ENDLIST);
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(248);
					match(T__10);
					setState(249);
					ruleNAT0();
					}
				}

				setState(252);
				match(RULE_ID);
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==RULE_NAT || _la==RULE_ZERO) {
					{
					setState(253);
					ruleNAT0();
					}
				}

				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(256);
					match(T__11);
					setState(257);
					ruleNUMBER();
					}
				}

				setState(263);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(260);
					match(T__5);
					setState(261);
					ruleNAT0();
					}
					break;
				case T__6:
					{
					setState(262);
					match(T__6);
					}
					break;
				case T__7:
				case T__9:
				case RULE_ENDLIST:
					break;
				default:
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(267);
				match(T__7);
				setState(268);
				ruleNAT0();
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleTaskIdContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleTaskIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleTaskId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleTaskId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleTaskId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleTaskId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleTaskIdContext ruleTaskId() throws RecognitionException {
		RuleTaskIdContext _localctx = new RuleTaskIdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ruleTaskId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryContext ruleEntry() throws RecognitionException {
		RuleEntryContext _localctx = new RuleEntryContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ruleEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryDemandContext extends ParserRuleContext {
		public RuleEntryContext ruleEntry() {
			return getRuleContext(RuleEntryContext.class,0);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleEntryDemandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryDemand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryDemand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryDemand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryDemand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryDemandContext ruleEntryDemand() throws RecognitionException {
		RuleEntryDemandContext _localctx = new RuleEntryDemandContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ruleEntryDemand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(T__12);
			setState(276);
			ruleEntry();
			setState(278); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(277);
				ruleNUMBER();
				}
				}
				setState(280); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(282);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSynchronousCallContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleSynchronousCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSynchronousCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSynchronousCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSynchronousCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSynchronousCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSynchronousCallContext ruleSynchronousCall() throws RecognitionException {
		RuleSynchronousCallContext _localctx = new RuleSynchronousCallContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ruleSynchronousCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__13);
			setState(285);
			match(RULE_ID);
			setState(286);
			match(RULE_ID);
			setState(288); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(287);
				ruleNUMBER();
				}
				}
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(292);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAsynchronousCallContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleAsynchronousCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAsynchronousCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleAsynchronousCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleAsynchronousCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleAsynchronousCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleAsynchronousCallContext ruleAsynchronousCall() throws RecognitionException {
		RuleAsynchronousCallContext _localctx = new RuleAsynchronousCallContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ruleAsynchronousCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(T__11);
			setState(295);
			match(RULE_ID);
			setState(296);
			match(RULE_ID);
			setState(298); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(297);
				ruleNUMBER();
				}
				}
				setState(300); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(302);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityDiagramContext extends ParserRuleContext {
		public RuleEntryContext ruleEntry() {
			return getRuleContext(RuleEntryContext.class,0);
		}
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleActivityDiagramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivityDiagram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivityDiagram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivityDiagram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivityDiagram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityDiagramContext ruleActivityDiagram() throws RecognitionException {
		RuleActivityDiagramContext _localctx = new RuleActivityDiagramContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ruleActivityDiagram);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__14);
			setState(305);
			ruleEntry();
			setState(306);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryArrivalrateContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleEntryArrivalrateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryArrivalrate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryArrivalrate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryArrivalrate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryArrivalrate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryArrivalrateContext ruleEntryArrivalrate() throws RecognitionException {
		RuleEntryArrivalrateContext _localctx = new RuleEntryArrivalrateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ruleEntryArrivalrate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(T__15);
			setState(309);
			match(RULE_ID);
			setState(310);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryCoeffsContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleEntryCoeffsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryCoeffs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryCoeffs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryCoeffs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryCoeffs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryCoeffsContext ruleEntryCoeffs() throws RecognitionException {
		RuleEntryCoeffsContext _localctx = new RuleEntryCoeffsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ruleEntryCoeffs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(T__16);
			setState(313);
			match(RULE_ID);
			setState(315); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(314);
				ruleNUMBER();
				}
				}
				setState(317); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(319);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryPhasesContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public RuleEntryPhasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryPhases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryPhases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryPhases(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryPhases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryPhasesContext ruleEntryPhases() throws RecognitionException {
		RuleEntryPhasesContext _localctx = new RuleEntryPhasesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ruleEntryPhases);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(T__17);
			setState(322);
			match(RULE_ID);
			setState(324); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(323);
				ruleNAT0();
				}
				}
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RULE_NAT || _la==RULE_ZERO );
			setState(328);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleFanInEntryContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public RuleFanInEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFanInEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleFanInEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleFanInEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleFanInEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleFanInEntryContext ruleFanInEntry() throws RecognitionException {
		RuleFanInEntryContext _localctx = new RuleFanInEntryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ruleFanInEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(T__6);
			setState(331);
			match(RULE_ID);
			setState(332);
			match(RULE_ID);
			setState(334); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(333);
				ruleNAT0();
				}
				}
				setState(336); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RULE_NAT || _la==RULE_ZERO );
			setState(338);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleFanOutEntryContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNAT0Context> ruleNAT0() {
			return getRuleContexts(RuleNAT0Context.class);
		}
		public RuleNAT0Context ruleNAT0(int i) {
			return getRuleContext(RuleNAT0Context.class,i);
		}
		public RuleFanOutEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFanOutEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleFanOutEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleFanOutEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleFanOutEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleFanOutEntryContext ruleFanOutEntry() throws RecognitionException {
		RuleFanOutEntryContext _localctx = new RuleFanOutEntryContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_ruleFanOutEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(T__18);
			setState(341);
			match(RULE_ID);
			setState(342);
			match(RULE_ID);
			setState(344); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(343);
				ruleNAT0();
				}
				}
				setState(346); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RULE_NAT || _la==RULE_ZERO );
			setState(348);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryPriorityContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNAT0Context ruleNAT0() {
			return getRuleContext(RuleNAT0Context.class,0);
		}
		public RuleEntryPriorityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryPriority; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryPriority(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryPriority(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryPriority(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryPriorityContext ruleEntryPriority() throws RecognitionException {
		RuleEntryPriorityContext _localctx = new RuleEntryPriorityContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ruleEntryPriority);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__4);
			setState(351);
			match(RULE_ID);
			setState(352);
			ruleNAT0();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryTimeLimitContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleEntryTimeLimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryTimeLimit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryTimeLimit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryTimeLimit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryTimeLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryTimeLimitContext ruleEntryTimeLimit() throws RecognitionException {
		RuleEntryTimeLimitContext _localctx = new RuleEntryTimeLimitContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ruleEntryTimeLimit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(T__19);
			setState(355);
			match(RULE_ID);
			setState(357); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(356);
				ruleNUMBER();
				}
				}
				setState(359); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(361);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleForwardEntryContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleForwardEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleForwardEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleForwardEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleForwardEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleForwardEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleForwardEntryContext ruleForwardEntry() throws RecognitionException {
		RuleForwardEntryContext _localctx = new RuleForwardEntryContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_ruleForwardEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(T__20);
			setState(364);
			match(RULE_ID);
			setState(365);
			match(RULE_ID);
			setState(366);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleEntryThinkTimeContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<RuleNUMBERContext> ruleNUMBER() {
			return getRuleContexts(RuleNUMBERContext.class);
		}
		public RuleNUMBERContext ruleNUMBER(int i) {
			return getRuleContext(RuleNUMBERContext.class,i);
		}
		public RuleEntryThinkTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleEntryThinkTime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleEntryThinkTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleEntryThinkTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleEntryThinkTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleEntryThinkTimeContext ruleEntryThinkTime() throws RecognitionException {
		RuleEntryThinkTimeContext _localctx = new RuleEntryThinkTimeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ruleEntryThinkTime);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(T__21);
			setState(369);
			match(RULE_ID);
			setState(371); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(370);
				ruleNUMBER();
				}
				}
				setState(373); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 7L) != 0) );
			setState(375);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleActivityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityContext ruleActivity() throws RecognitionException {
		RuleActivityContext _localctx = new RuleActivityContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ruleActivity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityInfoContext extends ParserRuleContext {
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public List<RuleActivityDemandContext> ruleActivityDemand() {
			return getRuleContexts(RuleActivityDemandContext.class);
		}
		public RuleActivityDemandContext ruleActivityDemand(int i) {
			return getRuleContext(RuleActivityDemandContext.class,i);
		}
		public List<RuleActSyncCallContext> ruleActSyncCall() {
			return getRuleContexts(RuleActSyncCallContext.class);
		}
		public RuleActSyncCallContext ruleActSyncCall(int i) {
			return getRuleContext(RuleActSyncCallContext.class,i);
		}
		public List<RuleActivityCoeffContext> ruleActivityCoeff() {
			return getRuleContexts(RuleActivityCoeffContext.class);
		}
		public RuleActivityCoeffContext ruleActivityCoeff(int i) {
			return getRuleContext(RuleActivityCoeffContext.class,i);
		}
		public List<RuleActivityPhaseContext> ruleActivityPhase() {
			return getRuleContexts(RuleActivityPhaseContext.class);
		}
		public RuleActivityPhaseContext ruleActivityPhase(int i) {
			return getRuleContext(RuleActivityPhaseContext.class,i);
		}
		public List<RuleFanInActContext> ruleFanInAct() {
			return getRuleContexts(RuleFanInActContext.class);
		}
		public RuleFanInActContext ruleFanInAct(int i) {
			return getRuleContext(RuleFanInActContext.class,i);
		}
		public List<RuleFanOutActContext> ruleFanOutAct() {
			return getRuleContexts(RuleFanOutActContext.class);
		}
		public RuleFanOutActContext ruleFanOutAct(int i) {
			return getRuleContext(RuleFanOutActContext.class,i);
		}
		public List<RuleActTimeLimitContext> ruleActTimeLimit() {
			return getRuleContexts(RuleActTimeLimitContext.class);
		}
		public RuleActTimeLimitContext ruleActTimeLimit(int i) {
			return getRuleContext(RuleActTimeLimitContext.class,i);
		}
		public List<RuleActAsyncCallContext> ruleActAsyncCall() {
			return getRuleContexts(RuleActAsyncCallContext.class);
		}
		public RuleActAsyncCallContext ruleActAsyncCall(int i) {
			return getRuleContext(RuleActAsyncCallContext.class,i);
		}
		public List<RuleActThinkTimeContext> ruleActThinkTime() {
			return getRuleContexts(RuleActThinkTimeContext.class);
		}
		public RuleActThinkTimeContext ruleActThinkTime(int i) {
			return getRuleContext(RuleActThinkTimeContext.class,i);
		}
		public RuleActConnectionsContext ruleActConnections() {
			return getRuleContext(RuleActConnectionsContext.class,0);
		}
		public RuleActivityInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivityInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivityInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivityInfo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivityInfo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityInfoContext ruleActivityInfo() throws RecognitionException {
		RuleActivityInfoContext _localctx = new RuleActivityInfoContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ruleActivityInfo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(379);
				match(T__14);
				setState(380);
				match(RULE_ID);
				}
			}

			setState(392); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(392);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__12:
					{
					setState(383);
					ruleActivityDemand();
					}
					break;
				case T__13:
					{
					setState(384);
					ruleActSyncCall();
					}
					break;
				case T__16:
					{
					setState(385);
					ruleActivityCoeff();
					}
					break;
				case T__17:
					{
					setState(386);
					ruleActivityPhase();
					}
					break;
				case T__6:
					{
					setState(387);
					ruleFanInAct();
					}
					break;
				case T__18:
					{
					setState(388);
					ruleFanOutAct();
					}
					break;
				case T__19:
					{
					setState(389);
					ruleActTimeLimit();
					}
					break;
				case T__11:
					{
					setState(390);
					ruleActAsyncCall();
					}
					break;
				case T__21:
					{
					setState(391);
					ruleActThinkTime();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(394); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 6189184L) != 0) );
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(396);
				ruleActConnections();
				}
			}

			setState(399);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActConnectionsContext extends ParserRuleContext {
		public RuleJoinListContext ruleJoinList() {
			return getRuleContext(RuleJoinListContext.class,0);
		}
		public List<RuleTransitionContext> ruleTransition() {
			return getRuleContexts(RuleTransitionContext.class);
		}
		public RuleTransitionContext ruleTransition(int i) {
			return getRuleContext(RuleTransitionContext.class,i);
		}
		public RuleForkListContext ruleForkList() {
			return getRuleContext(RuleForkListContext.class,0);
		}
		public RuleActConnectionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActConnections; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActConnections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActConnections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActConnections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActConnectionsContext ruleActConnections() throws RecognitionException {
		RuleActConnectionsContext _localctx = new RuleActConnectionsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ruleActConnections);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			match(T__22);
			setState(405);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(402);
					ruleTransition();
					}
					} 
				}
				setState(407);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			setState(408);
			ruleJoinList();
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(409);
				match(T__23);
				setState(410);
				ruleForkList();
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityDemandContext extends ParserRuleContext {
		public RuleActivityContext ruleActivity() {
			return getRuleContext(RuleActivityContext.class,0);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActivityDemandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivityDemand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivityDemand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivityDemand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivityDemand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityDemandContext ruleActivityDemand() throws RecognitionException {
		RuleActivityDemandContext _localctx = new RuleActivityDemandContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_ruleActivityDemand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			match(T__12);
			setState(414);
			ruleActivity();
			setState(415);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActSyncCallContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActSyncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActSyncCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActSyncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActSyncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActSyncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActSyncCallContext ruleActSyncCall() throws RecognitionException {
		RuleActSyncCallContext _localctx = new RuleActSyncCallContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_ruleActSyncCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(T__13);
			setState(418);
			match(RULE_ID);
			setState(419);
			match(RULE_ID);
			setState(420);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityCoeffContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActivityCoeffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivityCoeff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivityCoeff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivityCoeff(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivityCoeff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityCoeffContext ruleActivityCoeff() throws RecognitionException {
		RuleActivityCoeffContext _localctx = new RuleActivityCoeffContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_ruleActivityCoeff);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			match(T__16);
			setState(423);
			match(RULE_ID);
			setState(424);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActivityPhaseContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNAT0Context ruleNAT0() {
			return getRuleContext(RuleNAT0Context.class,0);
		}
		public RuleActivityPhaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActivityPhase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActivityPhase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActivityPhase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActivityPhase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActivityPhaseContext ruleActivityPhase() throws RecognitionException {
		RuleActivityPhaseContext _localctx = new RuleActivityPhaseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_ruleActivityPhase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(T__17);
			setState(427);
			match(RULE_ID);
			setState(428);
			ruleNAT0();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleFanInActContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleNAT0Context ruleNAT0() {
			return getRuleContext(RuleNAT0Context.class,0);
		}
		public RuleFanInActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFanInAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleFanInAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleFanInAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleFanInAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleFanInActContext ruleFanInAct() throws RecognitionException {
		RuleFanInActContext _localctx = new RuleFanInActContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ruleFanInAct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(T__6);
			setState(431);
			match(RULE_ID);
			setState(432);
			match(RULE_ID);
			setState(433);
			ruleNAT0();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleFanOutActContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleNAT0Context ruleNAT0() {
			return getRuleContext(RuleNAT0Context.class,0);
		}
		public RuleFanOutActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleFanOutAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleFanOutAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleFanOutAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleFanOutAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleFanOutActContext ruleFanOutAct() throws RecognitionException {
		RuleFanOutActContext _localctx = new RuleFanOutActContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ruleFanOutAct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(T__18);
			setState(436);
			match(RULE_ID);
			setState(437);
			match(RULE_ID);
			setState(438);
			ruleNAT0();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActTimeLimitContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActTimeLimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActTimeLimit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActTimeLimit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActTimeLimit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActTimeLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActTimeLimitContext ruleActTimeLimit() throws RecognitionException {
		RuleActTimeLimitContext _localctx = new RuleActTimeLimitContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_ruleActTimeLimit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			match(T__19);
			setState(441);
			match(RULE_ID);
			setState(442);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActAsyncCallContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActAsyncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActAsyncCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActAsyncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActAsyncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActAsyncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActAsyncCallContext ruleActAsyncCall() throws RecognitionException {
		RuleActAsyncCallContext _localctx = new RuleActAsyncCallContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_ruleActAsyncCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(T__11);
			setState(445);
			match(RULE_ID);
			setState(446);
			match(RULE_ID);
			setState(447);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleActThinkTimeContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleActThinkTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleActThinkTime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleActThinkTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleActThinkTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleActThinkTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleActThinkTimeContext ruleActThinkTime() throws RecognitionException {
		RuleActThinkTimeContext _localctx = new RuleActThinkTimeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ruleActThinkTime);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			match(T__21);
			setState(450);
			match(RULE_ID);
			setState(451);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleTransitionContext extends ParserRuleContext {
		public RuleJoinListContext ruleJoinList() {
			return getRuleContext(RuleJoinListContext.class,0);
		}
		public RuleForkListContext ruleForkList() {
			return getRuleContext(RuleForkListContext.class,0);
		}
		public RuleTransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleTransition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleTransitionContext ruleTransition() throws RecognitionException {
		RuleTransitionContext _localctx = new RuleTransitionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ruleTransition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			ruleJoinList();
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(454);
				match(T__23);
				setState(455);
				ruleForkList();
				}
			}

			setState(458);
			match(T__24);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleJoinListContext extends ParserRuleContext {
		public RuleReplyContext ruleReply() {
			return getRuleContext(RuleReplyContext.class,0);
		}
		public RuleAndJoinContext ruleAndJoin() {
			return getRuleContext(RuleAndJoinContext.class,0);
		}
		public RuleOrJoinContext ruleOrJoin() {
			return getRuleContext(RuleOrJoinContext.class,0);
		}
		public RuleJoinListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleJoinList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleJoinList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleJoinList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleJoinList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleJoinListContext ruleJoinList() throws RecognitionException {
		RuleJoinListContext _localctx = new RuleJoinListContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ruleJoinList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(460);
				ruleReply();
				}
				break;
			case 2:
				{
				setState(461);
				ruleAndJoin();
				}
				break;
			case 3:
				{
				setState(462);
				ruleOrJoin();
				}
				break;
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleListEntryContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleListEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleListEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleListEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleListEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleListEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleListEntryContext ruleListEntry() throws RecognitionException {
		RuleListEntryContext _localctx = new RuleListEntryContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ruleListEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			match(T__25);
			setState(466);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleReplyContext extends ParserRuleContext {
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public List<RuleListEntryContext> ruleListEntry() {
			return getRuleContexts(RuleListEntryContext.class);
		}
		public RuleListEntryContext ruleListEntry(int i) {
			return getRuleContext(RuleListEntryContext.class,i);
		}
		public RuleReplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleReply; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleReply(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleReply(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleReply(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleReplyContext ruleReply() throws RecognitionException {
		RuleReplyContext _localctx = new RuleReplyContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_ruleReply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			match(RULE_ID);
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(469);
				match(T__26);
				setState(470);
				match(RULE_ID);
				setState(474);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__25) {
					{
					{
					setState(471);
					ruleListEntry();
					}
					}
					setState(476);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(477);
				match(T__27);
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAndReplContext extends ParserRuleContext {
		public RuleReplyContext ruleReply() {
			return getRuleContext(RuleReplyContext.class,0);
		}
		public RuleAndReplContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAndRepl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleAndRepl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleAndRepl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleAndRepl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleAndReplContext ruleAndRepl() throws RecognitionException {
		RuleAndReplContext _localctx = new RuleAndReplContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ruleAndRepl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			match(T__28);
			setState(481);
			ruleReply();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleOrReplContext extends ParserRuleContext {
		public RuleReplyContext ruleReply() {
			return getRuleContext(RuleReplyContext.class,0);
		}
		public RuleOrReplContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOrRepl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleOrRepl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleOrRepl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleOrRepl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleOrReplContext ruleOrRepl() throws RecognitionException {
		RuleOrReplContext _localctx = new RuleOrReplContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_ruleOrRepl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			match(T__29);
			setState(484);
			ruleReply();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAndJoinContext extends ParserRuleContext {
		public RuleReplyContext ruleReply() {
			return getRuleContext(RuleReplyContext.class,0);
		}
		public List<RuleAndReplContext> ruleAndRepl() {
			return getRuleContexts(RuleAndReplContext.class);
		}
		public RuleAndReplContext ruleAndRepl(int i) {
			return getRuleContext(RuleAndReplContext.class,i);
		}
		public RuleAndJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAndJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleAndJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleAndJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleAndJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleAndJoinContext ruleAndJoin() throws RecognitionException {
		RuleAndJoinContext _localctx = new RuleAndJoinContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_ruleAndJoin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			ruleReply();
			setState(488); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(487);
				ruleAndRepl();
				}
				}
				setState(490); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__28 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleOrJoinContext extends ParserRuleContext {
		public RuleReplyContext ruleReply() {
			return getRuleContext(RuleReplyContext.class,0);
		}
		public List<RuleOrReplContext> ruleOrRepl() {
			return getRuleContexts(RuleOrReplContext.class);
		}
		public RuleOrReplContext ruleOrRepl(int i) {
			return getRuleContext(RuleOrReplContext.class,i);
		}
		public RuleOrJoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOrJoin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleOrJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleOrJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleOrJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleOrJoinContext ruleOrJoin() throws RecognitionException {
		RuleOrJoinContext _localctx = new RuleOrJoinContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_ruleOrJoin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			ruleReply();
			setState(494); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(493);
				ruleOrRepl();
				}
				}
				setState(496); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__29 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleForkListContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleAndForkContext ruleAndFork() {
			return getRuleContext(RuleAndForkContext.class,0);
		}
		public RuleOrForkContext ruleOrFork() {
			return getRuleContext(RuleOrForkContext.class,0);
		}
		public RuleLoopContext ruleLoop() {
			return getRuleContext(RuleLoopContext.class,0);
		}
		public RuleForkListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleForkList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleForkList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleForkList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleForkList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleForkListContext ruleForkList() throws RecognitionException {
		RuleForkListContext _localctx = new RuleForkListContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_ruleForkList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(498);
				match(RULE_ID);
				}
				break;
			case 2:
				{
				setState(499);
				ruleAndFork();
				}
				break;
			case 3:
				{
				setState(500);
				ruleOrFork();
				}
				break;
			case 4:
				{
				setState(501);
				ruleLoop();
				}
				break;
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAndForkContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public List<RuleAndActContext> ruleAndAct() {
			return getRuleContexts(RuleAndActContext.class);
		}
		public RuleAndActContext ruleAndAct(int i) {
			return getRuleContext(RuleAndActContext.class,i);
		}
		public RuleAndForkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAndFork; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleAndFork(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleAndFork(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleAndFork(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleAndForkContext ruleAndFork() throws RecognitionException {
		RuleAndForkContext _localctx = new RuleAndForkContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_ruleAndFork);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			match(RULE_ID);
			setState(506); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(505);
				ruleAndAct();
				}
				}
				setState(508); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__28 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleAndActContext extends ParserRuleContext {
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleAndActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleAndAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleAndAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleAndAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleAndAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleAndActContext ruleAndAct() throws RecognitionException {
		RuleAndActContext _localctx = new RuleAndActContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_ruleAndAct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			match(T__28);
			setState(511);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleOrForkContext extends ParserRuleContext {
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public List<RuleOrActContext> ruleOrAct() {
			return getRuleContexts(RuleOrActContext.class);
		}
		public RuleOrActContext ruleOrAct(int i) {
			return getRuleContext(RuleOrActContext.class,i);
		}
		public RuleOrForkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOrFork; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleOrFork(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleOrFork(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleOrFork(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleOrForkContext ruleOrFork() throws RecognitionException {
		RuleOrForkContext _localctx = new RuleOrForkContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_ruleOrFork);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(T__30);
			setState(514);
			ruleNUMBER();
			setState(515);
			match(T__31);
			setState(516);
			match(RULE_ID);
			setState(518); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(517);
				ruleOrAct();
				}
				}
				setState(520); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__29 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleOrActContext extends ParserRuleContext {
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleOrActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOrAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleOrAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleOrAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleOrAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleOrActContext ruleOrAct() throws RecognitionException {
		RuleOrActContext _localctx = new RuleOrActContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_ruleOrAct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			match(T__29);
			setState(523);
			match(T__30);
			setState(524);
			ruleNUMBER();
			setState(525);
			match(T__31);
			setState(526);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleLoopContext extends ParserRuleContext {
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public List<RuleLoopActContext> ruleLoopAct() {
			return getRuleContexts(RuleLoopActContext.class);
		}
		public RuleLoopActContext ruleLoopAct(int i) {
			return getRuleContext(RuleLoopActContext.class,i);
		}
		public RuleLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleLoopContext ruleLoop() throws RecognitionException {
		RuleLoopContext _localctx = new RuleLoopContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_ruleLoop);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			ruleNUMBER();
			setState(529);
			match(T__32);
			setState(530);
			match(RULE_ID);
			setState(534);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(531);
					ruleLoopAct();
					}
					} 
				}
				setState(536);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(539);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__25) {
				{
				setState(537);
				match(T__25);
				setState(538);
				match(RULE_ID);
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleLoopActContext extends ParserRuleContext {
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleLoopActContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleLoopAct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleLoopAct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleLoopAct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleLoopAct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleLoopActContext ruleLoopAct() throws RecognitionException {
		RuleLoopActContext _localctx = new RuleLoopActContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_ruleLoopAct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			match(T__25);
			setState(542);
			ruleNUMBER();
			setState(543);
			match(T__32);
			setState(544);
			match(RULE_ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleInfinityRateContext extends ParserRuleContext {
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleInfinityRateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleInfinityRate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleInfinityRate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleInfinityRate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleInfinityRate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleInfinityRateContext ruleInfinityRate() throws RecognitionException {
		RuleInfinityRateContext _localctx = new RuleInfinityRateContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_ruleInfinityRate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			match(T__33);
			setState(547);
			_la = _input.LA(1);
			if ( !(_la==T__34 || _la==T__35) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(548);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolverContext extends ParserRuleContext {
		public RuleSolverKindContext ruleSolverKind() {
			return getRuleContext(RuleSolverKindContext.class,0);
		}
		public RuleSolverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolver; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolver(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolver(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolverContext ruleSolver() throws RecognitionException {
		RuleSolverContext _localctx = new RuleSolverContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_ruleSolver);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(T__33);
			setState(551);
			match(T__36);
			setState(553);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(552);
				match(T__37);
				}
			}

			setState(555);
			ruleSolverKind();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolVarContext extends ParserRuleContext {
		public RuleSolVarKindContext ruleSolVarKind() {
			return getRuleContext(RuleSolVarKindContext.class,0);
		}
		public RuleNUMBERContext ruleNUMBER() {
			return getRuleContext(RuleNUMBERContext.class,0);
		}
		public RuleSolVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolVarContext ruleSolVar() throws RecognitionException {
		RuleSolVarContext _localctx = new RuleSolVarContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_ruleSolVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			_la = _input.LA(1);
			if ( !(_la==T__25 || _la==T__33) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(558);
			ruleSolVarKind();
			setState(559);
			ruleNUMBER();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolSettingContext extends ParserRuleContext {
		public RuleSolBoolKindContext ruleSolBoolKind() {
			return getRuleContext(RuleSolBoolKindContext.class,0);
		}
		public RuleSolSettingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolSetting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolSetting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolSetting(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolSetting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolSettingContext ruleSolSetting() throws RecognitionException {
		RuleSolSettingContext _localctx = new RuleSolSettingContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_ruleSolSetting);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561);
			_la = _input.LA(1);
			if ( !(_la==T__25 || _la==T__33) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(562);
			ruleSolBoolKind();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleOutputReqContext extends ParserRuleContext {
		public TerminalNode RULE_ENDLIST() { return getToken(JLqnParser.RULE_ENDLIST, 0); }
		public List<TerminalNode> RULE_ID() { return getTokens(JLqnParser.RULE_ID); }
		public TerminalNode RULE_ID(int i) {
			return getToken(JLqnParser.RULE_ID, i);
		}
		public RuleOutputReqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleOutputReq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleOutputReq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleOutputReq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleOutputReq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleOutputReqContext ruleOutputReq() throws RecognitionException {
		RuleOutputReqContext _localctx = new RuleOutputReqContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_ruleOutputReq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(564);
				match(T__33);
				setState(565);
				match(T__38);
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(566);
					match(T__33);
					}
				}

				setState(570); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(569);
					match(RULE_ID);
					}
					}
					setState(572); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==RULE_ID );
				}
				break;
			}
			setState(586);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(576);
				match(T__33);
				setState(577);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(578);
					match(T__33);
					}
				}

				setState(582); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(581);
					match(RULE_ID);
					}
					}
					setState(584); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==RULE_ID );
				}
				break;
			}
			setState(598);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(588);
				match(T__33);
				setState(589);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(591);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(590);
					match(T__33);
					}
				}

				setState(594); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(593);
					match(RULE_ID);
					}
					}
					setState(596); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==RULE_ID );
				}
				break;
			}
			setState(601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__33) {
				{
				setState(600);
				match(T__33);
				}
			}

			setState(603);
			match(RULE_ENDLIST);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleExportSettingContext extends ParserRuleContext {
		public RuleExportKindContext ruleExportKind() {
			return getRuleContext(RuleExportKindContext.class,0);
		}
		public TerminalNode RULE_ID() { return getToken(JLqnParser.RULE_ID, 0); }
		public RuleExportSettingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleExportSetting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleExportSetting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleExportSetting(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleExportSetting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleExportSettingContext ruleExportSetting() throws RecognitionException {
		RuleExportSettingContext _localctx = new RuleExportSettingContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_ruleExportSetting);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			match(T__33);
			setState(606);
			match(T__43);
			setState(607);
			ruleExportKind();
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__44) {
				{
				setState(608);
				match(T__44);
				setState(609);
				match(RULE_ID);
				}
			}

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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleExportKindContext extends ParserRuleContext {
		public RuleExportKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleExportKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleExportKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleExportKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleExportKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleExportKindContext ruleExportKind() throws RecognitionException {
		RuleExportKindContext _localctx = new RuleExportKindContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_ruleExportKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2181431069507584L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolverKindContext extends ParserRuleContext {
		public RuleSolverKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolverKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolverKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolverKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolverKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolverKindContext ruleSolverKind() throws RecognitionException {
		RuleSolverKindContext _localctx = new RuleSolverKindContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_ruleSolverKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15762598695796736L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolVarKindContext extends ParserRuleContext {
		public RuleSolVarKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolVarKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolVarKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolVarKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolVarKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolVarKindContext ruleSolVarKind() throws RecognitionException {
		RuleSolVarKindContext _localctx = new RuleSolVarKindContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_ruleSolVarKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4593671619917905920L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleSolBoolKindContext extends ParserRuleContext {
		public RuleSolBoolKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleSolBoolKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleSolBoolKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleSolBoolKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleSolBoolKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleSolBoolKindContext ruleSolBoolKind() throws RecognitionException {
		RuleSolBoolKindContext _localctx = new RuleSolBoolKindContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_ruleSolBoolKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleTaskKindContext extends ParserRuleContext {
		public RuleTaskKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleTaskKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleTaskKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleTaskKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleTaskKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleTaskKindContext ruleTaskKind() throws RecognitionException {
		RuleTaskKindContext _localctx = new RuleTaskKindContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_ruleTaskKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(620);
			_la = _input.LA(1);
			if ( !(((((_la - 5)) & ~0x3f) == 0 && ((1L << (_la - 5)) & 8070450532247937033L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleScheduleKindContext extends ParserRuleContext {
		public RuleScheduleKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleScheduleKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).enterRuleScheduleKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JLqnListener ) ((JLqnListener)listener).exitRuleScheduleKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JLqnVisitor ) return ((JLqnVisitor<? extends T>)visitor).visitRuleScheduleKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleScheduleKindContext ruleScheduleKind() throws RecognitionException {
		RuleScheduleKindContext _localctx = new RuleScheduleKindContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_ruleScheduleKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
			_la = _input.LA(1);
			if ( !(((((_la - 5)) & ~0x3f) == 0 && ((1L << (_la - 5)) & 2305843009213702409L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static final String _serializedATN =
		"\u0004\u0001M\u0271\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000~\b"+
		"\u0000\u0001\u0000\u0003\u0000\u0081\b\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u0000\u0085\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000\u008a"+
		"\b\u0000\n\u0000\f\u0000\u008d\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u0000\u0093\b\u0000\n\u0000\f\u0000\u0096\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u00a8\b\u0000\u000b"+
		"\u0000\f\u0000\u00a9\u0001\u0000\u0001\u0000\u0005\u0000\u00ae\b\u0000"+
		"\n\u0000\f\u0000\u00b1\t\u0000\u0001\u0000\u0003\u0000\u00b4\b\u0000\u0001"+
		"\u0000\u0003\u0000\u00b7\b\u0000\u0001\u0000\u0005\u0000\u00ba\b\u0000"+
		"\n\u0000\f\u0000\u00bd\t\u0000\u0001\u0000\u0005\u0000\u00c0\b\u0000\n"+
		"\u0000\f\u0000\u00c3\t\u0000\u0001\u0000\u0003\u0000\u00c6\b\u0000\u0001"+
		"\u0000\u0005\u0000\u00c9\b\u0000\n\u0000\f\u0000\u00cc\t\u0000\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\u00d0\b\u0001\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u00da\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00df\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00e3\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00e7\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0004\u0005\u00ed\b\u0005\u000b\u0005\f\u0005\u00ee\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0004\u0005\u00f4\b\u0005\u000b\u0005\f\u0005"+
		"\u00f5\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00fb\b\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u00ff\b\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u0103\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0108"+
		"\b\u0005\u0003\u0005\u010a\b\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"\u010e\b\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b"+
		"\u0001\b\u0001\b\u0004\b\u0117\b\b\u000b\b\f\b\u0118\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0004\t\u0121\b\t\u000b\t\f\t\u0122\u0001\t"+
		"\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u012b\b\n\u000b\n\f\n"+
		"\u012c\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0004\r\u013c"+
		"\b\r\u000b\r\f\r\u013d\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0004\u000e\u0145\b\u000e\u000b\u000e\f\u000e\u0146\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0004\u000f\u014f"+
		"\b\u000f\u000b\u000f\f\u000f\u0150\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0004\u0010\u0159\b\u0010\u000b\u0010"+
		"\f\u0010\u015a\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0004\u0012\u0166\b\u0012"+
		"\u000b\u0012\f\u0012\u0167\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0004\u0014\u0174\b\u0014\u000b\u0014\f\u0014\u0175\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u017e"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0004\u0016\u0189\b\u0016\u000b"+
		"\u0016\f\u0016\u018a\u0001\u0016\u0003\u0016\u018e\b\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0005\u0017\u0194\b\u0017\n\u0017"+
		"\f\u0017\u0197\t\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u019c\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0003!\u01c9"+
		"\b!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0003\"\u01d0\b\"\u0001#\u0001"+
		"#\u0001#\u0001$\u0001$\u0001$\u0001$\u0005$\u01d9\b$\n$\f$\u01dc\t$\u0001"+
		"$\u0003$\u01df\b$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001"+
		"\'\u0004\'\u01e9\b\'\u000b\'\f\'\u01ea\u0001(\u0001(\u0004(\u01ef\b(\u000b"+
		"(\f(\u01f0\u0001)\u0001)\u0001)\u0001)\u0003)\u01f7\b)\u0001*\u0001*\u0004"+
		"*\u01fb\b*\u000b*\f*\u01fc\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0004,\u0207\b,\u000b,\f,\u0208\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001.\u0001.\u0001.\u0001.\u0005.\u0215\b.\n.\f.\u0218\t.\u0001"+
		".\u0001.\u0003.\u021c\b.\u0001/\u0001/\u0001/\u0001/\u0001/\u00010\u0001"+
		"0\u00010\u00010\u00011\u00011\u00011\u00031\u022a\b1\u00011\u00011\u0001"+
		"2\u00012\u00012\u00012\u00013\u00013\u00013\u00014\u00014\u00014\u0003"+
		"4\u0238\b4\u00014\u00044\u023b\b4\u000b4\f4\u023c\u00034\u023f\b4\u0001"+
		"4\u00014\u00014\u00034\u0244\b4\u00014\u00044\u0247\b4\u000b4\f4\u0248"+
		"\u00034\u024b\b4\u00014\u00014\u00014\u00034\u0250\b4\u00014\u00044\u0253"+
		"\b4\u000b4\f4\u0254\u00034\u0257\b4\u00014\u00034\u025a\b4\u00014\u0001"+
		"4\u00015\u00015\u00015\u00015\u00015\u00035\u0263\b5\u00016\u00016\u0001"+
		"7\u00017\u00018\u00018\u00019\u00019\u0001:\u0001:\u0001;\u0001;\u0001"+
		";\u0000\u0000<\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprt"+
		"v\u0000\f\u0001\u0000FG\u0001\u0000GH\u0001\u0000#$\u0002\u0000\u001a"+
		"\u001a\"\"\u0001\u0000()\u0001\u0000*+\u0001\u0000.2\u0001\u000035\u0001"+
		"\u00006=\u0001\u0000>@\u0004\u0000\u0005\u0005\b\b\u0012\u0012AC\u0005"+
		"\u0000\u0005\u0005\b\b\r\r\u0012\u0012BB\u028c\u0000\u0084\u0001\u0000"+
		"\u0000\u0000\u0002\u00cf\u0001\u0000\u0000\u0000\u0004\u00d1\u0001\u0000"+
		"\u0000\u0000\u0006\u00d3\u0001\u0000\u0000\u0000\b\u00d5\u0001\u0000\u0000"+
		"\u0000\n\u00e8\u0001\u0000\u0000\u0000\f\u010f\u0001\u0000\u0000\u0000"+
		"\u000e\u0111\u0001\u0000\u0000\u0000\u0010\u0113\u0001\u0000\u0000\u0000"+
		"\u0012\u011c\u0001\u0000\u0000\u0000\u0014\u0126\u0001\u0000\u0000\u0000"+
		"\u0016\u0130\u0001\u0000\u0000\u0000\u0018\u0134\u0001\u0000\u0000\u0000"+
		"\u001a\u0138\u0001\u0000\u0000\u0000\u001c\u0141\u0001\u0000\u0000\u0000"+
		"\u001e\u014a\u0001\u0000\u0000\u0000 \u0154\u0001\u0000\u0000\u0000\""+
		"\u015e\u0001\u0000\u0000\u0000$\u0162\u0001\u0000\u0000\u0000&\u016b\u0001"+
		"\u0000\u0000\u0000(\u0170\u0001\u0000\u0000\u0000*\u0179\u0001\u0000\u0000"+
		"\u0000,\u017d\u0001\u0000\u0000\u0000.\u0191\u0001\u0000\u0000\u00000"+
		"\u019d\u0001\u0000\u0000\u00002\u01a1\u0001\u0000\u0000\u00004\u01a6\u0001"+
		"\u0000\u0000\u00006\u01aa\u0001\u0000\u0000\u00008\u01ae\u0001\u0000\u0000"+
		"\u0000:\u01b3\u0001\u0000\u0000\u0000<\u01b8\u0001\u0000\u0000\u0000>"+
		"\u01bc\u0001\u0000\u0000\u0000@\u01c1\u0001\u0000\u0000\u0000B\u01c5\u0001"+
		"\u0000\u0000\u0000D\u01cf\u0001\u0000\u0000\u0000F\u01d1\u0001\u0000\u0000"+
		"\u0000H\u01d4\u0001\u0000\u0000\u0000J\u01e0\u0001\u0000\u0000\u0000L"+
		"\u01e3\u0001\u0000\u0000\u0000N\u01e6\u0001\u0000\u0000\u0000P\u01ec\u0001"+
		"\u0000\u0000\u0000R\u01f6\u0001\u0000\u0000\u0000T\u01f8\u0001\u0000\u0000"+
		"\u0000V\u01fe\u0001\u0000\u0000\u0000X\u0201\u0001\u0000\u0000\u0000Z"+
		"\u020a\u0001\u0000\u0000\u0000\\\u0210\u0001\u0000\u0000\u0000^\u021d"+
		"\u0001\u0000\u0000\u0000`\u0222\u0001\u0000\u0000\u0000b\u0226\u0001\u0000"+
		"\u0000\u0000d\u022d\u0001\u0000\u0000\u0000f\u0231\u0001\u0000\u0000\u0000"+
		"h\u023e\u0001\u0000\u0000\u0000j\u025d\u0001\u0000\u0000\u0000l\u0264"+
		"\u0001\u0000\u0000\u0000n\u0266\u0001\u0000\u0000\u0000p\u0268\u0001\u0000"+
		"\u0000\u0000r\u026a\u0001\u0000\u0000\u0000t\u026c\u0001\u0000\u0000\u0000"+
		"v\u026e\u0001\u0000\u0000\u0000xy\u0005\u0001\u0000\u0000yz\u0005J\u0000"+
		"\u0000z{\u0003\u0002\u0001\u0000{}\u0003\u0006\u0003\u0000|~\u0003\u0006"+
		"\u0003\u0000}|\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u0080"+
		"\u0001\u0000\u0000\u0000\u007f\u0081\u0005F\u0000\u0000\u0080\u007f\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0005E\u0000\u0000\u0083\u0085\u0001\u0000"+
		"\u0000\u0000\u0084x\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0002\u0000"+
		"\u0000\u0087\u008b\u0003\u0006\u0003\u0000\u0088\u008a\u0003\b\u0004\u0000"+
		"\u0089\u0088\u0001\u0000\u0000\u0000\u008a\u008d\u0001\u0000\u0000\u0000"+
		"\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000"+
		"\u008c\u008e\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0005E\u0000\u0000\u008f\u0090\u0005\u0003\u0000\u0000\u0090"+
		"\u0094\u0003\u0006\u0003\u0000\u0091\u0093\u0003\n\u0005\u0000\u0092\u0091"+
		"\u0001\u0000\u0000\u0000\u0093\u0096\u0001\u0000\u0000\u0000\u0094\u0092"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0097"+
		"\u0001\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0097\u0098"+
		"\u0005E\u0000\u0000\u0098\u0099\u0005\u0004\u0000\u0000\u0099\u00a7\u0003"+
		"\u0006\u0003\u0000\u009a\u00a8\u0003\u0010\b\u0000\u009b\u00a8\u0003\u0012"+
		"\t\u0000\u009c\u00a8\u0003\u0014\n\u0000\u009d\u00a8\u0003\u0016\u000b"+
		"\u0000\u009e\u00a8\u0003\u0018\f\u0000\u009f\u00a8\u0003\u001a\r\u0000"+
		"\u00a0\u00a8\u0003\u001c\u000e\u0000\u00a1\u00a8\u0003\u001e\u000f\u0000"+
		"\u00a2\u00a8\u0003 \u0010\u0000\u00a3\u00a8\u0003\"\u0011\u0000\u00a4"+
		"\u00a8\u0003$\u0012\u0000\u00a5\u00a8\u0003&\u0013\u0000\u00a6\u00a8\u0003"+
		"(\u0014\u0000\u00a7\u009a\u0001\u0000\u0000\u0000\u00a7\u009b\u0001\u0000"+
		"\u0000\u0000\u00a7\u009c\u0001\u0000\u0000\u0000\u00a7\u009d\u0001\u0000"+
		"\u0000\u0000\u00a7\u009e\u0001\u0000\u0000\u0000\u00a7\u009f\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a0\u0001\u0000\u0000\u0000\u00a7\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a2\u0001\u0000\u0000\u0000\u00a7\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a4\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000"+
		"\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00af\u0005E\u0000"+
		"\u0000\u00ac\u00ae\u0003,\u0016\u0000\u00ad\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000"+
		"\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2\u00b4\u0003`0\u0000\u00b3\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b7\u0003b1\u0000\u00b6\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00bb\u0001\u0000"+
		"\u0000\u0000\u00b8\u00ba\u0003d2\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00c1\u0001\u0000\u0000\u0000"+
		"\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00c0\u0003f3\u0000\u00bf\u00be"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c6"+
		"\u0003h4\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00ca\u0001\u0000\u0000\u0000\u00c7\u00c9\u0003j5\u0000"+
		"\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c9\u00cc\u0001\u0000\u0000\u0000"+
		"\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cb\u0001\u0001\u0000\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000"+
		"\u00cd\u00d0\u0005H\u0000\u0000\u00ce\u00d0\u0003\u0004\u0002\u0000\u00cf"+
		"\u00cd\u0001\u0000\u0000\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00d0"+
		"\u0003\u0001\u0000\u0000\u0000\u00d1\u00d2\u0007\u0000\u0000\u0000\u00d2"+
		"\u0005\u0001\u0000\u0000\u0000\u00d3\u00d4\u0007\u0001\u0000\u0000\u00d4"+
		"\u0007\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005\u0005\u0000\u0000\u00d6"+
		"\u00d7\u0005I\u0000\u0000\u00d7\u00d9\u0003v;\u0000\u00d8\u00da\u0003"+
		"\u0002\u0001\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00d9\u00da\u0001"+
		"\u0000\u0000\u0000\u00da\u00de\u0001\u0000\u0000\u0000\u00db\u00dc\u0005"+
		"\u0006\u0000\u0000\u00dc\u00df\u0003\u0006\u0003\u0000\u00dd\u00df\u0005"+
		"\u0007\u0000\u0000\u00de\u00db\u0001\u0000\u0000\u0000\u00de\u00dd\u0001"+
		"\u0000\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e0\u00e1\u0005\b\u0000\u0000\u00e1\u00e3\u0003\u0006"+
		"\u0003\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005\t\u0000"+
		"\u0000\u00e5\u00e7\u0003\u0002\u0001\u0000\u00e6\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7\t\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e9\u0005\n\u0000\u0000\u00e9\u0109\u0003\f\u0006\u0000\u00ea"+
		"\u00ec\u0005\u0007\u0000\u0000\u00eb\u00ed\u0005I\u0000\u0000\u00ec\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0"+
		"\u0001\u0000\u0000\u0000\u00f0\u010a\u0005E\u0000\u0000\u00f1\u00f3\u0003"+
		"t:\u0000\u00f2\u00f4\u0005I\u0000\u0000\u00f3\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f7\u00fa\u0005E\u0000\u0000\u00f8\u00f9\u0005\u000b\u0000\u0000"+
		"\u00f9\u00fb\u0003\u0006\u0003\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000"+
		"\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fe\u0005I\u0000\u0000\u00fd\u00ff\u0003\u0006\u0003\u0000\u00fe"+
		"\u00fd\u0001\u0000\u0000\u0000\u00fe\u00ff\u0001\u0000\u0000\u0000\u00ff"+
		"\u0102\u0001\u0000\u0000\u0000\u0100\u0101\u0005\f\u0000\u0000\u0101\u0103"+
		"\u0003\u0002\u0001\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103"+
		"\u0001\u0000\u0000\u0000\u0103\u0107\u0001\u0000\u0000\u0000\u0104\u0105"+
		"\u0005\u0006\u0000\u0000\u0105\u0108\u0003\u0006\u0003\u0000\u0106\u0108"+
		"\u0005\u0007\u0000\u0000\u0107\u0104\u0001\u0000\u0000\u0000\u0107\u0106"+
		"\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000\u0000\u0000\u0108\u010a"+
		"\u0001\u0000\u0000\u0000\u0109\u00ea\u0001\u0000\u0000\u0000\u0109\u00f1"+
		"\u0001\u0000\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u010c"+
		"\u0005\b\u0000\u0000\u010c\u010e\u0003\u0006\u0003\u0000\u010d\u010b\u0001"+
		"\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u000b\u0001"+
		"\u0000\u0000\u0000\u010f\u0110\u0005I\u0000\u0000\u0110\r\u0001\u0000"+
		"\u0000\u0000\u0111\u0112\u0005I\u0000\u0000\u0112\u000f\u0001\u0000\u0000"+
		"\u0000\u0113\u0114\u0005\r\u0000\u0000\u0114\u0116\u0003\u000e\u0007\u0000"+
		"\u0115\u0117\u0003\u0002\u0001\u0000\u0116\u0115\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000"+
		"\u0118\u0119\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000"+
		"\u011a\u011b\u0005E\u0000\u0000\u011b\u0011\u0001\u0000\u0000\u0000\u011c"+
		"\u011d\u0005\u000e\u0000\u0000\u011d\u011e\u0005I\u0000\u0000\u011e\u0120"+
		"\u0005I\u0000\u0000\u011f\u0121\u0003\u0002\u0001\u0000\u0120\u011f\u0001"+
		"\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0120\u0001"+
		"\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0124\u0001"+
		"\u0000\u0000\u0000\u0124\u0125\u0005E\u0000\u0000\u0125\u0013\u0001\u0000"+
		"\u0000\u0000\u0126\u0127\u0005\f\u0000\u0000\u0127\u0128\u0005I\u0000"+
		"\u0000\u0128\u012a\u0005I\u0000\u0000\u0129\u012b\u0003\u0002\u0001\u0000"+
		"\u012a\u0129\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000"+
		"\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000"+
		"\u012d\u012e\u0001\u0000\u0000\u0000\u012e\u012f\u0005E\u0000\u0000\u012f"+
		"\u0015\u0001\u0000\u0000\u0000\u0130\u0131\u0005\u000f\u0000\u0000\u0131"+
		"\u0132\u0003\u000e\u0007\u0000\u0132\u0133\u0005I\u0000\u0000\u0133\u0017"+
		"\u0001\u0000\u0000\u0000\u0134\u0135\u0005\u0010\u0000\u0000\u0135\u0136"+
		"\u0005I\u0000\u0000\u0136\u0137\u0003\u0002\u0001\u0000\u0137\u0019\u0001"+
		"\u0000\u0000\u0000\u0138\u0139\u0005\u0011\u0000\u0000\u0139\u013b\u0005"+
		"I\u0000\u0000\u013a\u013c\u0003\u0002\u0001\u0000\u013b\u013a\u0001\u0000"+
		"\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000"+
		"\u0000\u0000\u013f\u0140\u0005E\u0000\u0000\u0140\u001b\u0001\u0000\u0000"+
		"\u0000\u0141\u0142\u0005\u0012\u0000\u0000\u0142\u0144\u0005I\u0000\u0000"+
		"\u0143\u0145\u0003\u0006\u0003\u0000\u0144\u0143\u0001\u0000\u0000\u0000"+
		"\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0144\u0001\u0000\u0000\u0000"+
		"\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0148\u0001\u0000\u0000\u0000"+
		"\u0148\u0149\u0005E\u0000\u0000\u0149\u001d\u0001\u0000\u0000\u0000\u014a"+
		"\u014b\u0005\u0007\u0000\u0000\u014b\u014c\u0005I\u0000\u0000\u014c\u014e"+
		"\u0005I\u0000\u0000\u014d\u014f\u0003\u0006\u0003\u0000\u014e\u014d\u0001"+
		"\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u014e\u0001"+
		"\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000\u0000\u0151\u0152\u0001"+
		"\u0000\u0000\u0000\u0152\u0153\u0005E\u0000\u0000\u0153\u001f\u0001\u0000"+
		"\u0000\u0000\u0154\u0155\u0005\u0013\u0000\u0000\u0155\u0156\u0005I\u0000"+
		"\u0000\u0156\u0158\u0005I\u0000\u0000\u0157\u0159\u0003\u0006\u0003\u0000"+
		"\u0158\u0157\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000"+
		"\u015a\u0158\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000\u0000"+
		"\u015b\u015c\u0001\u0000\u0000\u0000\u015c\u015d\u0005E\u0000\u0000\u015d"+
		"!\u0001\u0000\u0000\u0000\u015e\u015f\u0005\u0005\u0000\u0000\u015f\u0160"+
		"\u0005I\u0000\u0000\u0160\u0161\u0003\u0006\u0003\u0000\u0161#\u0001\u0000"+
		"\u0000\u0000\u0162\u0163\u0005\u0014\u0000\u0000\u0163\u0165\u0005I\u0000"+
		"\u0000\u0164\u0166\u0003\u0002\u0001\u0000\u0165\u0164\u0001\u0000\u0000"+
		"\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000"+
		"\u0000\u0167\u0168\u0001\u0000\u0000\u0000\u0168\u0169\u0001\u0000\u0000"+
		"\u0000\u0169\u016a\u0005E\u0000\u0000\u016a%\u0001\u0000\u0000\u0000\u016b"+
		"\u016c\u0005\u0015\u0000\u0000\u016c\u016d\u0005I\u0000\u0000\u016d\u016e"+
		"\u0005I\u0000\u0000\u016e\u016f\u0003\u0002\u0001\u0000\u016f\'\u0001"+
		"\u0000\u0000\u0000\u0170\u0171\u0005\u0016\u0000\u0000\u0171\u0173\u0005"+
		"I\u0000\u0000\u0172\u0174\u0003\u0002\u0001\u0000\u0173\u0172\u0001\u0000"+
		"\u0000\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u0173\u0001\u0000"+
		"\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000"+
		"\u0000\u0000\u0177\u0178\u0005E\u0000\u0000\u0178)\u0001\u0000\u0000\u0000"+
		"\u0179\u017a\u0005I\u0000\u0000\u017a+\u0001\u0000\u0000\u0000\u017b\u017c"+
		"\u0005\u000f\u0000\u0000\u017c\u017e\u0005I\u0000\u0000\u017d\u017b\u0001"+
		"\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u0188\u0001"+
		"\u0000\u0000\u0000\u017f\u0189\u00030\u0018\u0000\u0180\u0189\u00032\u0019"+
		"\u0000\u0181\u0189\u00034\u001a\u0000\u0182\u0189\u00036\u001b\u0000\u0183"+
		"\u0189\u00038\u001c\u0000\u0184\u0189\u0003:\u001d\u0000\u0185\u0189\u0003"+
		"<\u001e\u0000\u0186\u0189\u0003>\u001f\u0000\u0187\u0189\u0003@ \u0000"+
		"\u0188\u017f\u0001\u0000\u0000\u0000\u0188\u0180\u0001\u0000\u0000\u0000"+
		"\u0188\u0181\u0001\u0000\u0000\u0000\u0188\u0182\u0001\u0000\u0000\u0000"+
		"\u0188\u0183\u0001\u0000\u0000\u0000\u0188\u0184\u0001\u0000\u0000\u0000"+
		"\u0188\u0185\u0001\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000"+
		"\u0188\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000\u0000"+
		"\u018a\u0188\u0001\u0000\u0000\u0000\u018a\u018b\u0001\u0000\u0000\u0000"+
		"\u018b\u018d\u0001\u0000\u0000\u0000\u018c\u018e\u0003.\u0017\u0000\u018d"+
		"\u018c\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e"+
		"\u018f\u0001\u0000\u0000\u0000\u018f\u0190\u0005E\u0000\u0000\u0190-\u0001"+
		"\u0000\u0000\u0000\u0191\u0195\u0005\u0017\u0000\u0000\u0192\u0194\u0003"+
		"B!\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000\u0000"+
		"\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000"+
		"\u0000\u0196\u0198\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000\u0000"+
		"\u0000\u0198\u019b\u0003D\"\u0000\u0199\u019a\u0005\u0018\u0000\u0000"+
		"\u019a\u019c\u0003R)\u0000\u019b\u0199\u0001\u0000\u0000\u0000\u019b\u019c"+
		"\u0001\u0000\u0000\u0000\u019c/\u0001\u0000\u0000\u0000\u019d\u019e\u0005"+
		"\r\u0000\u0000\u019e\u019f\u0003*\u0015\u0000\u019f\u01a0\u0003\u0002"+
		"\u0001\u0000\u01a01\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005\u000e\u0000"+
		"\u0000\u01a2\u01a3\u0005I\u0000\u0000\u01a3\u01a4\u0005I\u0000\u0000\u01a4"+
		"\u01a5\u0003\u0002\u0001\u0000\u01a53\u0001\u0000\u0000\u0000\u01a6\u01a7"+
		"\u0005\u0011\u0000\u0000\u01a7\u01a8\u0005I\u0000\u0000\u01a8\u01a9\u0003"+
		"\u0002\u0001\u0000\u01a95\u0001\u0000\u0000\u0000\u01aa\u01ab\u0005\u0012"+
		"\u0000\u0000\u01ab\u01ac\u0005I\u0000\u0000\u01ac\u01ad\u0003\u0006\u0003"+
		"\u0000\u01ad7\u0001\u0000\u0000\u0000\u01ae\u01af\u0005\u0007\u0000\u0000"+
		"\u01af\u01b0\u0005I\u0000\u0000\u01b0\u01b1\u0005I\u0000\u0000\u01b1\u01b2"+
		"\u0003\u0006\u0003\u0000\u01b29\u0001\u0000\u0000\u0000\u01b3\u01b4\u0005"+
		"\u0013\u0000\u0000\u01b4\u01b5\u0005I\u0000\u0000\u01b5\u01b6\u0005I\u0000"+
		"\u0000\u01b6\u01b7\u0003\u0006\u0003\u0000\u01b7;\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b9\u0005\u0014\u0000\u0000\u01b9\u01ba\u0005I\u0000\u0000\u01ba"+
		"\u01bb\u0003\u0002\u0001\u0000\u01bb=\u0001\u0000\u0000\u0000\u01bc\u01bd"+
		"\u0005\f\u0000\u0000\u01bd\u01be\u0005I\u0000\u0000\u01be\u01bf\u0005"+
		"I\u0000\u0000\u01bf\u01c0\u0003\u0002\u0001\u0000\u01c0?\u0001\u0000\u0000"+
		"\u0000\u01c1\u01c2\u0005\u0016\u0000\u0000\u01c2\u01c3\u0005I\u0000\u0000"+
		"\u01c3\u01c4\u0003\u0002\u0001\u0000\u01c4A\u0001\u0000\u0000\u0000\u01c5"+
		"\u01c8\u0003D\"\u0000\u01c6\u01c7\u0005\u0018\u0000\u0000\u01c7\u01c9"+
		"\u0003R)\u0000\u01c8\u01c6\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0005\u0019"+
		"\u0000\u0000\u01cbC\u0001\u0000\u0000\u0000\u01cc\u01d0\u0003H$\u0000"+
		"\u01cd\u01d0\u0003N\'\u0000\u01ce\u01d0\u0003P(\u0000\u01cf\u01cc\u0001"+
		"\u0000\u0000\u0000\u01cf\u01cd\u0001\u0000\u0000\u0000\u01cf\u01ce\u0001"+
		"\u0000\u0000\u0000\u01d0E\u0001\u0000\u0000\u0000\u01d1\u01d2\u0005\u001a"+
		"\u0000\u0000\u01d2\u01d3\u0005I\u0000\u0000\u01d3G\u0001\u0000\u0000\u0000"+
		"\u01d4\u01de\u0005I\u0000\u0000\u01d5\u01d6\u0005\u001b\u0000\u0000\u01d6"+
		"\u01da\u0005I\u0000\u0000\u01d7\u01d9\u0003F#\u0000\u01d8\u01d7\u0001"+
		"\u0000\u0000\u0000\u01d9\u01dc\u0001\u0000\u0000\u0000\u01da\u01d8\u0001"+
		"\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000\u0000\u01db\u01dd\u0001"+
		"\u0000\u0000\u0000\u01dc\u01da\u0001\u0000\u0000\u0000\u01dd\u01df\u0005"+
		"\u001c\u0000\u0000\u01de\u01d5\u0001\u0000\u0000\u0000\u01de\u01df\u0001"+
		"\u0000\u0000\u0000\u01dfI\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005\u001d"+
		"\u0000\u0000\u01e1\u01e2\u0003H$\u0000\u01e2K\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e4\u0005\u001e\u0000\u0000\u01e4\u01e5\u0003H$\u0000\u01e5M"+
		"\u0001\u0000\u0000\u0000\u01e6\u01e8\u0003H$\u0000\u01e7\u01e9\u0003J"+
		"%\u0000\u01e8\u01e7\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000"+
		"\u0000\u01ea\u01e8\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000"+
		"\u0000\u01ebO\u0001\u0000\u0000\u0000\u01ec\u01ee\u0003H$\u0000\u01ed"+
		"\u01ef\u0003L&\u0000\u01ee\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f0\u01ee\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001"+
		"\u0000\u0000\u0000\u01f1Q\u0001\u0000\u0000\u0000\u01f2\u01f7\u0005I\u0000"+
		"\u0000\u01f3\u01f7\u0003T*\u0000\u01f4\u01f7\u0003X,\u0000\u01f5\u01f7"+
		"\u0003\\.\u0000\u01f6\u01f2\u0001\u0000\u0000\u0000\u01f6\u01f3\u0001"+
		"\u0000\u0000\u0000\u01f6\u01f4\u0001\u0000\u0000\u0000\u01f6\u01f5\u0001"+
		"\u0000\u0000\u0000\u01f7S\u0001\u0000\u0000\u0000\u01f8\u01fa\u0005I\u0000"+
		"\u0000\u01f9\u01fb\u0003V+\u0000\u01fa\u01f9\u0001\u0000\u0000\u0000\u01fb"+
		"\u01fc\u0001\u0000\u0000\u0000\u01fc\u01fa\u0001\u0000\u0000\u0000\u01fc"+
		"\u01fd\u0001\u0000\u0000\u0000\u01fdU\u0001\u0000\u0000\u0000\u01fe\u01ff"+
		"\u0005\u001d\u0000\u0000\u01ff\u0200\u0005I\u0000\u0000\u0200W\u0001\u0000"+
		"\u0000\u0000\u0201\u0202\u0005\u001f\u0000\u0000\u0202\u0203\u0003\u0002"+
		"\u0001\u0000\u0203\u0204\u0005 \u0000\u0000\u0204\u0206\u0005I\u0000\u0000"+
		"\u0205\u0207\u0003Z-\u0000\u0206\u0205\u0001\u0000\u0000\u0000\u0207\u0208"+
		"\u0001\u0000\u0000\u0000\u0208\u0206\u0001\u0000\u0000\u0000\u0208\u0209"+
		"\u0001\u0000\u0000\u0000\u0209Y\u0001\u0000\u0000\u0000\u020a\u020b\u0005"+
		"\u001e\u0000\u0000\u020b\u020c\u0005\u001f\u0000\u0000\u020c\u020d\u0003"+
		"\u0002\u0001\u0000\u020d\u020e\u0005 \u0000\u0000\u020e\u020f\u0005I\u0000"+
		"\u0000\u020f[\u0001\u0000\u0000\u0000\u0210\u0211\u0003\u0002\u0001\u0000"+
		"\u0211\u0212\u0005!\u0000\u0000\u0212\u0216\u0005I\u0000\u0000\u0213\u0215"+
		"\u0003^/\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0215\u0218\u0001\u0000"+
		"\u0000\u0000\u0216\u0214\u0001\u0000\u0000\u0000\u0216\u0217\u0001\u0000"+
		"\u0000\u0000\u0217\u021b\u0001\u0000\u0000\u0000\u0218\u0216\u0001\u0000"+
		"\u0000\u0000\u0219\u021a\u0005\u001a\u0000\u0000\u021a\u021c\u0005I\u0000"+
		"\u0000\u021b\u0219\u0001\u0000\u0000\u0000\u021b\u021c\u0001\u0000\u0000"+
		"\u0000\u021c]\u0001\u0000\u0000\u0000\u021d\u021e\u0005\u001a\u0000\u0000"+
		"\u021e\u021f\u0003\u0002\u0001\u0000\u021f\u0220\u0005!\u0000\u0000\u0220"+
		"\u0221\u0005I\u0000\u0000\u0221_\u0001\u0000\u0000\u0000\u0222\u0223\u0005"+
		"\"\u0000\u0000\u0223\u0224\u0007\u0002\u0000\u0000\u0224\u0225\u0003\u0002"+
		"\u0001\u0000\u0225a\u0001\u0000\u0000\u0000\u0226\u0227\u0005\"\u0000"+
		"\u0000\u0227\u0229\u0005%\u0000\u0000\u0228\u022a\u0005&\u0000\u0000\u0229"+
		"\u0228\u0001\u0000\u0000\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a"+
		"\u022b\u0001\u0000\u0000\u0000\u022b\u022c\u0003n7\u0000\u022cc\u0001"+
		"\u0000\u0000\u0000\u022d\u022e\u0007\u0003\u0000\u0000\u022e\u022f\u0003"+
		"p8\u0000\u022f\u0230\u0003\u0002\u0001\u0000\u0230e\u0001\u0000\u0000"+
		"\u0000\u0231\u0232\u0007\u0003\u0000\u0000\u0232\u0233\u0003r9\u0000\u0233"+
		"g\u0001\u0000\u0000\u0000\u0234\u0235\u0005\"\u0000\u0000\u0235\u0237"+
		"\u0005\'\u0000\u0000\u0236\u0238\u0005\"\u0000\u0000\u0237\u0236\u0001"+
		"\u0000\u0000\u0000\u0237\u0238\u0001\u0000\u0000\u0000\u0238\u023a\u0001"+
		"\u0000\u0000\u0000\u0239\u023b\u0005I\u0000\u0000\u023a\u0239\u0001\u0000"+
		"\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000\u023c\u023a\u0001\u0000"+
		"\u0000\u0000\u023c\u023d\u0001\u0000\u0000\u0000\u023d\u023f\u0001\u0000"+
		"\u0000\u0000\u023e\u0234\u0001\u0000\u0000\u0000\u023e\u023f\u0001\u0000"+
		"\u0000\u0000\u023f\u024a\u0001\u0000\u0000\u0000\u0240\u0241\u0005\"\u0000"+
		"\u0000\u0241\u0243\u0007\u0004\u0000\u0000\u0242\u0244\u0005\"\u0000\u0000"+
		"\u0243\u0242\u0001\u0000\u0000\u0000\u0243\u0244\u0001\u0000\u0000\u0000"+
		"\u0244\u0246\u0001\u0000\u0000\u0000\u0245\u0247\u0005I\u0000\u0000\u0246"+
		"\u0245\u0001\u0000\u0000\u0000\u0247\u0248\u0001\u0000\u0000\u0000\u0248"+
		"\u0246\u0001\u0000\u0000\u0000\u0248\u0249\u0001\u0000\u0000\u0000\u0249"+
		"\u024b\u0001\u0000\u0000\u0000\u024a\u0240\u0001\u0000\u0000\u0000\u024a"+
		"\u024b\u0001\u0000\u0000\u0000\u024b\u0256\u0001\u0000\u0000\u0000\u024c"+
		"\u024d\u0005\"\u0000\u0000\u024d\u024f\u0007\u0005\u0000\u0000\u024e\u0250"+
		"\u0005\"\u0000\u0000\u024f\u024e\u0001\u0000\u0000\u0000\u024f\u0250\u0001"+
		"\u0000\u0000\u0000\u0250\u0252\u0001\u0000\u0000\u0000\u0251\u0253\u0005"+
		"I\u0000\u0000\u0252\u0251\u0001\u0000\u0000\u0000\u0253\u0254\u0001\u0000"+
		"\u0000\u0000\u0254\u0252\u0001\u0000\u0000\u0000\u0254\u0255\u0001\u0000"+
		"\u0000\u0000\u0255\u0257\u0001\u0000\u0000\u0000\u0256\u024c\u0001\u0000"+
		"\u0000\u0000\u0256\u0257\u0001\u0000\u0000\u0000\u0257\u0259\u0001\u0000"+
		"\u0000\u0000\u0258\u025a\u0005\"\u0000\u0000\u0259\u0258\u0001\u0000\u0000"+
		"\u0000\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000\u0000"+
		"\u0000\u025b\u025c\u0005E\u0000\u0000\u025ci\u0001\u0000\u0000\u0000\u025d"+
		"\u025e\u0005\"\u0000\u0000\u025e\u025f\u0005,\u0000\u0000\u025f\u0262"+
		"\u0003l6\u0000\u0260\u0261\u0005-\u0000\u0000\u0261\u0263\u0005I\u0000"+
		"\u0000\u0262\u0260\u0001\u0000\u0000\u0000\u0262\u0263\u0001\u0000\u0000"+
		"\u0000\u0263k\u0001\u0000\u0000\u0000\u0264\u0265\u0007\u0006\u0000\u0000"+
		"\u0265m\u0001\u0000\u0000\u0000\u0266\u0267\u0007\u0007\u0000\u0000\u0267"+
		"o\u0001\u0000\u0000\u0000\u0268\u0269\u0007\b\u0000\u0000\u0269q\u0001"+
		"\u0000\u0000\u0000\u026a\u026b\u0007\t\u0000\u0000\u026bs\u0001\u0000"+
		"\u0000\u0000\u026c\u026d\u0007\n\u0000\u0000\u026du\u0001\u0000\u0000"+
		"\u0000\u026e\u026f\u0007\u000b\u0000\u0000\u026fw\u0001\u0000\u0000\u0000"+
		"A}\u0080\u0084\u008b\u0094\u00a7\u00a9\u00af\u00b3\u00b6\u00bb\u00c1\u00c5"+
		"\u00ca\u00cf\u00d9\u00de\u00e2\u00e6\u00ee\u00f5\u00fa\u00fe\u0102\u0107"+
		"\u0109\u010d\u0118\u0122\u012c\u013d\u0146\u0150\u015a\u0167\u0175\u017d"+
		"\u0188\u018a\u018d\u0195\u019b\u01c8\u01cf\u01da\u01de\u01ea\u01f0\u01f6"+
		"\u01fc\u0208\u0216\u021b\u0229\u0237\u023c\u023e\u0243\u0248\u024a\u024f"+
		"\u0254\u0256\u0259\u0262";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}