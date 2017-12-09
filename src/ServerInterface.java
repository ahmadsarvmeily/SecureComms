import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    void requestSecureConnection(ClientInterface client) throws RemoteException;

    int getPrime() throws RemoteException;

    int getPrimRoot() throws RemoteException;

    int getValue(ClientInterface client) throws RemoteException;

    String getCipherText(ClientInterface client, String userID) throws RemoteException;
}
