package abc.protocol;

public enum ABCParameterName {

    _id("id"),
    _p1("p1"),
    _p2("p2"),
    _p3("p3");

    private final String canonical;

    private ABCParameterName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
}
