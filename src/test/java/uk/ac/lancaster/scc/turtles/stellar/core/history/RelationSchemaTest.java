package uk.ac.lancaster.scc.turtles.stellar.core.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class RelationSchemaTest {

    @Test
    public void createRelationMetadata() {
        RelationSchema schema = new RelationSchema("rel", Arrays.asList("attr1", "attr2"));
        assertEquals("rel", schema.getName());
        assertEquals(Arrays.asList("attr1", "attr2"), schema.getAttributes());
    }

    @Test
    public void twoSchemataAreEqualIfTheyHaveSameNameAndAttributes() {
        assertEquals(new RelationSchema("rel", Arrays.asList("attr1", "attr2")), new RelationSchema("rel", Arrays.asList("attr2", "attr1")));
    }
    
    @Test
    public void containsParameter() {
        assertTrue((new RelationSchema("rel", Arrays.asList("a1", "a2"))).contains("a1"));
    }

    @Test
    public void doesNotContainParameter() {
        assertFalse((new RelationSchema("rel", Arrays.asList("a1", "a2"))).contains("a3"));
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void creationFailsWhenNameIsEmpty() {
        RelationSchema schema = new RelationSchema("", Arrays.asList("attr"));
    }
  
    @Test(expected = IllegalArgumentException.class)
    public void creationFailsWhenAttributeListIsEmpty() {
        RelationSchema schema = new RelationSchema("rel", new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creationFailsWhenAttributeIsEmpty() {
        new RelationSchema("rel", Arrays.asList("attr1", ""));
    }

    @Test(expected = NullPointerException.class)
    public void creationFailsWhenNameIsNull() {
        new RelationSchema(null, Arrays.asList("attr"));
    }

    @Test(expected = NullPointerException.class)
    public void creationFailsWhenAttributeListIsNull() {
        new RelationSchema("rel", null);
    }
   
    @Test(expected = NullPointerException.class)
    public void creationFailsWhenAttributeIsNull() {
        List<String> attributes = new ArrayList<>();
        attributes.add("attr");
        attributes.add(null);
        new RelationSchema("rel", attributes);
    }
    
}