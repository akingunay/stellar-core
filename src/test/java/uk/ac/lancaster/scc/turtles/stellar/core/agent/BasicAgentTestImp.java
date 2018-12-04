package uk.ac.lancaster.scc.turtles.stellar.core.agent;

import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Message;
import uk.ac.lancaster.scc.turtles.stellar.core.protocol.Endpoint;

public class BasicAgentTestImp extends BasicAgent {

    private int handleMessageCount;
    private int beforeStartCount;
    private int afterStartCount;
    private int actCount;
    private int beforeStopCount;
    private int afterStopCount;
    
    public BasicAgentTestImp(Endpoint protocolAdapter) {
        super(protocolAdapter);
        handleMessageCount = 0;
        beforeStartCount = 0;
        afterStartCount = 0;
        actCount = 0;
        beforeStopCount = 0;
        afterStopCount = 0;
    }
    
    protected void handleMessageBehavior() {
        
    }

    protected void beforeStartBehavior() {
        
    }
    
    protected void afterStartBehavior() {
        
    }

    protected void actBehavior() {
        
    }    

    protected void beforeStopBehavior() {
        
    }    

    protected void afterStopBehavior() {
        
    }    
    
    @Override
    public void handleMessage(Message message) {
        if (beforeStartCount != 1) {
            throw new IllegalStateException("handleMessage() is called before beforeStart()");
        }
        if (afterStartCount != 1) {
            throw new IllegalStateException("handleMessage() is called before afterStart()");
        }
        if (beforeStopCount == 1) {
            throw new IllegalStateException("handleMessage() is called after beforeStop()");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("handleMessage() is called after afterStop()");
        }
        handleMessageBehavior();
        handleMessageCount++;
    }
    
    @Override
    protected void beforeStart() {
        if (beforeStartCount == 1) {
            throw new IllegalStateException("beforeStart() is already called");
        }
        if (afterStartCount == 1) {
            throw new IllegalStateException("beforeStart() is called after afterStart()");
        }
        if (beforeStopCount == 1) {
            throw new IllegalStateException("beforeStart() is called after beforeStop()");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("beforeStart() is called after afterStop()");
        }
        beforeStartBehavior();
        beforeStartCount++;
    }
    
    @Override
    protected void afterStart() {
        if (beforeStartCount != 1) {
            throw new IllegalStateException("afterStart() is called before beforeStart()");
        }
        if (afterStartCount == 1) {
            throw new IllegalStateException("afterStart() is already called");
        }
        if (beforeStopCount == 1) {
            throw new IllegalStateException("afterStart() is called after beforeStop()");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("afterStart() is called after afterStop()");
        }
        afterStartBehavior();
        afterStartCount++;
    }
    
    @Override
    protected void act() {
        if (beforeStartCount != 1) {
            throw new IllegalStateException("act() is called before beforeStart()");
        }
        if (afterStartCount != 1) {
            throw new IllegalStateException("act() is called before afterStart()");
        }
        if (beforeStopCount == 1) {
            throw new IllegalStateException("act() is called after beforeStop()");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("act() is called after afterStop()");
        }
        actBehavior();
        actCount++;
    }
   
    @Override
    protected void beforeStop() {
        if (beforeStartCount != 1) {
            throw new IllegalStateException("beforeStop() is called before beforeStart()");
        }
        if (afterStartCount != 1) {
            throw new IllegalStateException("beforeStop() is called before afterStart()");
        }
        if (beforeStopCount == 1) {
            throw new IllegalStateException("beforeStop() is already called");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("beforeStop() is called before afterStop()");
        }
        beforeStopBehavior();
        beforeStopCount++;
    }
    
    @Override
    protected void afterStop() {
        if (beforeStartCount != 1) {
            throw new IllegalStateException("afterStop() is called before beforeStart()");
        }
        if (afterStartCount != 1) {
            throw new IllegalStateException("afterStop() is called before afterStart()");
        }
        if (beforeStopCount != 1) {
            throw new IllegalStateException("afterStop() is called before beforeStop()");
        }
        if (afterStopCount == 1) {
            throw new IllegalStateException("afterStop() is already called");
        }
        afterStopBehavior();
        afterStopCount++;
    }
    
    int getBeforeStartCount() {
        return beforeStartCount;
    }

    int getAfterStartCount() {
        return afterStartCount;
    }

    int getActCount() {
        return actCount;
    }

    int getBeforeStopCount() {
        return beforeStopCount;
    }

    int getAfterStopCount() {
        return afterStopCount;
    }

    int getHandleMessageCount() {
        return handleMessageCount;
    }

}
