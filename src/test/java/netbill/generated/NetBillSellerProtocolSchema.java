package netbill.generated;

import java.util.ArrayList;
import java.util.List;

public class NetBillSellerProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final NetBillSellerProtocolSchema INSTANCE;

    static {
        INSTANCE = new NetBillSellerProtocolSchema(new NetBillSellerProtocolSchemaBuilder());
    }

    public static NetBillSellerProtocolSchema instance() {
        return INSTANCE;
    }

    private NetBillSellerProtocolSchema(NetBillSellerProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class NetBillSellerProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public NetBillSellerProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(RfqSchema.instance());
			references.add(QuoteSchema.instance());
			references.add(AcceptQuoteSchema.instance());
			references.add(RejectQuoteSchema.instance());
			references.add(ChargeSchema.instance());
			references.add(TransferSchema.instance());
			references.add(DenySchema.instance());
			references.add(GoodsSchema.instance());
			references.add(ErrorSchema.instance());
        }

    }
}