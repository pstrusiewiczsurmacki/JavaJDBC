import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface RmiInterface extends Remote {
    public String authenticate(String username, String password) throws RuntimeException;
}
