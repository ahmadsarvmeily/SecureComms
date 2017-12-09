import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyClient {

    public static void main(String[] args) {

        String username = "as20g16";//args[1];
        String serverName = "localhost"; //args[0];

        try {

            ServerInterface serverHandle = null;
            try {
                serverHandle =(ServerInterface) Naming.lookup("//" + serverName + "/MyServer");
            } catch (NotBoundException | MalformedURLException e) {
                e.printStackTrace();
            }

            String serverHandleStatus = serverHandle != null ? "[ OK ]" : "[FAIL]";
            System.out.println(serverHandleStatus + " Obtain server handle");

            int prime = serverHandle.getPrime();
            int primRoot = serverHandle.getPrimRoot();

            KeyGenerator clientKeyGen = new KeyGenerator(prime,primRoot);
            ClientInterface client = new ClientImpl(clientKeyGen.generateValue());
            System.out.println("Requesting secure connection...");
            serverHandle.requestSecureConnection(client);

            int x = serverHandle.getValue(client);

            int key = clientKeyGen.generateKey(x);
            System.out.println("Secret shared key established.");
            System.out.println("Fetching cipher text...\n");
            String cipherText = serverHandle.getCipherText(client, username);
            System.out.println("Cipher text: "+cipherText);
            System.out.println("Plain text: "+Crypto.decrypt(cipherText,key));
            System.exit(0);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
