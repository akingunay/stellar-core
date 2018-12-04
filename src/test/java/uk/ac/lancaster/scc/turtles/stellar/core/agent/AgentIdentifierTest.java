package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import org.junit.Test;
import static org.junit.Assert.*;

public class AgentIdentifierTest {

    @Test
    public void createFromNameHostPort() {
        AgentIdentifier identifier = new AgentIdentifier("test-agent", "127.0.0.1", "3169");
        assertEquals("test-agent", identifier.getName());
        assertEquals("127.0.0.1", identifier.getHost());
        assertEquals("3169", identifier.getPort());
    }
    
    @Test
    public void testEquality() {
        AgentIdentifier identifierA1 = new AgentIdentifier("A", "127.0.0.1", "3169");
        AgentIdentifier identifierA2 = new AgentIdentifier("A", "127.0.0.1", "3169");
        AgentIdentifier identifierB = new AgentIdentifier("B", "127.0.0.1", "3169");
        AgentIdentifier identifierC = new AgentIdentifier("C", "127.0.0.2", "3169");
        AgentIdentifier identifierD = new AgentIdentifier("D", "127.0.0.1", "3170");
        AgentIdentifier identifierE = new AgentIdentifier("E", "192.168.1.1", "3200");
        assertTrue(identifierA1.equals(identifierA2));
        assertFalse(identifierA1.equals(identifierB));
        assertFalse(identifierA1.equals(identifierC));
        assertFalse(identifierA1.equals(identifierD));
        assertFalse(identifierA1.equals(identifierE));
    }

    @Test
    public void testHashCode() {
        AgentIdentifier identifierA1 = new AgentIdentifier("A", "127.0.0.1", "3169");
        AgentIdentifier identifierA2 = new AgentIdentifier("A", "127.0.0.1", "3169");
        AgentIdentifier identifierB1 = new AgentIdentifier("B", "192.168.1.1", "4158");
        AgentIdentifier identifierB2 = new AgentIdentifier("B", "192.168.1.1", "4158");
        assertEquals(identifierA1.hashCode(), identifierA2.hashCode());
        assertEquals(identifierB1.hashCode(), identifierB2.hashCode());
    }
}