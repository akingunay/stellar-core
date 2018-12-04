package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.RelationalHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.SQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.util.StringBuilderHelper;

public class BSPLEnactmentRetriever {

    private final RelationalHistory history;
    private final BSPLProtocolSchema protocolSchema;
    private final SQLStatementCompiler compiler;

    public BSPLEnactmentRetriever(RelationalHistory history, BSPLProtocolSchema protocolSchema, SQLStatementCompiler compiler) {
        this.history = history;
        this.protocolSchema = protocolSchema;
        this.compiler = compiler;
    }
    
    public Relation retrieveAllEnactments() {
        return retrieveEnactments("");
    }
    
    void retrieveEnactmentsDummy() {
        List<BSPLReferenceSchema> references = protocolSchema.getReferences();
        int viewCounter = 1;
        ViewMetadata view = compileCreateViewStatementFromOutParametersOfReferenceSchema("view" + viewCounter++, references.get(0));
        System.out.println(view.getStatement() + ";");
        for (int i = 1 ; i < references.size() ; i++) {
            view = compileCreateViewStatementJoiningCommonOutParametersOfReferenceSchemaWithView("view" + viewCounter++, view.getSchema(), references.get(i));
            System.out.println(view.getStatement() + ";");
            view = compileCreateViewStatementAppendingUncommonOutParametersOfRereferenceSchemaToJointViewOfReferenceSchemaAndView("view" + viewCounter++, view.getSchema(), references.get(i));
            System.out.println(view.getStatement() + ";");
        }
        String queryStatement = compiler.compileSelectFromStatement(view.getSchema().getName(), view.getSchema().getAttributes()) + ("");
        System.out.println(queryStatement + ";\n");
        
        StringBuilder clause = new StringBuilder();
        for (int i = 1 ; i < viewCounter ; i++) {
            clause.append("view").append(i).append(", ");
        }
        String dropStatement = compiler.compileDropViewStatement(StringBuilderHelper.trimSuffix(clause, ", ").toString());
        System.out.println(dropStatement + ";\n");
    }
    
    public Relation retrieveEnactments(String whereClause) {
        List<BSPLReferenceSchema> references = protocolSchema.getReferences();
        int viewCounter = 1;
        ViewMetadata view = compileCreateViewStatementFromOutParametersOfReferenceSchema("view" + viewCounter++, references.get(0));
        history.update(view.getStatement());
        for (int i = 1 ; i < references.size() ; i++) {
            view = compileCreateViewStatementJoiningCommonOutParametersOfReferenceSchemaWithView("view" + viewCounter++, view.getSchema(), references.get(i));
            history.update(view.getStatement());
            view = compileCreateViewStatementAppendingUncommonOutParametersOfRereferenceSchemaToJointViewOfReferenceSchemaAndView("view" + viewCounter++, view.getSchema(), references.get(i));
            history.update(view.getStatement());
        }
        String queryStatement = compiler.compileSelectFromStatement(view.getSchema().getName(), view.getSchema().getAttributes()) + (whereClause.isEmpty() ? "" : " " + whereClause);
        Relation enactments = history.query(queryStatement);
        
        StringBuilder clause = new StringBuilder();
        for (int i = 1 ; i < viewCounter ; i++) {
            clause.append("view").append(i).append(", ");
        }
        String dropStatement = compiler.compileDropViewStatement(StringBuilderHelper.trimSuffix(clause, ", ").toString());
        history.update(dropStatement);
        return enactments;
    }
    
    ViewMetadata compileCreateViewStatementFromOutParametersOfReferenceSchema(final String newViewName, final BSPLReferenceSchema referenceSchema) {
        List<String> parameters = new ArrayList<>(referenceSchema.getKeyParameters());
        Collections.sort(parameters);
        List<String> nonKeyParameters = new ArrayList<>(referenceSchema.getNonKeyOutParameters());
        nonKeyParameters.addAll(referenceSchema.getNonKeyInParameters());
        Collections.sort(nonKeyParameters);
        parameters.addAll(nonKeyParameters);
        return new ViewMetadata(new RelationSchema(newViewName, parameters),
                compiler.compileCreateViewStatement(newViewName, compiler.compileSelectFromStatement(referenceSchema.getName(), parameters)));
    }
    
    ViewMetadata compileCreateViewStatementJoiningCommonOutParametersOfReferenceSchemaWithView(final String newViewName, final RelationSchema viewSchema, final BSPLReferenceSchema referenceSchema) {
        return new ViewMetadata(new RelationSchema(newViewName, viewSchema.getAttributes()), 
                compiler.compileCreateViewStatement(newViewName, compiler.compileUnionStatement(
                        selectCommonTuplesAndFillUnknonwValuesFromSchema(viewSchema, referenceSchema),
                        selectTuplesInViewButNotInSchema(viewSchema, referenceSchema))));
    }
    
