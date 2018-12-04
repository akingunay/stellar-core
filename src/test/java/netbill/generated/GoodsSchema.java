package netbill.generated;

public class GoodsSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final GoodsSchema INSTANCE;

    static {
        INSTANCE = new GoodsSchema(new GoodsSchemaBuilder());
    }

    public static GoodsSchema instance() {
        return INSTANCE;
    }

    private GoodsSchema(GoodsSchemaBuilder builder) {
        super(builder.name,
        		builder.senderRole,
        		builder.receiverRole,
        		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class GoodsSchemaBuilder {

        public final java.lang.String name;
        public final java.lang.String senderRole;
        public final java.lang.String receiverRole;
        public final java.util.List<java.lang.String> keyParameters;
        public final java.util.List<java.lang.String> inParameters;
        public final java.util.List<java.lang.String> outParameters;
        public final java.util.List<java.lang.String> nilParameters;

        public GoodsSchemaBuilder() {
            name = NetBillMessageName._goods.canonical();
            senderRole = NetBillRoleName._Seller.canonical();
            receiverRole = NetBillRoleName._Buyer.canonical();
            keyParameters = new java.util.ArrayList<>();
			keyParameters.add(NetBillParameterName._pid.canonical());
            inParameters = new java.util.ArrayList<>();
            inParameters.add(NetBillParameterName._pid.canonical());
            inParameters.add(NetBillParameterName._item.canonical());
            inParameters.add(NetBillParameterName._receipt.canonical());
            outParameters = new java.util.ArrayList<>();
            outParameters.add(NetBillParameterName._outcome.canonical());
            nilParameters = new java.util.ArrayList<>();
        }

    }

}
