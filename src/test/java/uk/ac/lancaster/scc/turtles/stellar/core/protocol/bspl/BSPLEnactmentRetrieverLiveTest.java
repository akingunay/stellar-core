package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.SQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.BSPLReferenceSchemaTestInstance;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.IKaIPaOPbOPc;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.IKaIPaOPbOPd;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.IKaNPaOPb;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.OKaOPa;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl.instance.OKaOPb;

public class BSPLEnactmentRetrieverLiveTest {
    
    private static final String HOST = "127.0.0.1";
    private static final String DATABASE = "extensiontest";
    private static final String USER = "root";
    private static final String PASS = "cigaacopso";
    
    private static final MySQLHistory HISTORY = new MySQLHistory(new MySQLServerMetadata(HOST, DATABASE, USER, PASS));
    private static final SQLStatementCompiler COMPILER = new MySQLStatementCompiler();
    
    private static final String MA = "ma";
    private static final String MB = "mb";
    private static final String KA_1 = "ka-1";
    private static final String KA_2 = "ka-2";
    private static final String KA_3 = "ka-3";
    private static final String KA_4 = "ka-4";
    private static final String PA_1 = "pa-1";
    private static final String PA_2 = "pa-2";
    private static final String PA_3 = "pa-3";
    private static final String PA_4 = "pa-4";
    private static final String PB_1 = "pb-1";
    private static final String PB_2 = "pb-2";
    private static final String PB_3 = "pb-3";
    private static final String PB_4 = "pb-4";
    private static final String PC_1 = "pc-1";
    private static final String PC_2 = "pc-2";
    private static final String PC_3 = "pc-3";
    private static final String PC_4 = "pc-4";
    private static final String PD_1 = "pd-1";
    private static final String PD_2 = "pd-2";
    private static final String PD_3 = "pd-3";
    private static final String PD_4 = "pd-4";
    
    @AfterClass
    public static void closeHistory() {
        HISTORY.close();
    }
    
    @Test
    public void extensionOfOKaOPaAndOKaOPa() {
        BSPLReferenceSchemaTestInstance m1 = new OKaOPa(MA);
        createAndInsert(m1, Arrays.asList(
                Arrays.asList(KA_1, PA_1),
                Arrays.asList(KA_2, PA_2)));
        BSPLReferenceSchemaTestInstance m2 = new OKaOPa(MB);
        createAndInsert(m2, Arrays.asList(
                Arrays.asList(KA_2, PA_2),
                Arrays.asList(KA_3, PA_3)));
        
        RelationSchema expectedExtensionSchema = new RelationSchema("extension", joinCanonicalParameterLists(m1, m2));
        Relation expectedExtension = (new Relation.Builder(expectedExtensionSchema)).
                addTuple(new String[] {KA_1, PA_1}).
                addTuple(new String[] {KA_2, PA_2}).
                addTuple(new String[] {KA_3, PA_3}).
                build();
        
        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(Arrays.asList(m1, m2));
        checkAssertion(protocolSchema, expectedExtension);
        protocolSchema = new BSPLProtocolSchema(Arrays.asList(m2, m1));
        checkAssertion(protocolSchema, expectedExtension);
        
        dropTables(protocolSchema);
    }
    
    @Test
    public void extensionOfOKaOPaAndOKaOPb() {
        BSPLReferenceSchemaTestInstance m1 = new OKaOPa(MA);
        createAndInsert(m1, Arrays.asList(
                Arrays.asList(KA_1, PA_1),
                Arrays.asList(KA_2, PA_2)));
        BSPLReferenceSchemaTestInstance m2 = new OKaOPb(MB);
        createAndInsert(m2, Arrays.asList(
                Arrays.asList(KA_2, PB_2),
                Arrays.asList(KA_3, PB_3)));
        
        RelationSchema expectedExtensionSchema = new RelationSchema("extension", joinCanonicalParameterLists(m1, m2));
        Relation expectedExtension = (new Relation.Builder(expectedExtensionSchema)).
                addTuple(new String[] {KA_1, PA_1, null}).
                addTuple(new String[] {KA_2, PA_2, PB_2}).
                addTuple(new String[] {KA_3, null, PB_3}).
                build();
        
        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(Arrays.asList(m1, m2));
        checkAssertion(protocolSchema, expectedExtension);
        protocolSchema = new BSPLProtocolSchema(Arrays.asList(m2, m1));
        checkAssertion(protocolSchema, expectedExtension);
        
        dropTables(protocolSchema);
    }
    
