package abc.agents;

import abc.protocol.AAgent;
import abc.protocol.EnabledMsgA;
import abc.protocol.MsgC;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class ExampleAAgent extends AAgent implements Runnable {

    private final AgentIdentifier selfIdentifier;  // This agent's indetifier
    private final AgentIdentifier bIdentifier;     // BAgent's identifier to whom this agent sends MsgA

    // This agent sends 5 MsgA messages and stops.
    private int cnt;
    private final int MAX_CNT = 5;
    
    public ExampleAAgent(AgentIdentifier selfIdentifier, AgentIdentifier bIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.bIdentifier = bIdentifier;
        cnt = 1;
    }
    
    @Override
    protected void handleMsgC(MsgC receivedMessage) {
        // This handler is called by Stellar everytime when MsgC is received.
        // Calls to this handler are NOT concurrent with any call to act()
        // method (see below), which means that there is no need for synchronization.
        System.out.println(selfIdentifier.getName() + " received " + receivedMessage.getName() + "[" + 
                receivedMessage.bindingOfId() + ", " + 
                receivedMessage.bindingOfP2() + ", " +
                receivedMessage.bindingOfP3() + "]");
        cnt++;
        if (cnt <= MAX_CNT) {
            EnabledMsgA enabledMsgA = retrieveEnabledMsgA();
            sendEnabledMsgA(enabledMsgA, "p1-" + cnt, bIdentifier);
            System.out.println(selfIdentifier.getName() + " sent new " + enabledMsgA.getName() + " with p1 = p1-" + cnt);
        } else {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        // This handler is called by Stellar only once before the agent starts.
        // No messages can be sent or received here.
        // This method can be used for initializations specific to this agent
        // or can be left empty if no such initialization is needed.
        System.out.println(selfIdentifier.getName() + " is ready to start.");
    }

    @Override
    protected void afterStart() {
        // This handler is called by Stellar only once when the agent starts.
        // Agent can send messages here.
        // Agent does not recieve any message until this method returns.
        EnabledMsgA enabledMsgA = retrieveEnabledMsgA();
        sendEnabledMsgA(enabledMsgA, "p1-" + cnt, bIdentifier);
        System.out.println(selfIdentifier.getName() + " sent first " + enabledMsgA.getName() + " with p1 = p1-" + cnt);
    }

    @Override
    protected void act() {
        // This handler is called at least once by Stellar after the reception of
        // a message. If the agent does not receive any message this handler
        // is repeatedly called until the reception of a message or termination
        // of the agent.
        // This method can be used to implement the agent's business logic in
        // addition to the specific message handlers.
        // Calls to this handler are NOT concurrent with any call to any other handler,
        // which means that there is no need for synchronization.
        try {
            // This agent has nothing to do and sleeps for half a second.
            Thread.sleep(500);
        } catch (InterruptedException ex) {
           
        }
    }

    @Override
    protected void beforeStop() {
        // This handler is called by Stellar only once before the agent stops.
        // No messages are received once this handler is called, but the 
        // agent can still send messages within this handler.
        // This method can be used to send remaining messages before the agent 
        // stops or can be left empty if there are no such messages.
        System.out.println(selfIdentifier.getName() + " is going to stop.");
    }

    @Override
    protected void afterStop() {
        // This handler is called by Stellar only once when the agent stops.
        // No messages can be sent or received here.
        // This method can be used to release resource that are specific to this
        // agent or can be left empty if no such clean-up is needed.
        System.out.println(selfIdentifier.getName() + " has stopped.");
    }

    @Override
    public void run() {
        // This method is needed to implement Runnable.
        // The call to execute() initiates the agent's execution.
        execute();
    }

}
