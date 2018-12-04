package abc.agents;

import abc.protocol.BAgent;
import abc.protocol.EnabledMsgB;
import abc.protocol.MsgA;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class ExampleBAgent extends BAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;
    private final AgentIdentifier cIdentifier;

    private int cnt;
    private final int MAX_CNT = 5;
    
    public ExampleBAgent(AgentIdentifier selfIdentifier, AgentIdentifier cIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.cIdentifier = cIdentifier;
        cnt = 1;
    }
    
    @Override
    protected void handleMsgA(MsgA receivedMessage) {
        // See ExampleAgentA for explanations
        System.out.println(selfIdentifier.getName() + " received " + receivedMessage.getName() + "[" + 
                receivedMessage.bindingOfId() + ", " + 
                receivedMessage.bindingOfP1() + "]");
        // Since this agent sends MsgB immediately after each received MsgA
        // there can only be a sinle enabled MsgB when the following retrieve
        // method is called. Hence, use get(0) to get the first and only
        // enabled message from the list that is returned by retrieve method.
        EnabledMsgB enabledMsgB = retrieveEnabledMsgB().get(0);
        sendEnabledMsgB(enabledMsgB, "p2-" + cnt, cIdentifier);
        System.out.println(selfIdentifier.getName() + " sent new " + enabledMsgB.getName() + " with" +
                " p1 = " + receivedMessage.bindingOfP1() + 
                " and p2 = p2-" + cnt);
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
