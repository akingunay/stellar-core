package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;

public class BSPLMessageTest {

    private final static String JSON_STRING = "{\"mName\":\"m\","
                + "\"mSender\":{\"name\":\"s\",\"host\":\"hs\",\"port\":\"ps\"},"
                + "\"mReceiver\":{\"name\":\"r\",\"host\":\"hr\",\"port\":\"pr\"},"
                + "\"mParameters\":[\"p1\",\"p2\"],\"mBindings\":[\"v1\",\"v2\"]}";
    
    @Test
    public void toJSON() {
        BSPLMessage msg = new BSPLMessage("m", new AgentIdentifier("s", "hs", "ps"), new AgentIdentifier("r", "hr", "pr"), testBindings());
        assertEquals(JSON_STRING, msg.toJSON());
    }
    
    @Test
    public void constructFromJSON() {
        BSPLMessage msg = new BSPLMessage(JSON_STRING);
        assertEquals(new BSPLMessage("m", new AgentIdentifier("s", "hs", "ps"), new AgentIdentifier("r", "hr", "pr"), testBindings()), msg);
    }

    private Map<String, String> testBindings() {
        Map<String, String> bindings = new HashMap<>();
        bindings.put("p1", "v1");
        bindings.put("p2", "v2");
        return bindings;
    }
    
}