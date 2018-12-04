package netbill.generated;

import java.util.ArrayList;
import java.util.List;

public class NetBillBankProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final NetBillBankProtocolSchema INSTANCE;

    static {
        INSTANCE = new NetBillBankProtocolSchema(new NetBillBankProtocolSchemaBuilder());
    }

    public static NetBillBankProtocolSchema instance() {
        return INSTANCE;
    }

    private NetBillBankProtocolSchema(NetBillBankProtocolSchemaBuilder builder) {
		super(builder.references);
    }

    private static class NetBillBankProtocolSchemaBuilder {

        public final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        public NetBillBankProtocolSchemaBuilder() {
            references = new ArrayList<>();
			references.add(ChargeSchema.instance());
			references.add(TransferSchema.instance());
			references.add(DenySchema.instance());
        }

    }
}