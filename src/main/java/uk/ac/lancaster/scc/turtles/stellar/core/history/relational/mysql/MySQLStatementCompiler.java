package uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql;

import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.SQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.util.ArgumentValidator;
import uk.ac.lancaster.scc.turtles.stellar.core.util.StringBuilderHelper;

public class MySQLStatementCompiler implements SQLStatementCompiler {

    @Override
    public String compileSelectStatement(final List<String> attributes) {
        StringBuilder sqlStatement = new StringBuilder("SELECT ");
        for (String attribute : attributes) {
            sqlStatement.append(attribute).append(", ");
        }
        return StringBuilderHelper.trimSuffix(sqlStatement, ", ").toString();
    }
    
    @Override
    public String compileSelectFromStatement(final String schemaName, final List<String> attributes) {
        StringBuilder sqlStatement = new StringBuilder("SELECT ");
        for (String attribute : attributes) {
            sqlStatement.append(schemaName).append(".").append(attribute).append(", ");
        }
        return StringBuilderHelper.trimSuffix(sqlStatement, ", ").append(" FROM ").append(schemaName).toString();
    }

    @Override
    public String compileInsertStatement(final String schemaName, final List<String> attributes, List<String> values) {
        ArgumentValidator.notEmpty(schemaName);
        if (attributes.size() == values.size()) {
            StringBuilder sqlStatement = new StringBuilder("INSERT INTO ").append(schemaName).append(" (");
            for (String attribute : attributes) {
                ArgumentValidator.notEmpty(attribute);
                sqlStatement.append(attribute).append(", ");
            }
            StringBuilderHelper.trimSuffix(sqlStatement, ", ").append(") VALUES (");
            for (String value : values) {
                sqlStatement.append("\"").append(value).append("\"").append(", ");
            }
            return StringBuilderHelper.trimSuffix(sqlStatement, ", ").append(")").toString();
        } else {
            throw new IllegalArgumentException("Length of attribute and value arrays do not match.");
        }
    }

    @Override
    public String compileUnionStatement(final String firstStatement, final String secondStatement) {
        return firstStatement + " UNION " + secondStatement;
    }

    @Override
    public String compileLeftJoinStatement(final String selectFromStatement, final String joinRelationName, final String onStatement) {
        return selectFromStatement + " LEFT JOIN " + joinRelationName + " ON " + onStatement;
    }

    @Override
    public String compileCreateViewStatement(String viewName, String viewStatement) {
        return "CREATE VIEW " + viewName + " AS " + viewStatement; 
    }

    @Override
    public String compileDropViewStatement(final String viewName) {
        ArgumentValidator.notEmpty(viewName);
        return "DROP VIEW " + viewName;
    }

}
