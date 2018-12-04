/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Akin Gunay
 */
public class OKaOPaOPc  extends BSPLReferenceSchemaTestInstance {

    final static String senderRole = "s";
    final static String receiverRole = "r";
    final static List<String> keyParameters = Arrays.asList("ka");
    final static List<String> inParameters = new ArrayList<>();
    final static List<String> outParameters = Arrays.asList("ka", "pa", "pc");
    final static List<String> nilParameters = new ArrayList<>();

    public OKaOPaOPc(String name) {
        super(name, senderRole, receiverRole, keyParameters, inParameters, outParameters, nilParameters);
    }
}