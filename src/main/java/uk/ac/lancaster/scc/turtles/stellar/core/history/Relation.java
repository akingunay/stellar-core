package uk.ac.lancaster.scc.turtles.stellar.core.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.util.StringBuilderHelper;

public class Relation implements Iterable<String[]> {

    private final RelationSchema schema;
    private final List<String[]> tuples;

    private Relation(final RelationSchema schema, final List<String[]> tuples) {
        this.schema = schema;
        this.tuples = tuples;
    }

    public RelationSchema getRelationSchema() {
        return schema;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Schema: ");
        builder.append(schema.getName()).append("\n");
        for (String attribute : schema.getAttributes()) {
            builder.append(attribute).append("\t");
        }
        StringBuilderHelper.trimSuffix(builder, "\t").append("\n");
        for (String[] tuple : tuples) {
            for (String value : tuple) {
                builder.append(value).append("\t");
            }
            StringBuilderHelper.trimSuffix(builder, "\t").append("\n");
        }
        return StringBuilderHelper.trimSuffix(builder, "\n").toString();
    }
    
    @Override
    public Iterator<String[]> iterator() {
        return Collections.unmodifiableList(tuples).iterator();
    }
    
    public static class Builder {
        
        private final RelationSchema schema;
        private final List<String[]> tuples;

        public Builder(RelationSchema schema) {
            this.schema = schema;
            this.tuples = new ArrayList<>();
        }
        
        public Builder addTuple(String[] tuple) {
            tuples.add(Arrays.copyOf(tuple, tuple.length));
            return this;
        }
        
        public Relation build() {
            return new Relation(schema, new ArrayList<>(tuples));
        }
    }
}
