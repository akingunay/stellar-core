package uk.ac.lancaster.scc.turtles.stellar.core.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class RelationTest {

    @Test
    public void createRelation() {
        Relation.Builder builder = new Relation.Builder(new RelationSchema("m", Arrays.asList("p1", "p2")));
        builder.addTuple(new String[] {"v11", "v12"}).
                addTuple(new String[] {"v21", "v22"});
        int i = 1;
        for (String[] tuple : builder.build()) {
            assertArrayEquals(new String[] {"v" + i + "1", "v" + i + "2"}, tuple);
            i++;
        }
    }
}