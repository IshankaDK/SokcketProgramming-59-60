import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    @FXML
    private TextArea textArea;

    @FXML
    private TextField textMessage;

    final int PORT = 5000;
    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message = "";


    public void initialize(){
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                textArea.appendText("Server Started..");
                accept = serverSocket.accept();
                textArea.appendText("\nClient Connected..");

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nClient : " + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void sendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(textMessage.getText().trim());
        dataOutputStream.flush();
    }

}
