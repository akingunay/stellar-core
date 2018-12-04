package netbill.generated;

public enum NetBillRoleName {

	_Buyer("Buyer"),
	_Seller("Seller"),
	_Bank("Bank");

    private final java.lang.String canonical;

    private NetBillRoleName(java.lang.String canonical) {
        this.canonical = canonical;
    }

    public java.lang.String canonical() {
        return canonical;
    }
}
