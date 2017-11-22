import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) {

        //String serverName = args[0];
        //String username = args[1];

        Socket socket;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5137);
            InputStream serverInputStream = socket.getInputStream();
            OutputStream serverOutputStream = socket.getOutputStream();

            int x = serverInputStream.read();
            int prime = serverInputStream.read();
            int primitiveRoot = serverInputStream.read();

            KeyGenerator generator = new KeyGenerator(prime,primitiveRoot);
            int y = generator.generateValue();
            serverOutputStream.write(y);
            int key = generator.generateKey(x);
            System.out.println(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
