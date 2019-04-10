import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.util.List;

public class employeesDaoImpl implements employeesDao {
    @Override
    public List<Employee> getEmployees() {
        return null;
    }

    @Override
    public void saveEmplyees() {
        //ConnectionPool pool = ConnectionPool.create("")
        BasicDataSource ds = ConnectionPool.getDataSource();
        try {
            Connection con = ds.getConnection();
            for (Employee e : EmpList.getEmployees()) {
                e.saveEmployee(con);
            }
            con.commit();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(101);
        }
    }
}
