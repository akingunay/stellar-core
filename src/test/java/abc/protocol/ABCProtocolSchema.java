package abc.protocol;

import java.util.ArrayList;
import java.util.List;

public class ABCProtocolSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLProtocolSchema {

    private static final ABCProtocolSchema INSTANCE;

    static {
        INSTANCE = new ABCProtocolSchema(new ABCProtocolSchemaBuilder());
    }

    public static ABCProtocolSchema instance() {
        return INSTANCE;
    }

    private ABCProtocolSchema(ABCProtocolSchemaBuilder builder) {
        super(builder.references);
    }
    
    private static class ABCProtocolSchemaBuilder {

        final List<uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema> references;

        ABCProtocolSchemaBuilder() {
            references = new ArrayList<>();
            references.add(MsgASchema.instance());
            references.add(MsgBSchema.instance());
            references.add(MsgCSchema.instance());
        }

    }
    
}