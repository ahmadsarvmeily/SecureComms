import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyClient {

    public static void main(String[] args) {

        // Fetch username and server name from command line args
        String username = args[1];
        String serverName = args[0];

        try {

            // Obtain server handle via RMI lookup
            ServerInterface serverHandle = null;
            try {
                serverHandle = (ServerInterface) Naming.lookup("//" + serverName + "/MyServer");
            } catch (NotBoundException | MalformedURLException e) {
                e.printStackTrace();
            }

            String serverHandleStatus = serverHandle != null ? "[ OK ]" : "[FAIL]";
            System.out.println(serverHandleStatus + " Obtain server handle");

            // Fetch prime number and primitive root from server
            int prime = serverHandle.getPrime();
            int primRoot = serverHandle.getPrimRoot();

            // Create new keygen from the prime and primitive root
            KeyGenerator clientKeyGen = new KeyGenerator(prime,primRoot);

            // Create client handle to be used by server
            ClientInterface client = new ClientImpl(clientKeyGen.generateValue());
            System.out.println("Requesting secure connection...");

            serverHandle.requestSecureConnection(client);

            // Fetch computed value from server
            int x = serverHandle.getValue(client);

            // Use keygen to generate private shared key
            int key = clientKeyGen.generateKey(x);

            System.out.println("Secret shared key established.");
            System.out.println("Fetching cipher text...\n");

            String cipherText = serverHandle.getCipherText(client, username);

            System.out.println("Cipher text: "+cipherText);

            // Decrypt cipher text
            String plainText = Crypto.decrypt(cipherText,key);

            System.out.println("Plain text: "+plainText);
            System.exit(0);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
