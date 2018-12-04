package soc.agents;

import soc.assignment.AssignmentParameterName;
import soc.assignment.EnabledExtension;
import soc.assignment.EnabledPostAssignment;
import soc.assignment.IAgent;
import soc.assignment.RequestExtension;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class IAgentImp extends IAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;  // This agent's indetifier
    private final AgentIdentifier sIdentifier;

    private final int MAX_CNT = 3;
    private int aCnt;
    private int wCnt;
    private int eCnt;
    
    public IAgentImp(AgentIdentifier selfIdentifier, AgentIdentifier sIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sIdentifier = sIdentifier;
        this.aCnt = 0;
        this.wCnt = 0;
        this.eCnt = 1;
    }
    
    @Override
    protected void handleRequestExtension(RequestExtension receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECV " + 
                receivedMessage.getName() + "[" +
                receivedMessage.bindingOfAID() + ", " +
                receivedMessage.bindingOfExtensionReason() + ", " +
                receivedMessage.bindingOfSubmission() +"]" +
                " FROM " + receivedMessage.getSender().getName());
        EnabledExtension enabled = retrieveEnabledExtension().get(0);
        String extension = "e-" + eCnt++;
        sendEnabledExtension(enabled, extension, sIdentifier);
        System.out.println(selfIdentifier.getName() + " SENT " +
                enabled.getName() + "[" +
                enabled.getBinding(AssignmentParameterName._aID.canonical()) + ", " +
                enabled.getBinding(AssignmentParameterName._extensionReason.canonical()) + ", " +
                extension + "]" +
                " TO " + sIdentifier.getName());
    }

    @Override
    protected void beforeStart() {
        
    }

    @Override
    protected void afterStart() {
        EnabledPostAssignment enabled = retrieveEnabledPostAssignment();
        sendEnabledPostAssignment(enabled, sIdentifier);
        System.out.println(selfIdentifier.getName() + " SENT " + enabled.getName() + "[] TO " + sIdentifier.getName());
        aCnt++;
    }

    @Override
    protected void act() {
        if (aCnt < MAX_CNT) {
            EnabledPostAssignment enabled = retrieveEnabledPostAssignment();
            sendEnabledPostAssignment(enabled, sIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT " + enabled.getName() + "[] TO " + sIdentifier.getName());
            aCnt++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            
            }
        } else if (wCnt < MAX_CNT * 3) {
             try {
                Thread.sleep(1000);
            System.out.println(selfIdentifier.getName() + " WAITING");
            wCnt++;
            } catch (InterruptedException ex) {
            
            }   
        } else {
            System.out.println(selfIdentifier.getName() + " STOPING");
            stop();
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
