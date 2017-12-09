import java.rmi.Remote;
import java.rmi.RemoteException;

interface ClientInterface extends Remote {

    int getValue() throws RemoteException;
}
