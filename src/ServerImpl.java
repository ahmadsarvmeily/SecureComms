import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    private CipherTextProxy proxy;
    private int prime, primRoot;
    private HashMap<ClientInterface,Integer> keyMap;
    private HashMap<ClientInterface,Integer> valueMap;

    ServerImpl(int prime, int primRoot, CipherTextProxy proxy) throws RemoteException {
        super();
        keyMap = new HashMap<>();
        valueMap = new HashMap<>();
        this.proxy = proxy;
        this.prime = prime;
        this.primRoot = primRoot;
    }

    @Override
    public void requestSecureConnection(ClientInterface client) throws RemoteException {

        KeyGenerator keyGen = new KeyGenerator(prime,primRoot);
        int y = client.getValue();
        int key = keyGen.generateKey(y);

        System.out.println("Secret shared key established.");

        synchronized (this) {
            keyMap.put(client, key);
        }

        synchronized (this) {
            valueMap.put(client, keyGen.generateValue());
        }
        System.out.println("Secure connection established.");
    }

    @Override
    public int getPrime() throws RemoteException {
        return prime;
    }

    @Override
    public int getPrimRoot() throws RemoteException {
        return primRoot;
    }

    @Override
    public synchronized int getValue(ClientInterface client) throws RemoteException {
        return valueMap.get(client);
    }

    @Override
    public String getCipherText(ClientInterface client, String userID) throws RemoteException {
        int key;
        synchronized (this) {
            key = keyMap.get(client);
        }
        System.out.println("Sending cipher text to "+userID+"...");
        return proxy.get(userID,key);
    }
}
