package uk.ac.lancaster.scc.turtles.stellar.core.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import uk.ac.lancaster.scc.turtles.stellar.core.util.ArgumentValidator;

public class RelationSchema {

    public static final String ANONYMOUS = "ANONYMOUS";
    
    private final String relationName;
    private final List<String> attributes;
    
    public RelationSchema(final String relationName, final List<String> attributes) {
        validateArguments(relationName, attributes);
        this.relationName = relationName;
        this.attributes = new ArrayList<>(attributes);
    }
    
    private void validateArguments(final String relationName, final List<String> attributes) {
        ArgumentValidator.notNull(relationName);
        ArgumentValidator.notEmpty(relationName);
        ArgumentValidator.notEmpty(attributes);
        for (String attribute : attributes) {
            ArgumentValidator.notNull(attribute);
            ArgumentValidator.notEmpty(attribute);
        }
    }
    
    public String getName() {
        return relationName;
    }
    
    public List<String> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public boolean contains(final String attribute) {
        return attributes.contains(attribute);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.relationName);
        hash = 67 * hash + Objects.hashCode(new HashSet<>(this.attributes));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RelationSchema other = (RelationSchema) obj;
        if (!Objects.equals(this.relationName, other.relationName)) {
            return false;
        }
        return Objects.equals(new HashSet<>(this.attributes), new HashSet<>(other.attributes));
    }
    
}
