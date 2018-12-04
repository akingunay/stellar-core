package netbill.generated;

import java.util.ArrayList;
import java.util.List;

public class NetBillBuyerProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final NetBillBuyerProtocolSchema INSTANCE;

    static {
        INSTANCE = new NetBillBuyerProtocolSchema(new NetBillBuyerProtocolSchemaBuilder());
    }

    public static NetBillBuyerProtocolSchema instance() {
        return INSTANCE;
    }

    private NetBillBuyerProtocolSchema(NetBillBuyerProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class NetBillBuyerProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public NetBillBuyerProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(RfqSchema.instance());
			references.add(QuoteSchema.instance());
			references.add(AcceptQuoteSchema.instance());
			references.add(RejectQuoteSchema.instance());
			references.add(GoodsSchema.instance());
			references.add(ErrorSchema.instance());
        }

    }
}