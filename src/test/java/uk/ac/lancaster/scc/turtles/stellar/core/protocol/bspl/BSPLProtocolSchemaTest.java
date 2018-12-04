package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSPLProtocolSchemaTest {

    @Test
    public void createSchema() {
        List<BSPLReferenceSchema> referenceSchemata = new ArrayList<>();
        BSPLReferenceSchema.Builder firstRefSchemaBuilder = new BSPLReferenceSchema.Builder("m1", "s", "r", Arrays.asList("k"), Arrays.asList("k"));
        referenceSchemata.add(firstRefSchemaBuilder.build());
        BSPLReferenceSchema.Builder secondRefSchemaBuilder = new BSPLReferenceSchema.Builder("m1", "s", "r", Arrays.asList("k"), Arrays.asList("p"));
        secondRefSchemaBuilder.addInParameter("k");
        referenceSchemata.add(secondRefSchemaBuilder.build());
        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(referenceSchemata);
        assertEquals(referenceSchemata, protocolSchema.getReferences());
    }

}