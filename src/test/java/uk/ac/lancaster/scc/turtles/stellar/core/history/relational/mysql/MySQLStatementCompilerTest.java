package uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class MySQLStatementCompilerTest {

    public static final MySQLStatementCompiler compiler = new MySQLStatementCompiler();

    @Test
    public void compileSelectStatementTest() {
        assertEquals("SELECT rel1.a1, rel2.a2", compiler.compileSelectStatement(Arrays.asList("rel1.a1", "rel2.a2")));
    }
    
    @Test
    public void compileSelectFromStatementTest() {
        assertEquals("SELECT rel.a1, rel.a2 FROM rel", compiler.compileSelectFromStatement("rel", Arrays.asList("a1", "a2")));
    }
    
    @Test
    public void compileUnionStatementTest() {
        assertEquals("SELECT rel1.a FROM rel1 UNION SELECT rel2.a FROM rel2", compiler.compileUnionStatement("SELECT rel1.a FROM rel1", "SELECT rel2.a FROM rel2"));
    }
    
    @Test
    public void compileLeftJoinStatementTest() {
        assertEquals("SELECT rel1.a1, rel2.a2 rel1.a3 FROM rel1 LEFT JOIN rel2 ON rel1.a1 = rel2.a1 AND rel1.a2 = rel2.a2",
                compiler.compileLeftJoinStatement("SELECT rel1.a1, rel2.a2 rel1.a3 FROM rel1", "rel2", "rel1.a1 = rel2.a1 AND rel1.a2 = rel2.a2"));
    }
    
    @Test
    public void compileCreateViewStatementTest() {
        assertEquals("CREATE VIEW view AS SELECT rel.p1 FROM rel", compiler.compileCreateViewStatement("view", "SELECT rel.p1 FROM rel"));
    }
    
    @Test
    public void compileDropViewStatementTest() {
        assertEquals("DROP VIEW testview", compiler.compileDropViewStatement("testview"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void compileDropViewStatementFailsWhenViewNameIsEmpty() {
        compiler.compileDropViewStatement("");
    }

}