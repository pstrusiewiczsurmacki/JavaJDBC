import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RmiInterface extends Remote {
    public String helloTo(String name) throws RemoteException;
    public String authenticate(String username, String password) throws RuntimeException;
}
