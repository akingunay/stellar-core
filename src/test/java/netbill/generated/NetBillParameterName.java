package netbill.generated;

public enum NetBillParameterName {

	_pid("pid"),
	_item("item"),
	_price("price"),
	_quoteResponse("quoteResponse"),
	_ccard("ccard"),
	_outcome("outcome"),
	_account("account"),
	_chargeResponse("chargeResponse"),
	_receipt("receipt"),
	_denialReason("denialReason");

    private final java.lang.String canonical;

    private NetBillParameterName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
