package netbill.generated;

public enum NetBillMessageName {

	_rfq("rfq"),
	_quote("quote"),
	_acceptQuote("acceptQuote"),
	_rejectQuote("rejectQuote"),
	_charge("charge"),
	_transfer("transfer"),
	_deny("deny"),
	_goods("goods"),
	_error("error");

    private final java.lang.String canonical;

    private NetBillMessageName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
