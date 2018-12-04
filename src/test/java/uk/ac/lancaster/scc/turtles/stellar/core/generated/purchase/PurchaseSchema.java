package uk.ac.lancaster.scc.turtles.stellar.core.generated.purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final PurchaseSchema INSTANCE;

    static {
        INSTANCE = new PurchaseSchema(new PurchaseProtocolBuilder());
    }

    public static PurchaseSchema instance() {
        return INSTANCE;
    }

    private PurchaseSchema(PurchaseProtocolBuilder builder) {
        super(builder.references);
    }
    
//    private PurchaseSchema(final PurchaseProtocolBuilder builder) {
//        super(builder.name,
//                builder.roles,
//                builder.publicParameters,
//                builder.privateParameters,
//                builder.keyParameters,
//                builder.inParameters,
//                builder.outParameters,
//                builder.nilParameters,
//                builder.references,
//                builder.relationNameForRoleBindings);
//    }

    private static List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> ref() {
        List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references =  new ArrayList<>();
        references.add(RfqSchema.instance());
            references.add(QuoteSchema.instance());
            references.add(AcceptSchema.instance());
            references.add(RejectSchema.instance());
            references.add(DeliverySchema.instance());
            return references;
    }
    
    private static class PurchaseProtocolBuilder {

        final String name;
        final List<String> roles;
        final List<String> publicParameters;
        final List<String> privateParameters;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;
        final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;
        final String relationNameForRoleBindings;

        PurchaseProtocolBuilder() {
            name = "Purchase";
            roles = new ArrayList<>();
            roles.add(PurchaseRoleName._merchant.canonical());
            roles.add(PurchaseRoleName._customer.canonical());
            publicParameters = new ArrayList<>();
            publicParameters.add(PurchaseParameterName._id.canonical());
            publicParameters.add(PurchaseParameterName._item.canonical());
            publicParameters.add(PurchaseParameterName._price.canonical());
            publicParameters.add(PurchaseParameterName._done.canonical());
            privateParameters = new ArrayList<>();
            privateParameters.add(PurchaseParameterName._response.canonical());
            privateParameters.add(PurchaseParameterName._address.canonical());
            keyParameters = new ArrayList<>();
            keyParameters.add(PurchaseParameterName._id.canonical());
            inParameters = new ArrayList<>();
            outParameters = new ArrayList<>();
            outParameters.add(PurchaseParameterName._id.canonical());
            outParameters.add(PurchaseParameterName._item.canonical());
            outParameters.add(PurchaseParameterName._price.canonical());
            outParameters.add(PurchaseParameterName._done.canonical());
            nilParameters = new ArrayList<>();
            references = new ArrayList<>();
            references.add(RfqSchema.instance());
            references.add(QuoteSchema.instance());
            references.add(AcceptSchema.instance());
            references.add(RejectSchema.instance());
            references.add(DeliverySchema.instance());
            relationNameForRoleBindings = "Purchase_role_bindings";
        }

    }
    
}