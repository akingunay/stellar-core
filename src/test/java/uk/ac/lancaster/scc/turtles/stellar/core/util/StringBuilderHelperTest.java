package uk.ac.lancaster.scc.turtles.stellar.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringBuilderHelperTest {

    @Test
    public void builderIsTrimmed() {
        assertEquals("", StringBuilderHelper.trimSuffix(new StringBuilder("1"), "1").toString());
        assertEquals("1", StringBuilderHelper.trimSuffix(new StringBuilder("12"), "2").toString());
        assertEquals("12", StringBuilderHelper.trimSuffix(new StringBuilder("122"), "2").toString());
        assertEquals("1", StringBuilderHelper.trimSuffix(new StringBuilder("122"), "22").toString());
    }
    
    @Test
    public void builderIsNotTrimedWhenSuffixDoesNotMuchToGivenStirng() {
        assertEquals("", StringBuilderHelper.trimSuffix(new StringBuilder(""), ".").toString());
        assertEquals("1223", StringBuilderHelper.trimSuffix(new StringBuilder("1223"), "22").toString());
    }

}