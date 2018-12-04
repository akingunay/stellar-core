package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import soc.assignment.AssignmentSProtocolSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.RelationalHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLStatementCompiler;

public class BSPLEnactmentRetrieverTest {

    public static final MySQLStatementCompiler compiler = new MySQLStatementCompiler();
    
    @Test
    public void compileCreateViewStatementFromInOutParametersOfReferenceSchema() {
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("rel", null, null, Arrays.asList("k1", "k2"), Arrays.asList("p4", "p5"));
        builder.addInParameters(Arrays.asList("p1", "p2")).addNilParameters(Arrays.asList("p3"));
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(null, null, compiler);
        BSPLEnactmentRetriever.ViewMetadata view = retriever.compileCreateViewStatementFromOutParametersOfReferenceSchema("view", builder.build());
        assertEquals("CREATE VIEW view AS SELECT rel.k1, rel.k2, rel.p1, rel.p2, rel.p4, rel.p5 FROM rel", view.getStatement());
        assertEquals(new RelationSchema("view", Arrays.asList("k1", "k2", "p1", "p2", "p4", "p5")), view.getSchema());
    }
    
    @Test
    public void compileCreateViewStatementJoiningCommonOutParametersOfReferenceSchemaWithView() {
        RelationSchema viewSchema = new RelationSchema("m1", Arrays.asList("k1", "p1", "p2"));
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m2", null, null, Arrays.asList("k1", "k2"), Arrays.asList("p2", "p3"));
        builder.addInParameters(Arrays.asList("p0")).addNilParameters(Arrays.asList("p1"));
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(null, null, compiler);
        BSPLEnactmentRetriever.ViewMetadata view = retriever.compileCreateViewStatementJoiningCommonOutParametersOfReferenceSchemaWithView("view", viewSchema, builder.build());
        assertEquals("CREATE VIEW view AS SELECT m2.k1, m1.p1, m2.p2 FROM m2 LEFT JOIN m1 ON m2.k1 = m1.k1" +
                " UNION SELECT m1.k1, m1.p1, m1.p2 FROM m1 WHERE (m1.k1) NOT IN (SELECT m2.k1 FROM m2)", view.getStatement());
        assertEquals(new RelationSchema("view", Arrays.asList("k1", "p1", "p2")), view.getSchema());
    }
    
    @Test
    public void compileCreateViewStatementAppendingUncommonOutParametersOfRereferenceSchemaToJointViewOfReferenceSchemaAndView() {
        RelationSchema viewSchema = new RelationSchema("m1", Arrays.asList("k1", "p1", "p2"));
        BSPLReferenceSchema.Builder builder = new BSPLReferenceSchema.Builder("m2", null, null, Arrays.asList("k1", "k2"), Arrays.asList("p2", "p3"));
        builder.addInParameters(Arrays.asList("p0")).addNilParameters(Arrays.asList("p1"));
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(null, null, compiler);
        BSPLEnactmentRetriever.ViewMetadata view = retriever.compileCreateViewStatementAppendingUncommonOutParametersOfRereferenceSchemaToJointViewOfReferenceSchemaAndView("view", viewSchema, builder.build());
        assertEquals("CREATE VIEW view AS SELECT m1.k1, m2.k2, m2.p0, m1.p1, m1.p2, m2.p3 FROM m1 LEFT JOIN m2 ON m1.k1 = m2.k1", view.getStatement());
        assertEquals(new RelationSchema("view", Arrays.asList("k1", "k2", "p0","p1", "p2", "p3")), view.getSchema());
    }
    
    public void compileAllEnactmentsDummy() {
        RelationalHistory history = Mockito.mock(RelationalHistory.class);
        Mockito.when(history.query(Mockito.anyString())).thenReturn((new Relation.Builder(null)).build());
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(history, AssignmentSProtocolSchema.instance(), compiler);
        retriever.retrieveEnactmentsDummy();
    }
    
