package uk.ac.lancaster.scc.turtles.stellar.core.history;

public interface History {

    Relation query(String queryStatement);
    
    void update(String updateStatement);
    
}
