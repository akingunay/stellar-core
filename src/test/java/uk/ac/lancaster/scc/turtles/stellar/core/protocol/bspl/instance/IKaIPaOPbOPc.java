package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.BSPLReferenceSchemaTestInstance.K_A;

public class IKaIPaOPbOPc  extends BSPLReferenceSchemaTestInstance {
   
    final static String senderRole = "s";
    final static String receiverRole = "r";
    final static List<String> keyParameters = Arrays.asList(K_A);
    final static List<String> inParameters = Arrays.asList(K_A, P_A);
    final static List<String> outParameters = Arrays.asList(P_B, P_C);
    final static List<String> nilParameters = new ArrayList<>();

    public IKaIPaOPbOPc(String name) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
    }

}
