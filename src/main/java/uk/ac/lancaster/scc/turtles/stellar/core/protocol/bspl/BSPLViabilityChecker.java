package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.util.StringBuilderHelper;

public class BSPLViabilityChecker {

    private final BSPLEnactmentRetriever enactmentRetriever;

    public BSPLViabilityChecker(BSPLEnactmentRetriever enactmentRetriever) {
        this.enactmentRetriever = enactmentRetriever;
    }
    
    public ViabilityCheckResult check(BSPLMessage message, BSPLReferenceSchema referenceSchema) {
        if (!allInsAndOutsAraBoundAndNilsUnbound(message, referenceSchema)) {
            return ViabilityCheckResult.UNBOUND_PARAMETER;
        }
        if (referenceSchema.getKeyInParameters().isEmpty()) {
            return ViabilityCheckResult.VIABLE;
        }
        Relation enactments = enactmentRetriever.retrieveEnactments(compileWhereClause(message, referenceSchema));
        if (!outKeyIntegrityHolds(enactments, message, referenceSchema)) {
            return ViabilityCheckResult.OUT_VIOLATION;
        }
        if (!inNonkeyIntegrityHolds(enactments, message, referenceSchema)) {
            return ViabilityCheckResult.IN_VIOLATION;
        }
        if (!nilIntegrityHolds(enactments, message, referenceSchema)) {
            return ViabilityCheckResult.NIL_VIOLATION;
        }
        return ViabilityCheckResult.VIABLE;
    }

    private String compileWhereClause(BSPLMessage message, BSPLReferenceSchema schema) {
        if (schema.getKeyInParameters().isEmpty()) {
            return "";
        }
        StringBuilder whereClause = new StringBuilder(" WHERE ");
        for (String key : schema.getKeyInParameters()) {
            whereClause.append(key).append(" = ").append("\"").append(message.getBinding(key)).append("\"").append(" AND ");
        }
        return StringBuilderHelper.trimSuffix(whereClause, " AND ").toString();
    }
    
    private boolean outKeyIntegrityHolds(Relation enactments, BSPLMessage message, BSPLReferenceSchema referenceSchema) {
        for (String parameter : referenceSchema.getKeyOutParameters()) {
            if (parameterBindingIsKnown(parameter, message.getBinding(parameter), enactments)) {
                return false;
            }
        }
        return true;
    }

    private boolean inNonkeyIntegrityHolds(Relation enactments, BSPLMessage message, BSPLReferenceSchema referenceSchema) {
        for (String parameter : referenceSchema.getNonKeyInParameters()) {
            if (!parameterBindingIsKnown(parameter, message.getBinding(parameter), enactments)) {
                return false;
            }
        }
        return true;
    }

    private boolean nilIntegrityHolds(Relation enactments, BSPLMessage message, BSPLReferenceSchema referenceSchema) {
        for (String parameter : referenceSchema.getNilParameters()) {
            if (parameterBindingIsKnown(parameter, message.getBinding(parameter), enactments)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean parameterBindingIsKnown(String parameter, String binding, Relation enactments) {
        int index = enactments.getRelationSchema().getAttributes().indexOf(parameter);
        if (index == -1) {
            return false;
        } else {
            return valuesOfParameter(enactments, index).contains(binding);
        }
    }
    
    private Set<String> valuesOfParameter(Relation enactments, int index) {
        Set<String> values = new HashSet<>();
        for (String[] tuple : enactments) {
            if (tuple[index] != null) {
                values.add(tuple[index]);
            }
        }
        return values;
    }
    
    private boolean allInsAndOutsAraBoundAndNilsUnbound(BSPLMessage message, BSPLReferenceSchema referenceSchema) {
        return allParmatersAreBound(referenceSchema.getOutParameters(), message) &&
                allParmatersAreBound(referenceSchema.getInParameters(), message) &&
                allParametersAreUnbound(referenceSchema.getNilParameters(), message);
    }
    
    private boolean allParmatersAreBound(List<String> parameters, BSPLMessage message) {
        for (String parameter : parameters) {
            if (message.getBinding(parameter) == null) {
                return false;
            }
        }
        return true;
    }
    
    private boolean allParametersAreUnbound(List<String> parameters, BSPLMessage message) {
        for (String parameter : parameters) {
            if (message.getBinding(parameter) != null) {
                return false;
            }
        }
        return true;
    }
}
