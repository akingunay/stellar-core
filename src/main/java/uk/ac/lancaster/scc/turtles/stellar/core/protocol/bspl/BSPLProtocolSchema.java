package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BSPLProtocolSchema {

    private final List<BSPLReferenceSchema> references;

    public BSPLProtocolSchema(List<BSPLReferenceSchema> references) {
        this.references = new ArrayList<>(references);
    }

    public List<BSPLReferenceSchema> getReferences() {
        return Collections.unmodifiableList(references);
    }
    
    public BSPLReferenceSchema getReferenceSchema(String referenceName) {
        for (BSPLReferenceSchema referenceSchema : references) {
            if (referenceSchema.getName().equals(referenceName)) {
                return referenceSchema;
            }
        }
        return null;
    }
}
