package abc.protocol;


public enum ABCMessageName {

    _MsgA("msga"),
    _MsgB("msgb"),
    _MsgC("msgc");

    private final String canonical;

    private ABCMessageName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
    
}
