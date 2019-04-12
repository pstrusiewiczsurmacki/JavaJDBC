import java.io.*;
import java.net.Socket;
import java.util.List;

public class employeesDaoSocket implements employeesDao {
    private String address;
    private int port;
    private Socket socket;

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
            if (socket == null)
                socket = new Socket(this.address, this.port);
            System.out.println("Sukces");

            System.out.print("Pobieranie...               ");

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println("READY");

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

    public boolean sendMsg(String strategy) {
        try {
            if (socket == null)
                socket = new Socket(this.address, this.port);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            PrintWriter out = new PrintWriter(outputStream, true);
            out.println(strategy);

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String response = in.readLine();

            if (response.equals("OK")){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(74836);
        }
        return false;
    }
}
