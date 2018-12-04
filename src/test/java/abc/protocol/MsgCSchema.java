package abc.protocol;

import java.util.ArrayList;
import java.util.List;

public class MsgCSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final MsgCSchema INSTANCE;

    static {
        INSTANCE = new MsgCSchema(new MsgCSchemaBuilder());
    }

    public static MsgCSchema instance() {
        return INSTANCE;
    }

    private MsgCSchema(final MsgCSchemaBuilder builder) {
        super(builder.name,
        	builder.senderRole,
        	builder.receiverRole,
        	builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class MsgCSchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        MsgCSchemaBuilder() {
            name = ABCMessageName._MsgC.canonical();
            senderRole = ABCRoleName._AgentC.canonical();
            receiverRole = ABCRoleName._AgentA.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(ABCParameterName._id.canonical());
            inParameters = new ArrayList<>();
            inParameters.add(ABCParameterName._id.canonical());
            inParameters.add(ABCParameterName._p2.canonical());
            outParameters = new ArrayList<>();
            outParameters.add(ABCParameterName._p3.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
