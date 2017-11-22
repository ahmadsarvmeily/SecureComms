import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    //TODO: Support multiple clients concurrently

    public static void main(String[] args) {
        int port = 5137;
        int prime = 191;
        int primitiveRoot = 131;

        ServerSocket serverSocket;
        Socket clientSocket;
        KeyGenerator generator = new KeyGenerator(prime, primitiveRoot);
        try {

            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            OutputStream clientOutputStream = clientSocket.getOutputStream();
            InputStream clientInputStream = clientSocket.getInputStream();

            int x = generator.generateValue();
            clientOutputStream.write(x);
            clientOutputStream.write(prime);
            clientOutputStream.write(primitiveRoot);

            int y = clientInputStream.read();
            int key = generator.generateKey(y);
            System.out.println(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
