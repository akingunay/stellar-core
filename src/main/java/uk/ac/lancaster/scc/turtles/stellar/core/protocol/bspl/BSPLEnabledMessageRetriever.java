package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;

public class BSPLEnabledMessageRetriever {

    private final BSPLEnactmentRetriever enactmentRetriever;

    public BSPLEnabledMessageRetriever(BSPLEnactmentRetriever enactmentRetriever) {
        this.enactmentRetriever = enactmentRetriever;
    }
    
    public List<Map<String,String>> retrieveAll(BSPLReferenceSchema referenceSchema) {
        return retrieve(referenceSchema, "");
    }
    
    public List<Map<String,String>> retrieve(BSPLReferenceSchema referenceSchema, String whereClause) {
        List<Map<String,String>> enabledMessageBindings = new ArrayList<>();
        Relation relation = enactmentRetriever.retrieveEnactments(whereClause);
        for (String[] tuple : relation) {
            if (isEnabled(tuple, relation.getRelationSchema(), referenceSchema)) {
                enabledMessageBindings.add(buildEnabledMessageBinding(tuple, relation.getRelationSchema(), referenceSchema));
            }
        }
        return enabledMessageBindings;
    }
    
    private boolean isEnabled(String[] tuple, RelationSchema relationSchema, BSPLReferenceSchema referenceSchema) {
        int i = 0;
        for (String parameter : relationSchema.getAttributes()) {
            if (referenceSchema.containsParameter(parameter)) {
                if ((referenceSchema.isOutParameter(parameter) || referenceSchema.isNilParameter(parameter)) && tuple[i] != null) {
                    return false;
                }
                if (referenceSchema.isInParameter(parameter) && tuple[i] == null) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
    
    private Map<String, String> buildEnabledMessageBinding(String[] tuple, RelationSchema relationSchema, BSPLReferenceSchema referenceSchema) {
        Map<String, String> bindings = new HashMap<>();
        int i = 0;
        for (String parameter : relationSchema.getAttributes()) {
            if (referenceSchema.containsParameter(parameter)) {
                bindings.put(parameter, tuple[i]);
            }
            i++;
        }
        return bindings;
        
    }
}
