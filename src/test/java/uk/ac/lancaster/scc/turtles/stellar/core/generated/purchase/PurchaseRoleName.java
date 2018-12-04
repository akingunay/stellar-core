package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

public enum PurchaseRoleName {

    _merchant("merchant"),
    _customer("customer");

    private final String canonical;

    private PurchaseRoleName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
}
