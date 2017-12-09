import java.io.IOException;
import java.rmi.Naming;

public class MyServer {

    public static void main(String[] args) {
        int prime = 191;
        int primitiveRoot = 131;

        CipherTextProxy cipherTextProxy = new CipherTextProxy();
        try {
            Naming.rebind("MyServer", new ServerImpl(prime, primitiveRoot, cipherTextProxy));
            System.out.println("MyServer is ready.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}