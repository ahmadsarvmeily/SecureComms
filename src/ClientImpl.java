import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {

    private int value;

    ClientImpl(int value) throws RemoteException {
        this.value = value;
    }

    @Override
    public int getValue() throws RemoteException {
        return value;
    }
}
