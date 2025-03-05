package JLQNListner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import Entity.Activity;
import Entity.App;
import Entity.Call;
import Entity.AsyncCall;
import Entity.ForkNode;
import Entity.Function;
import Entity.OrNode;
import Entity.ReplyNode;
import JLQN.JLqnListener;
import JLQN.JLqnParser.RuleActAsyncCallContext;
import JLQN.JLqnParser.RuleActConnectionsContext;
import JLQN.JLqnParser.RuleActSyncCallContext;
import JLQN.JLqnParser.RuleActThinkTimeContext;
import JLQN.JLqnParser.RuleActTimeLimitContext;
import JLQN.JLqnParser.RuleActivityCoeffContext;
import JLQN.JLqnParser.RuleActivityContext;
import JLQN.JLqnParser.RuleActivityDemandContext;
import JLQN.JLqnParser.RuleActivityDiagramContext;
import JLQN.JLqnParser.RuleActivityInfoContext;
import JLQN.JLqnParser.RuleActivityPhaseContext;
import JLQN.JLqnParser.RuleAndActContext;
import JLQN.JLqnParser.RuleAndForkContext;
import JLQN.JLqnParser.RuleAndJoinContext;
import JLQN.JLqnParser.RuleAndReplContext;
import JLQN.JLqnParser.RuleAsynchronousCallContext;
import JLQN.JLqnParser.RuleEntryArrivalrateContext;
import JLQN.JLqnParser.RuleEntryCoeffsContext;
import JLQN.JLqnParser.RuleEntryContext;
import JLQN.JLqnParser.RuleEntryDemandContext;
import JLQN.JLqnParser.RuleEntryPhasesContext;
import JLQN.JLqnParser.RuleEntryPriorityContext;
import JLQN.JLqnParser.RuleEntryThinkTimeContext;
import JLQN.JLqnParser.RuleEntryTimeLimitContext;
import JLQN.JLqnParser.RuleExportKindContext;
import JLQN.JLqnParser.RuleExportSettingContext;
import JLQN.JLqnParser.RuleFanInActContext;
import JLQN.JLqnParser.RuleFanInEntryContext;
import JLQN.JLqnParser.RuleFanOutActContext;
import JLQN.JLqnParser.RuleFanOutEntryContext;
import JLQN.JLqnParser.RuleForkListContext;
import JLQN.JLqnParser.RuleForwardEntryContext;
import JLQN.JLqnParser.RuleInfinityRateContext;
import JLQN.JLqnParser.RuleJoinListContext;
import JLQN.JLqnParser.RuleListEntryContext;
import JLQN.JLqnParser.RuleLoopActContext;
import JLQN.JLqnParser.RuleLoopContext;
import JLQN.JLqnParser.RuleModelContext;
import JLQN.JLqnParser.RuleNAT0Context;
import JLQN.JLqnParser.RuleNUMBERContext;
import JLQN.JLqnParser.RuleOrActContext;
import JLQN.JLqnParser.RuleOrForkContext;
import JLQN.JLqnParser.RuleOrJoinContext;
import JLQN.JLqnParser.RuleOrReplContext;
import JLQN.JLqnParser.RuleOutputReqContext;
import JLQN.JLqnParser.RulePOSITIVEINTORREALContext;
import JLQN.JLqnParser.RuleProcessorContext;
import JLQN.JLqnParser.RuleReplyContext;
import JLQN.JLqnParser.RuleScheduleKindContext;
import JLQN.JLqnParser.RuleSolBoolKindContext;
import JLQN.JLqnParser.RuleSolSettingContext;
import JLQN.JLqnParser.RuleSolVarContext;
import JLQN.JLqnParser.RuleSolVarKindContext;
import JLQN.JLqnParser.RuleSolverContext;
import JLQN.JLqnParser.RuleSolverKindContext;
import JLQN.JLqnParser.RuleSynchronousCallContext;
import JLQN.JLqnParser.RuleTaskContext;
import JLQN.JLqnParser.RuleTaskEntryNameContext;
import JLQN.JLqnParser.RuleTaskIdContext;
import JLQN.JLqnParser.RuleTaskKindContext;
import JLQN.JLqnParser.RuleTransitionContext;

public class MyJqnListner implements JLqnListener {

	public App app = null;
	private HashMap<String, String> f2entry=null;
	private HashMap<String, String> entry2mainAct=null;
	private HashMap<String, String> f2type=null;
	
