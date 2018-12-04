package soc.agents;

import soc.assignment.AcceptGrade;
import soc.assignment.AssignmentParameterName;
import soc.assignment.EnabledPostGrade;
import soc.assignment.EnabledRegrade;
import soc.assignment.PostSubmission;
import soc.assignment.RequestRegrade;
import soc.assignment.TAgent;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class TAgentImp extends TAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;  // This agent's indetifier
    private final AgentIdentifier sIdentifier;

    private static final int MAX_CNT = 3;
    private int tCnt;
    private int aCnt;
    private int rCnt;
    
    public TAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier sIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sIdentifier = sIdentifier;
        this.tCnt = 1;
        this.rCnt = 1;
        this.aCnt = 0;
    }
    
    @Override
    protected void handlePostSubmission(PostSubmission receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + ", " +
                receivedMessage.bindingOfSubmission() + "]" +
                " FROM" + receivedMessage.getSender().getName());
        EnabledPostGrade enabled = retrieveEnabledPostGrade().get(0);
        String tentative = "t-"+ tCnt++;
        sendEnabledPostGrade(enabled, tentative, sIdentifier);
        System.out.println(selfIdentifier.getName() + " SENT " +
                enabled.getName() + "[" +
                enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                enabled.getBinding(AssignmentParameterName._submission.canonical()) + ", " +
                tentative + "]" +
                " TO " + sIdentifier.getName());
    }

    @Override
    protected void handleAcceptGrade(AcceptGrade receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + ", " +
                receivedMessage.bindingOfTentativeGrade() + ", " +
                receivedMessage.bindingOfGrade()+ ", " +
                receivedMessage.bindingOfRegradeReason() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        aCnt++;
        if (MAX_CNT == aCnt) {
            stop();
        }
    }

    @Override
    protected void handleRequestRegrade(RequestRegrade receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + ", " +
                receivedMessage.bindingOfTentativeGrade() + ", " +
                receivedMessage.bindingOfRegradeReason() + ", " +
                receivedMessage.bindingOfGrade() + "]" +
                " FROM " + receivedMessage.getSender().getName());
        EnabledRegrade enabled = retrieveEnabledRegrade().get(0);
        String regrade = "g-" + rCnt++;
        sendEnabledRegrade(enabled, regrade, sIdentifier);
        System.out.println(selfIdentifier.getName() + " SENT " + 
                enabled.getName() + "[" + 
                receivedMessage.bindingOfAID() + ", " +
                enabled.getBinding(AssignmentParameterName._regradeReason.canonical()) + ", " +
                regrade + "]" +
                " TO " + sIdentifier.getName());
        aCnt++;
        if (MAX_CNT == aCnt) {
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
        try {
            Thread.sleep(1000);
            System.out.println(selfIdentifier.getName() + " sleeping");
        } catch (InterruptedException e) {
            
        }
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
