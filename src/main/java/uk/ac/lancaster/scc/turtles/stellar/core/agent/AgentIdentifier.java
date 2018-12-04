package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import java.util.Objects;

public class AgentIdentifier {

    private final String name;
    private final String host;
    private final String port;
    
    
    public AgentIdentifier(final String name, final String host, final String port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.host);
        hash = 23 * hash + Objects.hashCode(this.port);
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
        final AgentIdentifier other = (AgentIdentifier) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.host, other.host)) {
            return false;
        }
        return Objects.equals(this.port, other.port);
    }
    
}