    @Test
    public void compileAllEnactmanesCase1() {
        List<BSPLReferenceSchema> referenceSchemata = new ArrayList<>();
        BSPLReferenceSchema.Builder m1 = new BSPLReferenceSchema.Builder("m1", "a", "a", Arrays.asList("k1"), Arrays.asList("k1", "p1"));
        referenceSchemata.add(m1.build());

        BSPLReferenceSchema.Builder m2 = new BSPLReferenceSchema.Builder("m2", "a", "a", Arrays.asList("k1"), Arrays.asList("p2"));
        referenceSchemata.add(m2.addInParameter("k1").addInParameter("p1").build());

        BSPLReferenceSchema.Builder m3 = new BSPLReferenceSchema.Builder("m3", "a", "a", Arrays.asList("k1"), Arrays.asList("p2", "p3"));
        referenceSchemata.add(m3.addInParameter("k1").addInParameter("p1").build());

        BSPLReferenceSchema.Builder m4 = new BSPLReferenceSchema.Builder("m4", "a", "a", Arrays.asList("k1"), Arrays.asList("p4"));
        referenceSchemata.add(m4.addInParameter("k1").addInParameter("p1").addInParameter("p2").addNilParameter("p3").build());

        BSPLReferenceSchema.Builder m5 = new BSPLReferenceSchema.Builder("m5", "a", "a", Arrays.asList("k1"), Arrays.asList("p3", "p5"));
        referenceSchemata.add(m5.addInParameter("k1").addInParameter("p1").addInParameter("p2").addInParameter("p4").build());

        BSPLProtocolSchema protocolSchema = new BSPLProtocolSchema(referenceSchemata);

        RelationalHistory history = Mockito.mock(RelationalHistory.class);
        Mockito.when(history.query(Mockito.anyString())).thenReturn((new Relation.Builder(null)).build());
        BSPLEnactmentRetriever retriever = new BSPLEnactmentRetriever(history, protocolSchema, compiler);
        
        assertNotNull(retriever.retrieveAllEnactments());
        
        Mockito.verify(history).update("CREATE VIEW view1 AS SELECT m1.k1, m1.p1 FROM m1");
        Mockito.verify(history).update("CREATE VIEW view2 AS SELECT m2.k1, m2.p1 FROM m2 LEFT JOIN view1 ON m2.k1 = view1.k1 UNION SELECT view1.k1, view1.p1 FROM view1 WHERE (view1.k1) NOT IN (SELECT m2.k1 FROM m2)");
        Mockito.verify(history).update("CREATE VIEW view3 AS SELECT view2.k1, view2.p1, m2.p2 FROM view2 LEFT JOIN m2 ON view2.k1 = m2.k1");
        Mockito.verify(history).update("CREATE VIEW view4 AS SELECT m3.k1, m3.p1, m3.p2 FROM m3 LEFT JOIN view3 ON m3.k1 = view3.k1 UNION SELECT view3.k1, view3.p1, view3.p2 FROM view3 WHERE (view3.k1) NOT IN (SELECT m3.k1 FROM m3)");
        Mockito.verify(history).update("CREATE VIEW view5 AS SELECT view4.k1, view4.p1, view4.p2, m3.p3 FROM view4 LEFT JOIN m3 ON view4.k1 = m3.k1");
        Mockito.verify(history).update("CREATE VIEW view6 AS SELECT m4.k1, m4.p1, m4.p2, view5.p3 FROM m4 LEFT JOIN view5 ON m4.k1 = view5.k1 UNION SELECT view5.k1, view5.p1, view5.p2, view5.p3 FROM view5 WHERE (view5.k1) NOT IN (SELECT m4.k1 FROM m4)");
        Mockito.verify(history).update("CREATE VIEW view7 AS SELECT view6.k1, view6.p1, view6.p2, view6.p3, m4.p4 FROM view6 LEFT JOIN m4 ON view6.k1 = m4.k1");
        Mockito.verify(history).update("CREATE VIEW view8 AS SELECT m5.k1, m5.p1, m5.p2, m5.p3, m5.p4 FROM m5 LEFT JOIN view7 ON m5.k1 = view7.k1 UNION SELECT view7.k1, view7.p1, view7.p2, view7.p3, view7.p4 FROM view7 WHERE (view7.k1) NOT IN (SELECT m5.k1 FROM m5)");
        Mockito.verify(history).update("CREATE VIEW view9 AS SELECT view8.k1, view8.p1, view8.p2, view8.p3, view8.p4, m5.p5 FROM view8 LEFT JOIN m5 ON view8.k1 = m5.k1");
        Mockito.verify(history).query("SELECT view9.k1, view9.p1, view9.p2, view9.p3, view9.p4, view9.p5 FROM view9");
        Mockito.verify(history).update("DROP VIEW view1, view2, view3, view4, view5, view6, view7, view8, view9");
    }
    
}