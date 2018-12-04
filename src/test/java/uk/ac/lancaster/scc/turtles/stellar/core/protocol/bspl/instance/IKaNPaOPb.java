package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.Arrays;
import java.util.List;

public class IKaNPaOPb extends BSPLReferenceSchemaTestInstance {

    final static String senderRole = "s";
    final static String receiverRole = "r";
    final static List<String> keyParameters = Arrays.asList(K_A);
    final static List<String> inParameters = Arrays.asList(K_A);
    final static List<String> outParameters = Arrays.asList(P_B);
    final static List<String> nilParameters =  Arrays.asList(P_A);

    public IKaNPaOPb(String name) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
    }

}
