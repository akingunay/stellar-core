package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.ArrayList;
import java.util.List;

public class RfqSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final RfqSchema INSTANCE;

    static {
        INSTANCE = new RfqSchema(new RfqSchemaBuilder());
    }

    public static RfqSchema instance() {
        return INSTANCE;
    }

    private RfqSchema(final RfqSchemaBuilder builder) {
        super(builder.name,
                builder.senderRole,
                builder.receiverRole,
                builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class RfqSchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        public RfqSchemaBuilder() {
            name = PurchaseMessageName._rfq.canonical();
            senderRole = PurchaseRoleName._customer.canonical();
            receiverRole = PurchaseRoleName._merchant.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(PurchaseParameterName._id.canonical());
            inParameters = new ArrayList<>();
            outParameters = new ArrayList<>();
            outParameters.add(PurchaseParameterName._id.canonical());
            outParameters.add(PurchaseParameterName._item.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
