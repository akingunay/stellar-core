package uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uk.ac.lancaster.scc.turtles.stellar.core.history.Relation;
import uk.ac.lancaster.scc.turtles.stellar.core.history.RelationSchema;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.RelationalHistory;

public class MySQLHistory implements RelationalHistory {

    private static final String URL_PREFIX = "jdbc:mysql:";

    private Connection connection;
    private Statement statement;
    private boolean connected;
    
    public MySQLHistory(MySQLServerMetadata metadata) {
        String url = URL_PREFIX + "//" + metadata.getHost() + "/" + metadata.getDatabase();
        try {
            connection = DriverManager.getConnection(url + "?useSSL=false", metadata.getUser(), metadata.getPassword());
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Connection to agent history cannot be established. Check database settings and metadata.");
            System.exit(0);
        }
        connected = true;
    }
    
    @Override
    public Relation query(String queryStatement) {
        if (connected) {
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery(queryStatement);
                return extractRelation(resultSet);
            } catch (SQLException e1){
                e1.printStackTrace();
                return null;
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } else {
            throw new IllegalStateException("Cannot run query wihtout database connection.");
        }
    }

    Relation extractRelation(ResultSet resultSet) {
        try {
            int columnCount = resultSet.getMetaData().getColumnCount();
            List<String> attributes = new ArrayList<>();
            for (int i = 1; i <= columnCount ; i++) {
                attributes.add(resultSet.getMetaData().getColumnLabel(i));
            }
            Relation.Builder builder = new Relation.Builder(new RelationSchema(RelationSchema.ANONYMOUS, attributes));
            String[] tuple = new String[columnCount];
            while (resultSet.next()) {
                for (int i = 1; i <= tuple.length; i++) {
                    tuple[i - 1] = resultSet.getString(i);
                }
                builder.addTuple(tuple);
            }
            return builder.build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void update(String updateStatement) {
        if (connected) {
            try {
                statement.executeUpdate(updateStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalStateException("Cannot run query wihtout database connection.");
        }
    }

    public void close() {
        if (connected) {
            try {
                statement.close();
                statement = null;
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connected = false;
            }
        }
    }
    
}
