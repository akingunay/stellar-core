package abc.protocol;

import java.util.ArrayList;
import java.util.List;

public class MsgBSchema extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final MsgBSchema INSTANCE;

    static {
        INSTANCE = new MsgBSchema(new MsgBSchemaBuilder());
    }

    public static MsgBSchema instance() {
        return INSTANCE;
    }

    private MsgBSchema(final MsgBSchemaBuilder builder) {
        super(builder.name,
        	builder.senderRole,
        	builder.receiverRole,
        	builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class MsgBSchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        MsgBSchemaBuilder() {
            name = ABCMessageName._MsgB.canonical();
            senderRole = ABCRoleName._AgentB.canonical();
            receiverRole = ABCRoleName._AgentC.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(ABCParameterName._id.canonical());
            inParameters = new ArrayList<>();
            inParameters.add(ABCParameterName._id.canonical());
            inParameters.add(ABCParameterName._p1.canonical());
            outParameters = new ArrayList<>();
            outParameters.add(ABCParameterName._p2.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
