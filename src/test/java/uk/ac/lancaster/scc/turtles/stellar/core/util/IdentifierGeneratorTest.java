package uk.ac.lancaster.scc.turtles.stellar.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class IdentifierGeneratorTest {

    @Test
    public void generateUniqueIdentifier() {
        IdentifierGenerator gen = new IdentifierGenerator();
        String firstIdentifier = gen.unique();
        String secondIdentifier = gen.unique();
        assertNotEquals(firstIdentifier, secondIdentifier);
    }
    
    @Test
    public void generateIncrementalIdentifierStartingFromDefaultInitialValue() {
        IdentifierGenerator gen = new IdentifierGenerator();
        assertEquals("pre-0", gen.incremental("pre-"));
        assertEquals("pre-1", gen.incremental("pre-"));
    }
    
    @Test
    public void generateIncrementalIdentifierStartingFromGivenInitialValue() {
        IdentifierGenerator gen = new IdentifierGenerator();
        gen.setInitial(1);
        assertEquals("pre-1", gen.incremental("pre-"));
        assertEquals("pre-2", gen.incremental("pre-"));
    }
    
    @Test(expected = IllegalStateException.class)
    public void whenNextIdentifierIsMaxLongValueIncrementalGenerationThrowsIllegalStateException() {
        IdentifierGenerator gen = new IdentifierGenerator();
        gen.setInitial(Long.MAX_VALUE - 1);
        gen.incremental();
        gen.incremental();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void whenInitialIsSetToMaxLongValueIncrementalGenerationThrowsIllegalArgumentException() {
        IdentifierGenerator gen = new IdentifierGenerator();
        gen.setInitial(Long.MAX_VALUE);
    }
    
}