    private String selectCommonTuplesAndFillUnknonwValuesFromSchema(final RelationSchema viewSchema, final BSPLReferenceSchema referenceSchema) {
        List<String> keyParameters = new ArrayList<>(referenceSchema.getKeyParameters());
        keyParameters.retainAll(viewSchema.getAttributes());
        Collections.sort(keyParameters);
        List<String> nonKeyParamater = new ArrayList<>(viewSchema.getAttributes());
        nonKeyParamater.removeAll(keyParameters);
        Collections.sort(nonKeyParamater);

        List<String> leftJoinParameters = new ArrayList<>();
        for (String parameter : keyParameters) {
            leftJoinParameters.add(referenceSchema.getName() + "." + parameter);
        }
        for (String parameter : nonKeyParamater) {
            if (referenceSchema.containsParameter(parameter) && !referenceSchema.isNilParameter(parameter)) {
                leftJoinParameters.add(referenceSchema.getName() + "." + parameter);
            } else {
                leftJoinParameters.add(viewSchema.getName() + "." + parameter);
            }
        }
        
        StringBuilder leftJoinOnStatementExpressionsBuilder = new StringBuilder();
        for (String keyParameter : keyParameters) {
            leftJoinOnStatementExpressionsBuilder.append(referenceSchema.getName()).append(".").append(keyParameter)
                    .append(" = ")
                    .append(viewSchema.getName()).append(".").append(keyParameter).append(" AND ");
        }
        String leftJoinOnStatementExpressions = StringBuilderHelper.trimSuffix(leftJoinOnStatementExpressionsBuilder, " AND ").toString();
        return compiler.compileLeftJoinStatement(
                compiler.compileSelectStatement(leftJoinParameters) +" FROM " + referenceSchema.getName(), viewSchema.getName(), leftJoinOnStatementExpressions);
    }
    
    private String selectTuplesInViewButNotInSchema(final RelationSchema viewSchema, final BSPLReferenceSchema referenceSchema) {
        List<String> attributes = new ArrayList<>(viewSchema.getAttributes());
        
        StringBuilder whereStatementBuilder = new StringBuilder(" WHERE (");
        for (String parameter : referenceSchema.getKeyParameters()) {
            if (viewSchema.contains(parameter)) {
                whereStatementBuilder.append(viewSchema.getName()).append(".").append(parameter).append(", ");
            }
        }
        StringBuilderHelper.trimSuffix(whereStatementBuilder, ", ").append(") NOT IN (SELECT ");
        for (String parameter : referenceSchema.getKeyParameters()) {
            if (viewSchema.contains(parameter)) {
                whereStatementBuilder.append(referenceSchema.getName()).append(".").append(parameter).append(", ");
            }
        }
        StringBuilderHelper.trimSuffix(whereStatementBuilder, ", ").append(" FROM ").append(referenceSchema.getName()).append(")");
        
        return compiler.compileSelectFromStatement(viewSchema.getName(), attributes) + whereStatementBuilder.toString();
    }
    
    ViewMetadata compileCreateViewStatementAppendingUncommonOutParametersOfRereferenceSchemaToJointViewOfReferenceSchemaAndView(final String newViewName, final RelationSchema viewSchema, final BSPLReferenceSchema referenceSchema) {
        List<String> keyParameters = new ArrayList<>(referenceSchema.getKeyParameters());
        Collections.sort(keyParameters);
        List<String> nonKeyNonNilParamaters = getSortedNonKeyAttributesOfViewAndNonKeyNonNilParametersOfRefeference(viewSchema, referenceSchema);
        List<String> selectedParameters = new ArrayList<>();
        for (String parameter : keyParameters) {
            selectedParameters.add((viewSchema.contains(parameter) ? viewSchema.getName() : referenceSchema.getName()) + "." + parameter);
        }
        for (String parameter : nonKeyNonNilParamaters) {
            selectedParameters.add((viewSchema.contains(parameter) ? viewSchema.getName() : referenceSchema.getName()) + "." + parameter);
        }
        StringBuilder onStatementExpressionsBuilder = new StringBuilder();
        for (String keyParameter : keyParameters) {
            if (viewSchema.contains(keyParameter)) {
                onStatementExpressionsBuilder.append(viewSchema.getName()).append(".").append(keyParameter)
                        .append(" = ")
                        .append(referenceSchema.getName()).append(".").append(keyParameter).append(" AND ");
            }
        }
        StringBuilderHelper.trimSuffix(onStatementExpressionsBuilder, " AND ").toString();

        List<String> parameters = new ArrayList<>();
        for (String parameter : selectedParameters) {
            parameters.add(parameter.split("\\.")[1]);
        }
        
        return new ViewMetadata(new RelationSchema(newViewName, parameters), compiler.compileCreateViewStatement(newViewName, 
                compiler.compileLeftJoinStatement(
                        compiler.compileSelectStatement(selectedParameters) + " FROM " + viewSchema.getName(),
                        referenceSchema.getName(),
                        onStatementExpressionsBuilder.toString()
                )
        ));
    }
    
    private List<String> getSortedNonKeyAttributesOfViewAndNonKeyNonNilParametersOfRefeference(final RelationSchema viewSchema, final BSPLReferenceSchema referenceSchema) {
        Set<String> nonKeyParameterSet = new HashSet<>(viewSchema.getAttributes());
        nonKeyParameterSet.addAll(referenceSchema.getInParameters());
        nonKeyParameterSet.addAll(referenceSchema.getOutParameters());
        nonKeyParameterSet.removeAll(referenceSchema.getKeyParameters());
        List<String> nonKeyParameterList = new ArrayList<>(nonKeyParameterSet);
        Collections.sort(nonKeyParameterList);
        return nonKeyParameterList;
    }
    
    static class ViewMetadata {

        private final RelationSchema schema;
        private final String statement;

        public ViewMetadata(final RelationSchema schema, final String statement) {
            this.schema = schema;
            this.statement = statement;
        }
        
        public RelationSchema getSchema() {
            return schema;
        }
        
        public String getStatement() {
            return statement;
        }
        
    }
    
}
