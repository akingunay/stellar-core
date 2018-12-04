package netbill;

import netbill.generated.BankAgent;
import netbill.generated.Charge;
import netbill.generated.EnabledDeny;
import netbill.generated.EnabledTransfer;
import netbill.generated.NetBillParameterName;
import uk.ac.lancaster.scc.turtles.stellar.core.agent.AgentIdentifier;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.mysql.MySQLHistory;

public class Bank extends BankAgent implements Runnable {

    private AgentIdentifier selfIdentifier;
    private AgentIdentifier sellerIdentifier;
    private int enactmentCount;

    private final static int ENACTMENT_LIMIT = 2;

    public Bank(AgentIdentifier selfIdentifier, AgentIdentifier sellerIdentifier, MySQLHistory history) {
        super(selfIdentifier, history);
        this.selfIdentifier = selfIdentifier;
        this.sellerIdentifier = sellerIdentifier;
        enactmentCount = 0;
    }

    @Override
    protected void handleCharge(Charge receivedMessage) {
        System.out.println(selfIdentifier.getName() + " RECEIVED "
                + receivedMessage.getName() + "["
                + receivedMessage.bindingOfPid() + ", "
                + receivedMessage.bindingOfPrice() + ", "
                + receivedMessage.bindingOfCcard() + ", "
                + receivedMessage.bindingOfAccount() + "]"
                + " FROM " + receivedMessage.getSender().getName());
        enactmentCount++;
        if (enactmentCount % 2 == 0) {
            EnabledTransfer enabledTransfer = retrieveEnabledTransfer().get(0);

            String chargeResponse = "chargeResponse-" + enactmentCount;
            String receipt = "receipt-" + enactmentCount;
            sendEnabledTransfer(enabledTransfer, chargeResponse, receipt, sellerIdentifier);

            System.out.println(selfIdentifier.getName() + " SENT "
                    + enabledTransfer.getName() + "["
                    + enabledTransfer.getBinding(NetBillParameterName._pid.canonical()) + ", "
                    + enabledTransfer.getBinding(NetBillParameterName._price.canonical()) + ", "
                    + enabledTransfer.getBinding(NetBillParameterName._ccard.canonical()) + ", "
                    + enabledTransfer.getBinding(NetBillParameterName._account.canonical()) + ", "
                    + chargeResponse + ", " + receipt + "]"
                    + " TO " + receivedMessage.getSender().getName());
        } else {
            EnabledDeny enabledDeny = retrieveEnabledDeny().get(0);
            String chargeResponse = "chargeResponse-" + enactmentCount;
            String denialReason = "denialReason-" + enactmentCount;
            sendEnabledDeny(enabledDeny, chargeResponse, denialReason, sellerIdentifier);
            System.out.println(selfIdentifier.getName() + " SENT "
                    + enabledDeny.getName() + "["
                    + enabledDeny.getBinding(NetBillParameterName._pid.canonical()) + ", "
                    + enabledDeny.getBinding(NetBillParameterName._price.canonical()) + ", "
                    + enabledDeny.getBinding(NetBillParameterName._ccard.canonical()) + ", "
                    + enabledDeny.getBinding(NetBillParameterName._account.canonical()) + ", "
                    + chargeResponse + ", " + denialReason + "]"
                    + " TO " + receivedMessage.getSender().getName());
        }
        if (enactmentCount == ENACTMENT_LIMIT) {
            stop();
        }
    }

    @Override
    protected void beforeStart() {
        System.out.println("Bank is ready to start.");
    }

    @Override
    protected void afterStart() {
        System.out.println("Bank has started.");
    }

    @Override
    protected void act() {
        if (ENACTMENT_LIMIT < enactmentCount) {
            stop();
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    protected void beforeStop() {
        System.out.println("Bank will stop.");
    }

    @Override
    protected void afterStop() {
        System.out.println("Bank stopped.");
    }

    @Override
    public void run() {
        execute();
    }
}
