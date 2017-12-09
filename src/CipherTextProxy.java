import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CipherTextProxy {

    private CiphertextInterface ctstub;

    // Obtains reference to remote CiphertextInterface object
    CipherTextProxy() {
        try {
            System.setProperty( "java.security.policy", "mypolicy" );
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Registry ctreg = LocateRegistry.getRegistry( "svm-tjn1f15-comp2207.ecs.soton.ac.uk", 12345 );
            ctstub = (CiphertextInterface) ctreg.lookup( "CiphertextProvider" );
        }
        catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    // Fetches cipher text from remote CiphertextInterface object
    public String get(String userID, int key) throws RemoteException {
        return ctstub.get(userID,key);
    }
}
