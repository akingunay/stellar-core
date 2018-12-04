package abc.agents;

import abc.protocol.CAgent;
import abc.protocol.EnabledMsgC;
import abc.protocol.MsgB;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class ExampleCAgent  extends CAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier aIdentifier;

    private int cnt;
    private final int MAX_CNT = 5;
     
    public ExampleCAgent(AgentIdentifier selfIdentifier, AgentIdentifier aIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.aIdentifier = aIdentifier;
        cnt = 1;
    }
    
    @Override
    protected void handleMsgB(MsgB receivedMessage) {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " received " + receivedMessage.getName() + "[" + 
                receivedMessage.bindingOfId() + ", " + 
                receivedMessage.bindingOfP1() + ", " +
                receivedMessage.bindingOfP2() + "]");
        // Since this agent sends MsgC immediately after each received MsgB
        // there can only be a sinle enabled MsgC when the following retrieve
        // method is called. Hence, use get(0) to get the first and only
        // enabled message from the list that is returned by retrieve method.
        EnabledMsgC enabledMsgC = retrieveEnabledMsgC().get(0);
        sendEnabledMsgC(enabledMsgC, "p3-" + cnt, aIdentifier);
        System.out.println(selfIdentifier.getName() + " sent new " + enabledMsgC.getName() + " with" +
                " p2 = " + receivedMessage.bindingOfP2() + 
                " and p3 = p3-" + cnt);
                cnt++;
        if (MAX_CNT < cnt) {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " is ready to start.");
    }

    @Override
    protected void afterStart() {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " has started.");
    }

    @Override
    protected void act() {
        // See ExampleAgentA for explanations
        try {
            // This agent has nothing to do and sleeps for half a second.
            Thread.sleep(500);
        } catch (InterruptedException ex) {
           
        }
    }

    @Override
    protected void beforeStop() {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " is going to stop.");
    }

    @Override
    protected void afterStop() {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " has stopped.");
    }

    @Override
    public void run() {
        // This method is needed to implement Runnable.
        // The call to execute() initiates the agent's execution.
        execute();
    }

}
