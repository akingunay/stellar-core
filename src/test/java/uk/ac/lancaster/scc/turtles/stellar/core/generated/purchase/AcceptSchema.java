package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.ArrayList;
import java.util.List;

public class AcceptSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final AcceptSchema INSTANCE;

    static {
        INSTANCE = new AcceptSchema(new AcceptSchemaBuilder());
    }

    public static AcceptSchema instance() {
        return INSTANCE;
    }

    private AcceptSchema(final AcceptSchemaBuilder builder) {
        super(builder.name,
        	builder.senderRole,
        	builder.receiverRole,
        	builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class AcceptSchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        AcceptSchemaBuilder() {
            name = PurchaseMessageName._accept.canonical();
            senderRole = PurchaseRoleName._customer.canonical();
            receiverRole = PurchaseRoleName._merchant.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(PurchaseParameterName._id.canonical());
            inParameters = new ArrayList<>();
            inParameters.add(PurchaseParameterName._id.canonical());
            inParameters.add(PurchaseParameterName._item.canonical());
            inParameters.add(PurchaseParameterName._price.canonical());
            outParameters = new ArrayList<>();
            outParameters.add(PurchaseParameterName._response.canonical());
            outParameters.add(PurchaseParameterName._address.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
