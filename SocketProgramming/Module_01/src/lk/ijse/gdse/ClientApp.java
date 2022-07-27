package lk.ijse.gdse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        final int PORT = 8000;
        Socket socket = new Socket("localhost",PORT);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("Hello server, kohomada oyata?");
        printWriter.flush();
    }
}
