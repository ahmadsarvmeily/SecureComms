import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    // Request secure connection from server, giving client handle for callbacks
    void requestSecureConnection(ClientInterface client) throws RemoteException;

    int getPrime() throws RemoteException;

    int getPrimRoot() throws RemoteException;

    int getValue(ClientInterface client) throws RemoteException;

    // Request cipher text from server
    String getCipherText(ClientInterface client, String userID) throws RemoteException;
}
