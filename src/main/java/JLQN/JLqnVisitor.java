// Generated from /Users/emilio-imt/eclipse-workspace/jLQN/src/main/resources/grammar/JLqn.g by ANTLR 4.13.1
package JLQN;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JLqnParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JLqnVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleModel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleModel(JLqnParser.RuleModelContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleNUMBER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleNUMBER(JLqnParser.RuleNUMBERContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#rulePOSITIVEINTORREAL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRulePOSITIVEINTORREAL(JLqnParser.RulePOSITIVEINTORREALContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleNAT0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleNAT0(JLqnParser.RuleNAT0Context ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleProcessor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleProcessor(JLqnParser.RuleProcessorContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleTask}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleTask(JLqnParser.RuleTaskContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleTaskId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleTaskId(JLqnParser.RuleTaskIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntry(JLqnParser.RuleEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryDemand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryDemand(JLqnParser.RuleEntryDemandContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSynchronousCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSynchronousCall(JLqnParser.RuleSynchronousCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleAsynchronousCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAsynchronousCall(JLqnParser.RuleAsynchronousCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivityDiagram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivityDiagram(JLqnParser.RuleActivityDiagramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryArrivalrate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryArrivalrate(JLqnParser.RuleEntryArrivalrateContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryCoeffs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryCoeffs(JLqnParser.RuleEntryCoeffsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryPhases}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryPhases(JLqnParser.RuleEntryPhasesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleFanInEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFanInEntry(JLqnParser.RuleFanInEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleFanOutEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFanOutEntry(JLqnParser.RuleFanOutEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryPriority}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryPriority(JLqnParser.RuleEntryPriorityContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryTimeLimit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryTimeLimit(JLqnParser.RuleEntryTimeLimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleForwardEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleForwardEntry(JLqnParser.RuleForwardEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleEntryThinkTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleEntryThinkTime(JLqnParser.RuleEntryThinkTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivity}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivity(JLqnParser.RuleActivityContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivityInfo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivityInfo(JLqnParser.RuleActivityInfoContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActConnections}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActConnections(JLqnParser.RuleActConnectionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivityDemand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivityDemand(JLqnParser.RuleActivityDemandContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActSyncCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActSyncCall(JLqnParser.RuleActSyncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivityCoeff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivityCoeff(JLqnParser.RuleActivityCoeffContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActivityPhase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActivityPhase(JLqnParser.RuleActivityPhaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleFanInAct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFanInAct(JLqnParser.RuleFanInActContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleFanOutAct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleFanOutAct(JLqnParser.RuleFanOutActContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActTimeLimit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActTimeLimit(JLqnParser.RuleActTimeLimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActAsyncCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActAsyncCall(JLqnParser.RuleActAsyncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleActThinkTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleActThinkTime(JLqnParser.RuleActThinkTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleTransition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleTransition(JLqnParser.RuleTransitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleJoinList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleJoinList(JLqnParser.RuleJoinListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleListEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleListEntry(JLqnParser.RuleListEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleReply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleReply(JLqnParser.RuleReplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleAndRepl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAndRepl(JLqnParser.RuleAndReplContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleOrRepl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleOrRepl(JLqnParser.RuleOrReplContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleAndJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAndJoin(JLqnParser.RuleAndJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleOrJoin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleOrJoin(JLqnParser.RuleOrJoinContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleForkList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleForkList(JLqnParser.RuleForkListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleAndFork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAndFork(JLqnParser.RuleAndForkContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleAndAct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleAndAct(JLqnParser.RuleAndActContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleOrFork}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleOrFork(JLqnParser.RuleOrForkContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleOrAct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleOrAct(JLqnParser.RuleOrActContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleLoop(JLqnParser.RuleLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleLoopAct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleLoopAct(JLqnParser.RuleLoopActContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleInfinityRate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleInfinityRate(JLqnParser.RuleInfinityRateContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolver(JLqnParser.RuleSolverContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolVar(JLqnParser.RuleSolVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolSetting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolSetting(JLqnParser.RuleSolSettingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleOutputReq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleOutputReq(JLqnParser.RuleOutputReqContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleExportSetting}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleExportSetting(JLqnParser.RuleExportSettingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleExportKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleExportKind(JLqnParser.RuleExportKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolverKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolverKind(JLqnParser.RuleSolverKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolVarKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolVarKind(JLqnParser.RuleSolVarKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleSolBoolKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleSolBoolKind(JLqnParser.RuleSolBoolKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleTaskKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleTaskKind(JLqnParser.RuleTaskKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JLqnParser#ruleScheduleKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleScheduleKind(JLqnParser.RuleScheduleKindContext ctx);
}