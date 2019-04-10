import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Merchant extends Employee {
    private Integer commision;
    private Integer commisionLimit;

    Merchant(String pesel, String name, String surname, Integer salary, String phone, Integer commision, Integer commisionLimit) {
        super(pesel, name, surname, salary, phone);
        this.commision = commision;
        this.commisionLimit = commisionLimit;
    }

    private void internalPrint(){
        System.out.println("\tProwizja (%)                : " + this.commision);
        System.out.println("\tLimit prowizji/miesiąc (zł) : " + this.commisionLimit);
    }

    @Override
    public void print(){
        this.commonPrint();
        this.internalPrint();
    }

    @Override
    public void printWithoutPesel(){
        this.commonPrintWithoutPesel();
        this.internalPrint();
    }

    @Override
    public void saveEmployee(Connection con) {
        try {
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO employees VALUES (" +
                    addValue(pesel) + "," +addValue(name) +"," + addValue(surname) + "," +
                    salary +"," +addValue(phone) + ",'M')");
            System.out.println(ps1.toString());
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO merchants VALUES (" +
                    addValue(pesel) + "," + commision + "," + commisionLimit + ")");

            ps1.execute();
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(333);
        }
    }


}
