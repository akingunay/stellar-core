package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

public enum PurchaseParameterName {

    _id("id"),
    _item("item"),
    _price("price"),
    _response("response"),
    _address("address"),
    _done("done");

    private final String canonical;

    private PurchaseParameterName(final String canonical) {
        this.canonical = canonical;
    }

    public String canonical() {
        return canonical;
    }
}
