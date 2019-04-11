import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class employeesDaoSocket implements employeesDao {
    private String address;
    private int port;

    public employeesDaoSocket(String address, String port) {
        this.address = address;
        this.port = Integer.parseInt(port);
    }

    @Override
    public List<Employee> getEmployees() {
        for (int i = 0; i < 60; ++i)
            System.out.print("-");
        try {
            System.out.print("Ustanawianie połączenia... ");
            Socket socket = new Socket(this.address, this.port);
            System.out.println("Sukces");

            System.out.print("Pobieranie...               ");

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);

            @SuppressWarnings("unchecked")
            List<Employee> newList = (List<Employee>) objInputStream.readObject();

            if (newList != null) {
                System.out.println("Sukces");
                return newList;
            } else {
                System.out.println("Niepowodzenie");
            }
            for (int i = 0; i < 60; ++i)
                System.out.print("-");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(334);
        }
        return null;
    }

    @Override
    public void saveEmployees() {
        return;
    }
}
