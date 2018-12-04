package netbill.generated;

public class TransferSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final TransferSchema INSTANCE;

    static {
        INSTANCE = new TransferSchema(new TransferSchemaBuilder());
    }

    public static TransferSchema instance() {
        return INSTANCE;
    }

    private TransferSchema(TransferSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class TransferSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public TransferSchemaBuilder() {
            name = NetBillMessageName._transfer.canonical();
            senderRole = NetBillRoleName._Bank.canonical();
            receiverRole = NetBillRoleName._Seller.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(NetBillParameterName._pid.canonical());
            inParameters.add(NetBillParameterName._price.canonical());
            inParameters.add(NetBillParameterName._ccard.canonical());
            inParameters.add(NetBillParameterName._account.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._chargeResponse.canonical());
            outParameters.add(NetBillParameterName._receipt.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
