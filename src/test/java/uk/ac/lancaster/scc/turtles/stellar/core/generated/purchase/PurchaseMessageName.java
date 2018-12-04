package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

public enum PurchaseMessageName {

    _rfq("rfq"),
    _quote("quote"),
    _accept("accept"),
    _reject("reject"),
    _delivery("delivery");

    private final String canonical;

    private PurchaseMessageName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
    
}
