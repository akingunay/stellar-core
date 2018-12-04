package uk.ac.lancaster.scc.turtles.stellar.core.communication.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TestPayloadSender {

    public void send(String payload, String hostname, int port) throws IOException {
        try (Socket socket = new Socket(hostname, port); PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            writer.write(payload);
            writer.flush();
        }
    }

}
