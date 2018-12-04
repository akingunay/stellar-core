package netbill.generated;

public class QuoteSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final QuoteSchema INSTANCE;

    static {
        INSTANCE = new QuoteSchema(new QuoteSchemaBuilder());
    }

    public static QuoteSchema instance() {
        return INSTANCE;
    }

    private QuoteSchema(QuoteSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class QuoteSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public QuoteSchemaBuilder() {
            name = NetBillMessageName._quote.canonical();
            senderRole = NetBillRoleName._Seller.canonical();
            receiverRole = NetBillRoleName._Buyer.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(NetBillParameterName._pid.canonical());
            inParameters.add(NetBillParameterName._item.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._price.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
