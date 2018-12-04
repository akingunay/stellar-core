package soc.agents;

import soc.assignment.AssignmentParameterName;
import soc.assignment.EnabledAcceptGrade;
import soc.assignment.EnabledPostSubmission;
import soc.assignment.EnabledRequestExtension;
import soc.assignment.EnabledRequestRegrade;
import soc.assignment.Extension;
import soc.assignment.PostAssignment;
import soc.assignment.PostGrade;
import soc.assignment.Regrade;
import soc.assignment.SAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class SAgentImp extends SAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;  // This agent's indetifier
    private final AgentIdentifier iIdentifier;
    private final AgentIdentifier tIdentifier;

    private final int MAX_CNT = 3;
    private int eCnt;
    private int sCnt;
    private int aCnt;
    private int rCnt;
    private int wCnt;
    private boolean pa;
    private boolean pg;
    
    public SAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier iIdentifier, AgentIdentifier tIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.iIdentifier = iIdentifier;
        this.tIdentifier = tIdentifier;
        this.eCnt = 1;
        this.sCnt = 1;
        this.aCnt = 1;
        this.rCnt = 1;
        this.wCnt = 0;
        this.pa = false;
        this.pg = false;
    }
    
    @Override
    protected void handlePostAssignment(PostAssignment receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        if (pa) {
            String submit = "s-" + sCnt++;
            EnabledPostSubmission enabled = retrieveEnabledPostSubmission().get(0);
            sendEnabledPostSubmission(enabled, submit, tIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT " +
                    enabled.getName() + "[" +
                    enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                    submit + "] " +
                    " TO " + tIdentifier.getName());
            pa = !pa;
        } else{
            String ereason = "e-" + eCnt++;
            EnabledRequestExtension enabled = retrieveEnabledRequestExtension().get(0);
            sendEnabledRequestExtension(enabled, ereason, iIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT " + 
                    enabled.getName() + "[" +
                    enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                    ereason + ", " +
                    enabled.getBinding(AssignmentParameterName._submission.canonical()) + "]" +
                    " TO " + iIdentifier.getName()); 
            pa = !pa;
        }
    }

    @Override
    protected void handleExtension(Extension receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + "," +
                receivedMessage.bindingOfExtensionReason() + ", " +
                receivedMessage.bindingOfExtension() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        String submit = "s-" + sCnt++;
        EnabledPostSubmission enabled = retrieveEnabledPostSubmission().get(0);
        sendEnabledPostSubmission(enabled, submit, tIdentifier);
        System.out.println(selfIdentifier.getName() + " SENT " +
                enabled.getName() + "[" +
                enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                submit + "] " +
                " TO " + tIdentifier.getName());
    }

    @Override
    protected void handlePostGrade(PostGrade receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + ", " +
                receivedMessage.bindingOfSubmission() + ", " +
                receivedMessage.bindingOfTentativeGrade() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        if (pg) {
            EnabledAcceptGrade enabled = retrieveEnabledAcceptGrade().get(0);
            String grade = "g-" + aCnt++;
            sendEnabledAcceptGrade(enabled, grade, tIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT " +
                enabled.getName() + "[" +
                enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                enabled.getBinding(AssignmentParameterName._tentativeGrade.canonical()) + ", " +
                grade + ", " +
                enabled.getBinding(AssignmentParameterName._regradeReason.canonical()) + "]" +
                " TO " + tIdentifier.getName());
            wCnt++;
            pg = !pg;
        } else {
            EnabledRequestRegrade enabled = retrieveEnabledRequestRegrade().get(0);
            String reason = "r-" + rCnt++;
            sendEnabledRequestRegrade(enabled, reason, tIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT " +
                enabled.getName() + "[" +
                enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                enabled.getBinding(AssignmentParameterName._tentativeGrade.canonical()) + ", " +
                reason + ", " +
                enabled.getBinding(AssignmentParameterName._grade.canonical()) + "]" +
                " TO " + tIdentifier.getName());
            pg = !pg;
        }
        if (wCnt == MAX_CNT) {
            stop();
        }
    }

    @Override
    protected void handleRegrade(Regrade receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + "," +
                receivedMessage.bindingOfRegradeReason() + ", " +
                receivedMessage.bindingOfGrade() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        wCnt++;
        if (wCnt == MAX_CNT) {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        
    }

    @Override
    protected void afterStart() {
        
    }

    @Override
    protected void act() {
    }

    @Override
    protected void beforeStop() {
        
    }

    @Override
    protected void afterStop() {
        
    }

    @Override
    public void run() {
        execute();
    }
    
}
