package netbill.generated;

public class AcceptQuoteSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final AcceptQuoteSchema INSTANCE;

    static {
        INSTANCE = new AcceptQuoteSchema(new AcceptQuoteSchemaBuilder());
    }

    public static AcceptQuoteSchema instance() {
        return INSTANCE;
    }

    private AcceptQuoteSchema(AcceptQuoteSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class AcceptQuoteSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public AcceptQuoteSchemaBuilder() {
            name = NetBillMessageName._acceptQuote.canonical();
            senderRole = NetBillRoleName._Buyer.canonical();
            receiverRole = NetBillRoleName._Seller.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(NetBillParameterName._pid.canonical());
            inParameters.add(NetBillParameterName._item.canonical());
            inParameters.add(NetBillParameterName._price.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._quoteResponse.canonical());
            outParameters.add(NetBillParameterName._ccard.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
