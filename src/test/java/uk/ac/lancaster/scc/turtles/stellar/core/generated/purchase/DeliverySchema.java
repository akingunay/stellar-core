package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.ArrayList;
import java.util.List;

public class DeliverySchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final DeliverySchema INSTANCE;

    static {
        INSTANCE = new DeliverySchema(new DeliverySchemaBuilder());
    }

    public static DeliverySchema instance() {
        return INSTANCE;
    }

    private DeliverySchema(final DeliverySchemaBuilder builder) {
        super(builder.name,
       		builder.senderRole,
       		builder.receiverRole,
       		builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    static class DeliverySchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        DeliverySchemaBuilder() {
            name = PurchaseMessageName._delivery.canonical();
            senderRole = PurchaseRoleName._merchant.canonical();
            receiverRole = PurchaseRoleName._customer.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(PurchaseParameterName._id.canonical());
            inParameters = new ArrayList<>();
            inParameters.add(PurchaseParameterName._id.canonical());
            inParameters.add(PurchaseParameterName._item.canonical());
            inParameters.add(PurchaseParameterName._price.canonical());
            inParameters.add(PurchaseParameterName._address.canonical());
            outParameters = new ArrayList<>();
            outParameters.add(PurchaseParameterName._done.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
