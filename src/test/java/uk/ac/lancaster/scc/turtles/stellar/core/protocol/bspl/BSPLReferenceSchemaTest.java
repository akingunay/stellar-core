package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSPLReferenceSchemaTest {

    public BSPLReferenceSchemaTest() {
    }

    @Test
    public void containsParameterWhenParameterIsOut() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        assertTrue(builder.build().containsParameter("p"));
    }

    @Test
    public void containsParameterWhenParameterIsIn() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        builder.addInParameter("p1");
        assertTrue(builder.build().containsParameter("p1"));
    }

    @Test
    public void containsParameterWhenParameterIsNil() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        builder.addNilParameter("p1");
        assertTrue(builder.build().containsParameter("p1"));
    }
    
    @Test
    public void doesNotContainParameter() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        assertFalse(builder.build().containsParameter("p1"));
    }

    @Test
    public void isOutParameter() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        assertTrue(builder.build().isOutParameter("p"));
    }

    @Test
    public void isNotOutParameterButInParameter() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        builder.addInParameter("p1");
        assertFalse(builder.build().isOutParameter("p1"));
    }

    @Test
    public void isNotOutParameterButNilParameter() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        builder.addNilParameter("p1");
        assertFalse(builder.build().isOutParameter("p1"));
    }

    @Test
    public void isNotOutParameter() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m", "s", "r", Arrays.asList("p"), Arrays.asList("p"));
        assertFalse(builder.build().isOutParameter("p1"));
    }

}