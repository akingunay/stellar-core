package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;

public class BSPLMessage implements Message {

    private final String name;
    private final AgentIdentifier sender;
    private final AgentIdentifier receiver;
    private final Map<String, String> bindings;

    protected BSPLMessage(final String name, final AgentIdentifier sender, final AgentIdentifier receiver, final Map<String, String> bindings) {
        this.name = name;
        this.sender = sender;
        this.receiver = receiver;
        this.bindings = new HashMap<>(bindings);
    }
    
    protected BSPLMessage(final BSPLMessage message) {
        this.name = message.name;
        this.sender = message.sender;
        this.receiver = message.receiver;
        this.bindings = new HashMap<>(message.bindings);
    }
    
    protected BSPLMessage(final String jsonString) {
        Gson gson = new Gson();
        JSONRepresentation jsonMsg = gson.fromJson(jsonString, JSONRepresentation.class);
        this.name = jsonMsg.mName;
        this.sender = jsonMsg.mSender;
        this.receiver = jsonMsg.mReceiver;
        this.bindings = new HashMap<>();
        for (int i = 0 ; i < jsonMsg.mParameters.length ; i++) {
            this.bindings.put(jsonMsg.mParameters[i], jsonMsg.mBindings[i]);
        }
    }
    
    String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(new JSONRepresentation());
    }

    public String getName() {
        return name;
    }

    public String getBinding(String parameter) {
        return bindings.get(parameter);
    }

    public AgentIdentifier getSender() {
        return sender;
    }
    
    public AgentIdentifier getReceiver() {
        return receiver;
    }
    
    protected Map<String, String> getBindings() {
        return Collections.unmodifiableMap(bindings);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.sender);
        hash = 71 * hash + Objects.hashCode(this.receiver);
        hash = 71 * hash + Objects.hashCode(this.bindings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BSPLMessage other = (BSPLMessage) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.sender, other.sender)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        return Objects.equals(this.bindings, other.bindings);
    }
    
    private class JSONRepresentation {
        
        String mName = name;
        AgentIdentifier mSender = sender;
        AgentIdentifier mReceiver = receiver;
        String[] mParameters;
        String[] mBindings;
        
        public JSONRepresentation() {
            mParameters = new String[bindings.keySet().size()];
            mBindings = new String[bindings.values().size()];
            int i = 0;
            for (String parameter : bindings.keySet()) {
                mParameters[i] = parameter;
                mBindings[i] = bindings.get(parameter);
                i++;
            }
        }
    }
    
}
