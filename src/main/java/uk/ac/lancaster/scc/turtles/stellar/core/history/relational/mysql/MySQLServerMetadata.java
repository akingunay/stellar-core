package uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql;

public class MySQLServerMetadata {

    private final String host;
    private final String database;
    private final String user;
    private final String password;

    public MySQLServerMetadata(final String host, final String database, final String user, final String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    
    
}
