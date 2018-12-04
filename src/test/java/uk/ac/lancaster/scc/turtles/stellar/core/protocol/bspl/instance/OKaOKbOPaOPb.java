package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OKaOKbOPaOPb extends BSPLReferenceSchemaTestInstance {

    final static String senderRole = "s";
    final static String receiverRole = "r";
    final static List<String> keyParameters = Arrays.asList("ka", "kb");
    final static List<String> inParameters = new ArrayList<>();
    final static List<String> outParameters = Arrays.asList("ka", "kb", "pa", "pb");
    final static List<String> nilParameters = new ArrayList<>();

    public OKaOKbOPaOPb(String name) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
    }

    
    
}
