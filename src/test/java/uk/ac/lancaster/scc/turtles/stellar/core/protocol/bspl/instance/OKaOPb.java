package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OKaOPb extends BSPLReferenceSchemaTestInstance {

    final static String senderRole = "s";
    final static String receiverRole = "r";
    final static List<String> keyParameters = Arrays.asList("ka");
    final static List<String> inParameters = new ArrayList<>();
    final static List<String> outParameters = Arrays.asList("ka", "pb");
    final static List<String> nilParameters = new ArrayList<>();

    public OKaOPb(String name) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
    }

}
