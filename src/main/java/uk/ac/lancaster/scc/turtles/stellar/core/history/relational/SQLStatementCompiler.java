package uk.ac.lancaster.scc.turtles.stellar.core.history.relational;

import java.util.List;

public interface SQLStatementCompiler {

    String compileSelectStatement(final List<String> attributes);
    
    String compileSelectFromStatement(final String schemaName, final List<String> attributes);
    
    String compileInsertStatement(final String schemaName, final List<String> attributes, List<String> values);
    
    String compileUnionStatement(final String firstStatement, final String secondStatement);

    String compileLeftJoinStatement(final String selectFromStatement, final String joinRelationName, final String onStatement);
    
    String compileCreateViewStatement(final String viewName, final String viewStatement);
    
    String compileDropViewStatement(final String viewName);

}
