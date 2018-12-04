package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.ArrayList;
import java.util.List;

public class QuoteSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final QuoteSchema INSTANCE;

    static {
        INSTANCE = new QuoteSchema(new QuoteSchemaBuilder());
    }

    public static QuoteSchema instance() {
        return INSTANCE;
    }

    private QuoteSchema(final QuoteSchemaBuilder builder) {
        super(builder.name,
                builder.senderRole,
                builder.receiverRole,
                builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class QuoteSchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        public QuoteSchemaBuilder() {
            name = PurchaseMessageName._quote.canonical();
            senderRole = PurchaseRoleName._merchant.canonical();
            receiverRole = PurchaseRoleName._customer.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(PurchaseParameterName._id.canonical());
            inParameters = new ArrayList<>();
            inParameters.add(PurchaseParameterName._id.canonical());
            inParameters.add(PurchaseParameterName._item.canonical());
            outParameters = new ArrayList<>();
            outParameters.add(PurchaseParameterName._price.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
