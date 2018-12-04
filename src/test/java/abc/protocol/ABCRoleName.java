package abc.protocol;

public enum ABCRoleName {

    _AgentA("AgentA"),
    _AgentB("AgentB"),
    _AgentC("AgentC");

    private final String canonical;

    private ABCRoleName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
}
