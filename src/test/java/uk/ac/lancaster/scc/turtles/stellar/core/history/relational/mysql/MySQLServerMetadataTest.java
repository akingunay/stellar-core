package uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql;

import org.junit.Test;
import static org.junit.Assert.*;

public class MySQLServerMetadataTest {

    @Test
    public void createMySQLServerMetadata() {
        MySQLServerMetadata metadata = new MySQLServerMetadata("localhost", "testdb", "user", "pass");
        assertEquals("localhost", metadata.getHost());
        assertEquals("testdb", metadata.getDatabase());
        assertEquals("user", metadata.getUser());
        assertEquals("pass", metadata.getPassword());
    }
    
}