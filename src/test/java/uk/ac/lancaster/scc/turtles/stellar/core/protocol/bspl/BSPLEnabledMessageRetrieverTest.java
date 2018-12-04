package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;

public class BSPLEnabledMessageRetrieverTest {

    @Test
    public void retrieveWhenThereIsNoEnactment() {
        Relation.Builder relationBuilder = new Relation.Builder(new RelationSchema(RelationSchema.ANONYMOUS, Arrays.asList("k1", "k2", "p1", "p2", "p3", "p4")));
        BSPLEnactmentRetriever enactmentRetriever = Mockito.mock(BSPLEnactmentRetriever.class);
        Mockito.when(enactmentRetriever.retrieveEnactments(Mockito.anyString())).thenReturn(relationBuilder.build());
        BSPLReferenceSchema.Builder referenceSchemaBuilder = new BSPLReferenceSchema.Builder("m", "S", "R", Arrays.asList("k1", "k2"), Arrays.asList("k2", "p3"));
        referenceSchemaBuilder.addInParameters(Arrays.asList("k1", "p1")).addNilParameter("p2");
        BSPLEnabledMessageRetriever messageRetriever = new BSPLEnabledMessageRetriever(enactmentRetriever);
        assertEquals(new ArrayList<>(), messageRetriever.retrieveAll(referenceSchemaBuilder.build()));
    }
    
    @Test
    public void retrieveWhenThereAreEnactmentsButNoneEnabled() {
        Relation.Builder relationBuilder = new Relation.Builder(new RelationSchema(RelationSchema.ANONYMOUS, Arrays.asList("k1", "k2", "p1", "p2", "p3", "p4")));
        relationBuilder.addTuple(new String[]{"1", "1", "1", "1", "1", null}).
                addTuple(new String[]{"2", null, "2", "2", null, null}).
                addTuple(new String[]{"3", null, null, "3", null, null});
        BSPLEnactmentRetriever enactmentRetriever = Mockito.mock(BSPLEnactmentRetriever.class);
        Mockito.when(enactmentRetriever.retrieveEnactments(Mockito.anyString())).thenReturn(relationBuilder.build());
        BSPLReferenceSchema.Builder referenceSchemaBuilder = new BSPLReferenceSchema.Builder("m", "S", "R", Arrays.asList("k1", "k2"), Arrays.asList("k2", "p3"));
        referenceSchemaBuilder.addInParameters(Arrays.asList("k1", "p1")).addNilParameter("p2");
        BSPLEnabledMessageRetriever messageRetriever = new BSPLEnabledMessageRetriever(enactmentRetriever);
        assertEquals(new ArrayList<>(), messageRetriever.retrieveAll(referenceSchemaBuilder.build()));
        
    }
    
    @Test
    public void retrieveWhenEnabledExist() {
        Relation.Builder relationBuilder = new Relation.Builder(new RelationSchema(RelationSchema.ANONYMOUS, Arrays.asList("k1", "k2", "p1", "p2", "p3", "p4")));
        relationBuilder.addTuple(new String[]{"1", "1", "1", "1", "1", null}).
                addTuple(new String[]{"2", null, "2", "2", null, null}).
                addTuple(new String[]{"3", null, "3", null, null, null}).
                addTuple(new String[]{"4", null, null, "4", null, null}).
                addTuple(new String[]{"5", null, "5", null, null, null});
        BSPLEnactmentRetriever enactmentRetriever = Mockito.mock(BSPLEnactmentRetriever.class);
        Mockito.when(enactmentRetriever.retrieveEnactments(Mockito.anyString())).thenReturn(relationBuilder.build());
        BSPLReferenceSchema.Builder referenceSchemaBuilder = new BSPLReferenceSchema.Builder("m", "S", "R", Arrays.asList("k1", "k2"), Arrays.asList("k2", "p3"));
        referenceSchemaBuilder.addInParameters(Arrays.asList("k1", "p1")).addNilParameter("p2");
        List<Map<String, String>> expected = new ArrayList<>();
        Map<String, String> bindings = new HashMap<>();
        bindings.put("k1", "3");
        bindings.put("k2", null);
        bindings.put("p1", "3");
        bindings.put("p2", null);
        bindings.put("p3", null);
        expected.add(bindings);
        bindings = new HashMap<>();
        bindings.put("k1", "5");
        bindings.put("k2", null);
        bindings.put("p1", "5");
        bindings.put("p2", null);
        bindings.put("p3", null);
        expected.add(bindings);
        BSPLEnabledMessageRetriever messageRetriever = new BSPLEnabledMessageRetriever(enactmentRetriever);
        List<Map<String, String>> retrieved = messageRetriever.retrieveAll(referenceSchemaBuilder.build());
        assertEquals(expected, retrieved);
    }
}