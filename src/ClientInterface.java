import java.rmi.Remote;
import java.rmi.RemoteException;

interface ClientInterface extends Remote {

    // Returns computed value from prime, primitive root
    // and private value
    int getValue() throws RemoteException;
}
