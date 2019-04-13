import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class employeesDaoSql implements employeesDao {
    @Override
    public List<Employee> getEmployees() {
        List<Employee> newList = new ArrayList<>();
        BasicDataSource ds = ConnectionPool.getDataSource();
        try {
            Connection con = ds.getConnection();
            PreparedStatement queryEmpl = con.prepareStatement("SELECT * FROM employees");
            ResultSet empRs = queryEmpl.executeQuery();
            while (empRs.next()){
                String pesel = empRs.getString("pesel");
                String type = empRs.getString("type");


                String table;

                if (type.equals("M")){
                    table = "merchants";
                } else {
                    table = "directors";
                }

                PreparedStatement innerQuery = con.prepareStatement("SELECT * FROM employees JOIN " + table +
                        " WHERE employees.pesel=" + pesel +
                        " AND " + table +".pesel=" + pesel);

                ResultSet innerRs = innerQuery.executeQuery();
                innerRs.next();
                String name = innerRs.getString("name");
                String surname = innerRs.getString("surname");
                int salary = innerRs.getInt("salary");
                String phone = innerRs.getString("phone");


                Employee emp;
                if (type.equals("M")){
                    int commision = innerRs.getInt("commision");
                    int commisionLimit = innerRs.getInt("commisionLimit");
                    emp = new Merchant(pesel, name, surname, salary, phone, commision, commisionLimit);
                } else {
                    int allowance = innerRs.getInt("allowance");
                    String card = innerRs.getString("card");
                    int costLimit = innerRs.getInt("costLimit");
                    emp = new Director(pesel, name, surname, salary, phone, allowance, card, costLimit);
                }
                newList.add(emp);
            }


        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(25);
        }
        return newList;
    }

    @Override
    public void saveEmployees() {
        BasicDataSource ds = ConnectionPool.getDataSource();

        try {
            Connection con = ds.getConnection();
            try {

                PreparedStatement delM = con.prepareStatement("DELETE FROM merchants");
                PreparedStatement delD = con.prepareStatement("DELETE FROM directors");
                PreparedStatement delE = con.prepareStatement("DELETE FROM employees");

                delM.execute();
                delD.execute();
                delE.execute();
                for (Employee e : EmpList.getEmployees()) {
                    e.saveEmployee(con);
                }
                con.commit();
                con.close();
                ds.close();
            } catch (Exception e) {
                try {
                    if (con != null)
                        con.rollback();
                } catch (Exception e2) {
                    System.out.println(e.toString());
                    System.out.println(e2);
                    System.exit(102);
                }
                System.out.println(e.toString());
                System.exit(101);
            }
        }catch (Exception ee) {
                System.out.println(ee.toString());
                System.exit(103);
        }
    }
}
