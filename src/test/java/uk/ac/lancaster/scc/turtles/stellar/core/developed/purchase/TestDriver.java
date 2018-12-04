package uk.ac.lancaster.scc.turtles.stellar.core.developed.purchase;

public class TestDriver {

    public static void main(String[] args) throws InterruptedException {
        Thread tM = new Thread(TestMerchant.create());
        tM.start();
        Thread tC = new Thread(TestCustomer.create());
        tC.start();
        tC.join();
        tM.join();
    }
    
}