    @Test
    public void extensionOfIKaIPaOPbOPcAndIKaIPaOPbOPd() {
        BSPLReferenceSchemaTestInstance m1 = new IKaIPaOPbOPc(MA);
        createAndInsert(m1, Arrays.asList(
                Arrays.asList(KA_1, PA_1, PB_1, PC_1),
                Arrays.asList(KA_2, PA_2, PB_2, PC_2)));
        BSPLReferenceSchemaTestInstance m2 = new IKaIPaOPbOPd(MB);
        createAndInsert(m2, Arrays.asList(
                Arrays.asList(KA_3, PA_3, PB_3, PD_3),
                Arrays.asList(KA_4, PA_4, PB_4, PD_4)));

        RelationSchema expectedExtensionSchema = new RelationSchema("extension", joinCanonicalParameterLists(m1, m2));
        Relation expectedExtension = (new Relation.Builder(expectedExtensionSchema)).
                addTuple(new String[] {KA_1, PA_1, PB_1, PC_1, null}).
                addTuple(new String[] {KA_2, PA_2, PB_2, PC_2, null}).
                addTuple(new String[] {KA_3, PA_3, PB_3, null, PD_3}).
                addTuple(new String[] {KA_4, PA_4, PB_4, null, PD_4}).
                build();

        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(Arrays.asList(m1, m2));
        checkAssertion(protocolSchema, expectedExtension);
        protocolSchema = new BSPLProtocolSchema(Arrays.asList(m2, m1));
        checkAssertion(protocolSchema, expectedExtension);
        
        dropTables(protocolSchema);
    }
        
    @Test
    public void extensionOfOKaOPaAndIKaNPaOPb() {
        BSPLReferenceSchemaTestInstance m1 = new OKaOPa(MA);
        createAndInsert(m1, Arrays.asList(
                Arrays.asList(KA_1, PA_1),
                Arrays.asList(KA_2, PA_2)));
        BSPLReferenceSchemaTestInstance m2 = new IKaNPaOPb(MB);
        createAndInsert(m2, Arrays.asList(
                Arrays.asList(KA_3, null, PB_3),
                Arrays.asList(KA_4, null, PB_4)));
        
        RelationSchema expectedExtensionSchema = new RelationSchema("extension", joinCanonicalParameterLists(m1, m2));
        Relation expectedExtension = (new Relation.Builder(expectedExtensionSchema)).
                addTuple(new String[] {KA_1, PA_1, null}).
                addTuple(new String[] {KA_2, PA_2, null}).
                addTuple(new String[] {KA_3, null, PB_3}).
                addTuple(new String[] {KA_4, null, PB_4}).
                build();
        
        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(Arrays.asList(m1, m2));
        checkAssertion(protocolSchema, expectedExtension);
        protocolSchema = new BSPLProtocolSchema(Arrays.asList(m2, m1));
        checkAssertion(protocolSchema, expectedExtension);
        
        dropTables(protocolSchema);
    }
    
    private void checkAssertion(BSPLProtocolSchema protocolSchema, Relation expectedExtension) {
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(HISTORY, protocolSchema, COMPILER);
        Relation extension = retriever.retrieveAllEnactments();
        assertTrue(areEquals(expectedExtension, extension));
    }
    
    private void checkAssertionVerbose(BSPLProtocolSchema protocolSchema, Relation expectedExtension) {
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(HISTORY, protocolSchema, COMPILER);
        retriever.retrieveEnactmentsDummy();
        Relation extension = retriever.retrieveAllEnactments();
        System.out.println("---EXPECTED---\n" + expectedExtension);
        System.out.println("---ACTUAL---\n" + extension);
        assertTrue(areEquals(expectedExtension, extension));
    }
    
    private void createAndInsert(BSPLReferenceSchemaTestInstance r, List<List<String>> tuples) {
        HISTORY.update(r.drop());
        HISTORY.update(r.create());
        HISTORY.update(r.insert(tuples));
    }
    
    public void dropTables(BSPLProtocolSchema protocolSchema) {
        for (BSPLReferenceSchema reference : protocolSchema.getReferences()) {
            HISTORY.update(((BSPLReferenceSchemaTestInstance)reference).drop());
        }
    }
    
    private List<String> joinCanonicalParameterLists(BSPLReferenceSchema firstReference, BSPLReferenceSchema secondReference) {
        List<String> parameters = new ArrayList<>(uniqueJoinAndSort(firstReference.getKeyParameters(), secondReference.getKeyParameters()));
        parameters.addAll(uniqueJoinAndSort(firstReference.getNonKeyParameters(), secondReference.getNonKeyParameters()));
        return parameters;
    }
    
    private List<String> uniqueJoinAndSort(List<String> firstList, List<String> secondList) {
        Set<String> joinedSet = new HashSet<>(firstList);
        joinedSet.addAll(secondList);
        List<String> joinedList = new ArrayList<>(joinedSet);
        Collections.sort(joinedList);
        return joinedList;
    }
    
    private boolean areEquals(Relation r1, Relation r2) {
        if (!r1.getRelationSchema().getAttributes().equals(r2.getRelationSchema().getAttributes())) {
            return false;
        }
        return subsetOf(r1, r2) && subsetOf(r2, r1);
    }
    
    private boolean subsetOf(Relation r1, Relation r2) {
        for (String[] t1 : r1) {
            boolean hasMatchingTuple = false;
            for (String[] t2 : r2) {
                if (Arrays.equals(t1, t2)) {
                    hasMatchingTuple = true;
                    break;
                }
            }
            if (!hasMatchingTuple) {
                return false;
            }
        }
        return true;
    }
    
}
