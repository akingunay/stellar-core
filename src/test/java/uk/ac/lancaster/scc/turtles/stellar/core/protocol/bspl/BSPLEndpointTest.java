package uk.ac.lancaster.scc.turtles.stellar.core.protocol.bspl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;
import org.mockito.Mockito;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.RelationalHistory;
import uk.ac.lancaster.scc.turtles.stellar.core.history.relational.SQLStatementCompiler;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageReceiver;
import uk.ac.lancaster.scc.turtles.stellar.core.communication.MessageSender;

public class BSPLEndpointTest {

    private final static String JSON_STRING = "{\"mName\":\"m\","
            + "\"mSender\":{\"name\":\"s\",\"host\":\"hs\",\"port\":\"1234\"},"
            + "\"mReceiver\":{\"name\":\"r\",\"host\":\"hr\",\"port\":\"1234\"},"
            + "\"mParameters\":[\"p1\"],\"mBindings\":[\"v1\"]}";

    @Test
    public void startAndStopEndpoint() {
        MessageReceiver receiver = Mockito.mock(MessageReceiver.class);
        MessageSender sender = Mockito.mock(MessageSender.class);
        BSPLEndpoint endpoint = new BSPLEndpoint(sender, receiver, null, null, null, null, null);
        endpoint.start();
        endpoint.stop();

        InOrder inOrder = Mockito.inOrder(receiver);
        inOrder.verify(receiver).start();
        inOrder.verify(receiver).stop();
    }

    @Test
    public void sendMessage() throws IOException {
        MessageReceiver receiver = Mockito.mock(MessageReceiver.class);
        MessageSender sender = Mockito.mock(MessageSender.class);
        BSPLReferenceSchema.Builder referenceSchemaBuilder = new BSPLReferenceSchema.Builder("m", "S", "R", Arrays.asList("p1"), Arrays.asList("p1"));
        BSPLProtocolSchema schema = new BSPLProtocolSchema(Arrays.asList(referenceSchemaBuilder.build()));
        RelationalHistory history = Mockito.mock(RelationalHistory.class);
        SQLStatementCompiler compiler = Mockito.mock(SQLStatementCompiler.class);
        Mockito.when(compiler.compileInsertStatement(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn("INSERT INTO m (\"p1\") VALUES (\"v1\")");
        BSPLViabilityChecker checker = Mockito.mock(BSPLViabilityChecker.class);
        Mockito.when(checker.check(Mockito.any(), Mockito.any())).thenReturn(ViabilityCheckResult.VIABLE);

        BSPLEndpoint endpoint = new BSPLEndpoint(sender, receiver, schema, history, compiler, null, checker);

        endpoint.start();
        endpoint.send(new BSPLMessage(JSON_STRING));
        endpoint.stop();
        
        //Mockito.verify(checker).check(new BSPLMessage(JSON_STRING), referenceSchemaBuilder.build());
        Mockito.verify(compiler).compileInsertStatement("m", Arrays.asList("p1"), Arrays.asList("v1"));
        Mockito.verify(history).update(Mockito.anyString());
        Mockito.verify(sender).send(JSON_STRING, new InetSocketAddress("hr", 1234));
    }

}
