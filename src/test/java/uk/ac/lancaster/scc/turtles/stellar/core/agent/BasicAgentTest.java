package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicAgentTest {

    @Test
    public void terminatesWithoutReceivingMessage() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithoutReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentTerminatesWithoutReceivingMessage(endpoint);
        agent.execute();
        assertTrue(endpoint.isStarted());
        assertTrue(endpoint.isStoped());
        assertEquals(endpoint.getPollCount(), 1);
        assertEquals(endpoint.getSendCount(), 0);
        assertEquals(agent.getHandleMessageCount(), 0);
        assertEquals(agent.getBeforeStartCount(), 1);
        assertEquals(agent.getAfterStartCount(), 1);
        assertEquals(agent.getBeforeStopCount(), 1);
        assertEquals(agent.getAfterStopCount(), 1);
        assertEquals(agent.getActCount(), 1);
    }
    
    @Test
    public void receivesMessageAndTerminates() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentReceivesMessageAndTerminates(endpoint);
        agent.execute();
        assertTrue(endpoint.isStarted());
        assertTrue(endpoint.isStoped());
        assertEquals(endpoint.getPollCount(), 1);
        assertEquals(endpoint.getSendCount(), 0);
        assertEquals(agent.getHandleMessageCount(), 1);
        assertEquals(agent.getBeforeStartCount(), 1);
        assertEquals(agent.getAfterStartCount(), 1);
        assertEquals(agent.getBeforeStopCount(), 1);
        assertEquals(agent.getAfterStopCount(), 1);
        assertEquals(agent.getActCount(), 0);
    }
    
    @Test
    public void responsesReceivedMessageAndTerminates() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentResponsesReceivedMessageAndTerminates(endpoint);
        agent.execute();
        assertTrue(endpoint.isStarted());
        assertTrue(endpoint.isStoped());
        assertEquals(endpoint.getPollCount(), 1);
        assertEquals(endpoint.getSendCount(), 1);
        assertEquals(agent.getHandleMessageCount(), 1);
        assertEquals(agent.getBeforeStartCount(), 1);
        assertEquals(agent.getAfterStartCount(), 1);
        assertEquals(agent.getBeforeStopCount(), 1);
        assertEquals(agent.getAfterStopCount(), 1);
        assertEquals(agent.getActCount(), 0);
    }
    
    @Test
    public void responsesThreeReceivedMessagesAndTerminates() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentResponsesThreeReceivedMessagesAndTerminates(endpoint);
        agent.execute();
        assertTrue(endpoint.isStarted());
        assertTrue(endpoint.isStoped());
        assertEquals(endpoint.getPollCount(), 3);
        assertEquals(endpoint.getSendCount(), 3);
        assertEquals(agent.getHandleMessageCount(), 3);
        assertEquals(agent.getBeforeStartCount(), 1);
        assertEquals(agent.getAfterStartCount(), 1);
        assertEquals(agent.getBeforeStopCount(), 1);
        assertEquals(agent.getAfterStopCount(), 1);
        assertEquals(agent.getActCount(), 3);
    }
    
    @Test
    public void sendingMessageInAfterStartAndActAndBeforeStopIsEnabled() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithoutReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentSendsMessageInAfterStartAndActAndBeforeStop(endpoint);
        agent.execute();
    }
    
    @Test(expected = IllegalStateException.class)
    public void sendingMessageInBeforeStartThrowsIllegalStateException() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithoutReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentSendsMessageInBeforeStart(endpoint);
        agent.execute();
        
    }
    
    @Test(expected = IllegalStateException.class)
    public void sendingMessageInAfterStopThrowsIllegalStateException() {
        ProtocolEndpointSpy endpoint = new ProtocolEndpointSpyWithoutReceivedMessage();
        BasicAgentTestImp agent = new BasicAgentSendsMessageInAfterStop(endpoint);
        agent.execute();
        
    }
    
}