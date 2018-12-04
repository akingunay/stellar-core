package abc.agents;

import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;

public class Driver {

    // SET THE FOLLOWING CONFIGURATION PARAMETERS
 
    // For simplicity all agents use the same database user account and password.
    // DO NOT FORGET TO SET THESE ACCORDING TO YOUR SETUP
    private final static String DB_USER = "root";
    private final static String DB_PASS = "cigaacopso";
    
    // All agent databases are in the local host. Otherwise update MYSQL_HOST.
    private final static String MYSQL_HOST = "127.0.0.1";
 
    // The following are the names of the agent's databases according to abc.myq script
    // If you change the database names in the script, update these too.
    private final static String AGENT_A_DB = "ahistory";
    private final static String AGENT_B_DB = "bhistory";
    private final static String AGENT_C_DB = "chistory";
        
    // Agent names are not important. They are only used when printing log messages.
    // You can use the default values or change as you want.
    private final static String AGENT_A_NAME = "A";
    private final static String AGENT_B_NAME = "B";
    private final static String AGENT_C_NAME = "C";

    // Since agents run in the same process as threads, we use the localhost.
    // If you want to run the agents on different machines, I can implement them
    // in separate projects.
    private final static String AGENT_A_HOST = "127.0.0.1";
    private final static String AGENT_B_HOST = "127.0.0.1";
    private final static String AGENT_C_HOST = "127.0.0.1";

    // Each agent must have a uniqe port number, which is not in use. The following
    // defaults should normally work.
    private final static String AGENT_A_PORT = "41681";
    private final static String AGENT_B_PORT = "41682";
    private final static String AGENT_C_PORT = "41683";
        
    public static void main(String[] args) throws InterruptedException {
        AgentIdentifier identifierOfA = new AgentIdentifier(AGENT_A_NAME, AGENT_A_HOST, AGENT_A_PORT);
        MySQLHistory historyOfA = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_A_DB, DB_USER, DB_PASS));
        AgentIdentifier identifierofB = new AgentIdentifier(AGENT_B_NAME, AGENT_B_HOST, AGENT_B_PORT);
        MySQLHistory historyOfB = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_B_DB, DB_USER, DB_PASS));
        AgentIdentifier identifierOfC = new AgentIdentifier(AGENT_C_NAME, AGENT_C_HOST, AGENT_C_PORT);
        MySQLHistory historyOfC = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, AGENT_C_DB, DB_USER, DB_PASS));

        Thread threadA = new Thread(new ExampleAAgent(identifierOfA, identifierofB, historyOfA));
        Thread threadB = new Thread(new ExampleBAgent(identifierofB, identifierOfC, historyOfB));
        Thread threadC = new Thread(new ExampleCAgent(identifierOfC, identifierOfA, historyOfC));
        threadC.start();
        threadB.start();
        threadA.start();
        threadA.join();
        threadB.join();
        threadC.join();
    }
    
}