package abc.protocol;

import java.util.ArrayList;
import java.util.List;

public class MsgASchema  extends uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.BSPLReferenceSchema {

    private static final MsgASchema INSTANCE;

    static {
        INSTANCE = new MsgASchema(new MsgASchemaBuilder());
    }

    public static MsgASchema instance() {
        return INSTANCE;
    }

    private MsgASchema(final MsgASchemaBuilder builder) {
        super(builder.name,
        	builder.senderRole,
        	builder.receiverRole,
        	builder.keyParameters,
                builder.inParameters,
                builder.outParameters,
                builder.nilParameters);
    }

    private static class MsgASchemaBuilder {

        final String name;
        final String senderRole;
        final String receiverRole;
        final List<String> keyParameters;
        final List<String> inParameters;
        final List<String> outParameters;
        final List<String> nilParameters;

        MsgASchemaBuilder() {
            name = ABCMessageName._MsgA.canonical();
            senderRole = ABCRoleName._AgentA.canonical();
            receiverRole = ABCRoleName._AgentB.canonical();
            keyParameters = new ArrayList<>();
            keyParameters.add(ABCParameterName._id.canonical());
            inParameters = new ArrayList<>();
            outParameters = new ArrayList<>();
            outParameters.add(ABCParameterName._id.canonical());
            outParameters.add(ABCParameterName._p1.canonical());
            nilParameters = new ArrayList<>();
        }

    }

}
