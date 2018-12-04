package netbill;

import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLServerMetadata;

public class Driver {

    private final static String MYSQL_HOST = "127.0.0.1";
    
    // Seller configuration
    private final static String SELLER_DBUSER = "root";//"seller";
    private final static String SELLER_DBPASS = "cigaacopso";//"sellerpass";
    private final static String SELLER_DBNAME = "sellerhistory";
    private final static String SELLER_HOST = "127.0.0.1";
    private final static String SELLER_PORT = "41681";
    
    // Buyer configuration
    private final static String BUYER_DBUSER = "root";//"buyer";
    private final static String BUYER_DBPASS = "cigaacopso";//"buyerpass";
    private final static String BUYER_DBNAME = "buyerhistory";
    private final static String BUYER_HOST = "127.0.0.1";
    private final static String BUYER_PORT = "41682";
    
    // Bank configuration
    private final static String BANK_DBUSER = "root";//"bank";
    private final static String BANK_DBPASS = "cigaacopso";//"bankpass";
    private final static String BANK_DBNAME = "bankhistory";
    private final static String BANK_HOST = "127.0.0.1";
    private final static String BANK_PORT = "41683";
        
    public static void main(String[] args) throws InterruptedException {
        AgentIdentifier identifierOfSeller = new AgentIdentifier("SELLER", SELLER_HOST, SELLER_PORT);
        AgentIdentifier identifierOfBuyer = new AgentIdentifier("BUYER", BUYER_HOST, BUYER_PORT);
        AgentIdentifier identifierOfBank = new AgentIdentifier("BANK", BANK_HOST, BANK_PORT);
        
        
        MySQLHistory historyOfSeller = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, SELLER_DBNAME, SELLER_DBUSER, SELLER_DBPASS));
        Thread threadSeller = new Thread(new Seller(identifierOfSeller, identifierOfBuyer, identifierOfBank, historyOfSeller));
        
        MySQLHistory historyOfBuyer = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, BUYER_DBNAME, BUYER_DBUSER, BUYER_DBPASS));
        Thread threadBuyer = new Thread(new Buyer(identifierOfBuyer, identifierOfSeller, historyOfBuyer));

        MySQLHistory historyOfBank = new MySQLHistory(new MySQLServerMetadata(MYSQL_HOST, BANK_DBNAME, BANK_DBUSER, BANK_DBPASS));
        Thread threadBank = new Thread(new Bank(identifierOfBank, identifierOfSeller, historyOfBank));
        
        threadBank.start();
        threadSeller.start();
        threadBuyer.start();
        threadBuyer.join();
        threadSeller.join();
        threadBank.join();
    }


}