	public MyJqnListner() {
		this.f2entry=new HashMap<String, String>();
		this.entry2mainAct=new HashMap<String, String>();
		this.f2type=new HashMap<String, String>();
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleModel(RuleModelContext ctx) {
		this.app = new App(null);
	}

	@Override
	public void exitRuleModel(RuleModelContext ctx) {
		this.app.setName(ctx.RULE_STRING().getText());
	}

	@Override
	public void enterRuleNUMBER(RuleNUMBERContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleNUMBER(RuleNUMBERContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRulePOSITIVEINTORREAL(RulePOSITIVEINTORREALContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRulePOSITIVEINTORREAL(RulePOSITIVEINTORREALContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleNAT0(RuleNAT0Context ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleNAT0(RuleNAT0Context ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleProcessor(RuleProcessorContext ctx) {
	}

	@Override
	public void exitRuleProcessor(RuleProcessorContext ctx) {
		// System.out.println(ctx.RULE_ID().getText());
	}

	@Override
	public void enterRuleTask(RuleTaskContext ctx) {
		// TODO Auto-generated method stub
	}

	@Override
	public void exitRuleTask(RuleTaskContext ctx) {
		String taskname=ctx.ruleTaskId().RULE_ID().getText();
		String entry=ctx.ruleTaskEntryName().get(0).RULE_ID().getText();
		String kind=ctx.ruleTaskKind().getText();
		this.f2entry.put(taskname, entry);
		this.f2type.put(taskname, kind);
	}

	@Override
	public void enterRuleEntry(RuleEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntry(RuleEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryDemand(RuleEntryDemandContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryDemand(RuleEntryDemandContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSynchronousCall(RuleSynchronousCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSynchronousCall(RuleSynchronousCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleAsynchronousCall(RuleAsynchronousCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleAsynchronousCall(RuleAsynchronousCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivityDiagram(RuleActivityDiagramContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActivityDiagram(RuleActivityDiagramContext ctx) {
		this.entry2mainAct.put(ctx.ruleEntry().RULE_ID().getText(), ctx.RULE_ID().getText());
	}

	@Override
	public void enterRuleEntryArrivalrate(RuleEntryArrivalrateContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryArrivalrate(RuleEntryArrivalrateContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryCoeffs(RuleEntryCoeffsContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryCoeffs(RuleEntryCoeffsContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryPhases(RuleEntryPhasesContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryPhases(RuleEntryPhasesContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleFanInEntry(RuleFanInEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleFanInEntry(RuleFanInEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleFanOutEntry(RuleFanOutEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleFanOutEntry(RuleFanOutEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryPriority(RuleEntryPriorityContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryPriority(RuleEntryPriorityContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryTimeLimit(RuleEntryTimeLimitContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryTimeLimit(RuleEntryTimeLimitContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleForwardEntry(RuleForwardEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleForwardEntry(RuleForwardEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleEntryThinkTime(RuleEntryThinkTimeContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleEntryThinkTime(RuleEntryThinkTimeContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivity(RuleActivityContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActivity(RuleActivityContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivityInfo(RuleActivityInfoContext ctx) {
		// TODO Auto-generated method stub
	}

// Rule ActConnections
//	ruleActConnections:
//		':'
//		ruleTransition *
//		ruleJoinList
//		(
//			'->'
//			ruleForkList
//		)?
//	;
// Rule Transition
//	ruleTransition:
//		ruleJoinList
//		(
//			'->'
//			ruleForkList
//		)?
//		';'
//	;
//
//	// Rule JoinList
//	ruleJoinList:
//		(
//			ruleReply
//			    |
//			ruleAndJoin
//			    |
//			ruleOrJoin
//		)
//	;
//	// Rule ForkList
//	ruleForkList:
//		(
//			RULE_ID
//			    |
//			ruleAndFork
//			    |
//			ruleOrFork
//			    |
//			ruleLoop
//		)
//	;
//
//	// Rule AndFork
//	ruleAndFork:
//		RULE_ID
//		ruleAndAct
//		+
//	;
//
//	// Rule AndAct
//	ruleAndAct:
//		'&'
//		RULE_ID
//	;
//
//	// Rule OrFork
//	ruleOrFork:
//		'('
//		ruleNUMBER
//		')'
//		RULE_ID
//		ruleOrAct
//		+
//	;
	
	private void processRuleTransition(RuleJoinListContext joinList,RuleForkListContext forkList,Function f) {
		String rule = "";
		ArrayList<String> joinAct = new ArrayList<String>();
		ArrayList<String> forkAct = new ArrayList<String>();
		// recupero la join list della transizione
		if (joinList.ruleReply() != null) {
			rule += joinList.ruleReply().RULE_ID().get(0).getText();
			joinAct.add(joinList.ruleReply().RULE_ID().get(0).getText());
			if (joinList.ruleReply().RULE_ID().size() > 1) {
				// in questo caso si parla di un reply vero e proprio
				rule += String.format("[%s]", joinList.ruleReply().RULE_ID().get(1).getText());
				ReplyNode reply = new ReplyNode();
				try {
					reply.setJoinList(joinAct);
					reply.getOrList().add(joinList.ruleReply().RULE_ID().get(1).getText());
					f.getDnodes().add(reply);

					joinAct = null;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		if (joinList.ruleAndJoin() != null) {
			rule += joinList.ruleAndJoin().ruleReply().getText();
			joinAct.add(joinList.ruleAndJoin().ruleReply().getText());
			for (RuleAndReplContext andRepl : joinList.ruleAndJoin().ruleAndRepl()) {
				rule += " & " + andRepl.ruleReply().RULE_ID().get(0).getText();
				joinAct.add(andRepl.ruleReply().RULE_ID().get(0).getText());
			}
		}
		if (joinList.ruleOrJoin() != null) {
			rule += joinList.ruleOrJoin().ruleReply().getText();
			joinAct.add(joinList.ruleOrJoin().ruleReply().getText());
			for (RuleOrReplContext orRepl : joinList.ruleOrJoin().ruleOrRepl()) {
				rule += " + " + orRepl.ruleReply().RULE_ID().get(0).getText();
				joinAct.add(orRepl.ruleReply().RULE_ID().get(0).getText());
			}
		}
		// recupero la forklist
		if (forkList != null) {
			rule += " -> ";
			if (forkList.RULE_ID() != null) {
				rule += forkList.RULE_ID().getText();
				forkAct.add(forkList.RULE_ID().getText());

				OrNode orNode = new OrNode();
				orNode.setJoinList(joinAct);
				orNode.setForkList(forkAct);
				ArrayList<Double> prob = new ArrayList<Double>();
				prob.add(1.0);
				orNode.setProb(prob);
				f.getDnodes().add(orNode);
				forkAct = null;
				joinAct = null;
			}
			if (forkList.ruleAndFork() != null) {
				rule += forkList.ruleAndFork().RULE_ID().getText();
				forkAct.add(forkList.ruleAndFork().RULE_ID().getText());
				for (RuleAndActContext andAct : forkList.ruleAndFork().ruleAndAct()) {
					rule += " & " + andAct.RULE_ID().getText();
					forkAct.add(andAct.RULE_ID().getText());
				}

				ForkNode forkNode = new ForkNode();
				forkNode.setJoinList(joinAct);
				forkNode.setForkList(forkAct);
				f.getDnodes().add(forkNode);
				forkAct = null;
				joinAct = null;
			}
			if (forkList.ruleOrFork() != null) {
				rule += String.format("(%s)%s", forkList.ruleOrFork().ruleNUMBER().getText(),
						forkList.ruleOrFork().RULE_ID().getText());
				ArrayList<Double> prob = new ArrayList<Double>();
				forkAct.add(forkList.ruleOrFork().RULE_ID().getText());
				prob.add(Double.valueOf(forkList.ruleOrFork().ruleNUMBER().getText()));

				for (RuleOrActContext orAct : forkList.ruleOrFork().ruleOrAct()) {
					rule += String.format(" + (%s)%s", orAct.ruleNUMBER().getText(), orAct.RULE_ID().getText());
					forkAct.add(orAct.RULE_ID().getText());
					prob.add(Double.valueOf(orAct.ruleNUMBER().getText()));
				}

				OrNode orNode = new OrNode();
				orNode.setJoinList(joinAct);
				orNode.setForkList(forkAct);
				orNode.setProb(prob);
				f.getDnodes().add(orNode);
				forkAct = null;
				joinAct = null;
			}
		}
		//System.out.println(rule);
	}
	
	private void processRuleConnection(List<RuleTransitionContext> transitions, Function f) {
		for (RuleTransitionContext transition : transitions) {
			RuleJoinListContext joinList = transition.ruleJoinList();
			RuleForkListContext forkList = transition.ruleForkList();
			this.processRuleTransition(joinList, forkList, f);
		}
	}

	@Override
	public void exitRuleActivityInfo(RuleActivityInfoContext ctx) {
		//System.out.println("A " + ctx.RULE_ID().getText());
		Function f = new Function(ctx.RULE_ID().getText());
		this.app.getFunctions().add(f);

		// assegno tutte le activity della funzione
		List<RuleActivityDemandContext> activities = ctx.ruleActivityDemand();
		for (RuleActivityDemandContext act : activities) {
			//System.out.println(act.ruleActivity().getText() + " " + act.ruleNUMBER().getText());
			f.getActivities()
					.add(new Activity(act.ruleActivity().getText(), Double.valueOf(act.ruleNUMBER().getText())));
		}

		// riprendo tutte le chiamate sincrone
		List<RuleActSyncCallContext> syncCalls = ctx.ruleActSyncCall();
		for (RuleActSyncCallContext call : syncCalls) {
			//System.out.println(
					//"y " + call.RULE_ID().get(0) + " " + call.RULE_ID().get(1) + " " + call.ruleNUMBER().getText());
			try {
				Activity act = f.getActivityByName(call.RULE_ID().get(0).getText());
				act.setCall(new Call(call.RULE_ID().get(0).getText(), call.RULE_ID().get(1).getText()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// riprendo tutte le chiamate asincrone
		List<RuleActAsyncCallContext> asyncCalls = ctx.ruleActAsyncCall();
		for (RuleActAsyncCallContext call : asyncCalls) {
			//System.out.println(
					//"y " + call.RULE_ID().get(0) + " " + call.RULE_ID().get(1) + " " + call.ruleNUMBER().getText());
			try {
				Activity act = f.getActivityByName(call.RULE_ID().get(0).getText());
				act.setAsyncCall(new AsyncCall(call.RULE_ID().get(0).getText(), call.RULE_ID().get(1).getText()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// riprendo la logica della funzione
		// qui creo i decision node al'interno della funzione
		RuleActConnectionsContext connections = ctx.ruleActConnections();
		if (connections != null) {
			//System.out.println(":");
			this.processRuleConnection(connections.ruleTransition(),f);
			this.processRuleTransition(connections.ruleJoinList(), connections.ruleForkList(), f);
		}
		
		try {
			f.setMainAct(this.entry2mainAct.get(this.f2entry.get(f.getName())));
			f.setKind(this.f2type.get(f.getName()));
			f.setName(this.f2entry.get(f.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enterRuleActConnections(RuleActConnectionsContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActConnections(RuleActConnectionsContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivityDemand(RuleActivityDemandContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActivityDemand(RuleActivityDemandContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActSyncCall(RuleActSyncCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActSyncCall(RuleActSyncCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivityCoeff(RuleActivityCoeffContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActivityCoeff(RuleActivityCoeffContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActivityPhase(RuleActivityPhaseContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActivityPhase(RuleActivityPhaseContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleFanInAct(RuleFanInActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleFanInAct(RuleFanInActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleFanOutAct(RuleFanOutActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleFanOutAct(RuleFanOutActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActTimeLimit(RuleActTimeLimitContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActTimeLimit(RuleActTimeLimitContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActAsyncCall(RuleActAsyncCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActAsyncCall(RuleActAsyncCallContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleActThinkTime(RuleActThinkTimeContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleActThinkTime(RuleActThinkTimeContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleTransition(RuleTransitionContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleTransition(RuleTransitionContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleJoinList(RuleJoinListContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleJoinList(RuleJoinListContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleListEntry(RuleListEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleListEntry(RuleListEntryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleReply(RuleReplyContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleReply(RuleReplyContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleAndRepl(RuleAndReplContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleAndRepl(RuleAndReplContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleOrRepl(RuleOrReplContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleOrRepl(RuleOrReplContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleAndJoin(RuleAndJoinContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleAndJoin(RuleAndJoinContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleOrJoin(RuleOrJoinContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleOrJoin(RuleOrJoinContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleForkList(RuleForkListContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleForkList(RuleForkListContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleAndFork(RuleAndForkContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleAndFork(RuleAndForkContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleAndAct(RuleAndActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleAndAct(RuleAndActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleOrFork(RuleOrForkContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleOrFork(RuleOrForkContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleOrAct(RuleOrActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleOrAct(RuleOrActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleLoop(RuleLoopContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleLoop(RuleLoopContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleLoopAct(RuleLoopActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleLoopAct(RuleLoopActContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleInfinityRate(RuleInfinityRateContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleInfinityRate(RuleInfinityRateContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolver(RuleSolverContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolver(RuleSolverContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolVar(RuleSolVarContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolVar(RuleSolVarContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolSetting(RuleSolSettingContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolSetting(RuleSolSettingContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleOutputReq(RuleOutputReqContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleOutputReq(RuleOutputReqContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleExportSetting(RuleExportSettingContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleExportSetting(RuleExportSettingContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleExportKind(RuleExportKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleExportKind(RuleExportKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolverKind(RuleSolverKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolverKind(RuleSolverKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolVarKind(RuleSolVarKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolVarKind(RuleSolVarKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleSolBoolKind(RuleSolBoolKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleSolBoolKind(RuleSolBoolKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleTaskKind(RuleTaskKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleTaskKind(RuleTaskKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleScheduleKind(RuleScheduleKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleScheduleKind(RuleScheduleKindContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleTaskId(RuleTaskIdContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleTaskId(RuleTaskIdContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterRuleTaskEntryName(RuleTaskEntryNameContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRuleTaskEntryName(RuleTaskEntryNameContext ctx) {
		// TODO Auto-generated method stub

	}

}
