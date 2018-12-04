package netbill.generated;

public class RfqSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final RfqSchema INSTANCE;

    static {
        INSTANCE = new RfqSchema(new RfqSchemaBuilder());
    }

    public static RfqSchema instance() {
        return INSTANCE;
    }

    private RfqSchema(RfqSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class RfqSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public RfqSchemaBuilder() {
            name = NetBillMessageName._rfq.canonical();
            senderRole = NetBillRoleName._Buyer.canonical();
            receiverRole = NetBillRoleName._Seller.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._pid.canonical());
            outParameters.add(NetBillParameterName._item.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
