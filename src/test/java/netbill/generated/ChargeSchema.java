package netbill.generated;

public class ChargeSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final ChargeSchema INSTANCE;

    static {
        INSTANCE = new ChargeSchema(new ChargeSchemaBuilder());
    }

    public static ChargeSchema instance() {
        return INSTANCE;
    }

    private ChargeSchema(ChargeSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class ChargeSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public ChargeSchemaBuilder() {
            name = NetBillMessageName._charge.canonical();
            senderRole = NetBillRoleName._Seller.canonical();
            receiverRole = NetBillRoleName._Bank.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(NetBillParameterName._pid.canonical());
            inParameters.add(NetBillParameterName._price.canonical());
            inParameters.add(NetBillParameterName._ccard.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._account.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
