package uk.ac.lancaster.scc.turtles.stellar.core.util;

import java.util.UUID;

public class IdentifierGenerator {

    private long incrementalIdentifier;

    public IdentifierGenerator() {
        incrementalIdentifier = 0;
    }
    
    public void setInitial(long initialValue) {
        if (initialValue == Long.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        incrementalIdentifier = initialValue;
    }
    
    public String unique() {
        return UUID.randomUUID().toString();
    }
    
    public String incremental() {
        return incremental("");
    }
    
    public String incremental(String prefix) {
        if (incrementalIdentifier == Long.MAX_VALUE) {
            throw new IllegalStateException();
        }
        return prefix + Long.toString(incrementalIdentifier++);
    }
}
