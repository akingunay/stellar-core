package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;

public class BSPLViabilityCheckerTest {
//
//    @Test
//    public void checkViable() {
//        Relation.Builder relationBuilder = new Relation.Builder(new RelationSchema(RelationSchema.ANONYMOUS, Arrays.asList("k1", "k2", "p1", "p2", "p3", "p4")));
//        relationBuilder.addTuple(new String[]{"1", "1", "1", "1", "1", null}).
//                addTuple(new String[]{"2", null, "2", "2", null, null}).
//                addTuple(new String[]{"3", null, null, "3", null, null});BSPLEnactmentRetriever enactmentRetriever = Mockito.mock(BSPLEnactmentRetriever.class);
//        Mockito.when(enactmentRetriever.retrieveAllEnactments()).thenReturn(relationBuilder.build());
//        BSPLReferenceSchema.Builder referenceSchemaBuilder = new BSPLReferenceSchema.Builder("m", "S", "R", Arrays.asList("k1", "k2"), Arrays.asList("k2", "p3"));
//        referenceSchemaBuilder.addInParameters(Arrays.asList("k1", "p1")).addNilParameter("p2");
//
//        Map<String, String> bindings = new HashMap<>();
//        bindings.put("k1", "3");
//        bindings.put("k2", "3");
//        bindings.put("p1", "3");
//        bindings.put("p2", null);
//        bindings.put("p3", "3");
//        BSPLMessage message = new BSPLMessage("m", null, null, bindings);
//
//        BSPLViabilityChecker checker = new BSPLViabilityChecker(enactmentRetriever);
//        int result = checker.check(message, referenceSchemaBuilder.build());
//    }
}