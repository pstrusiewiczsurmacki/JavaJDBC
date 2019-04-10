import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Director extends Employee {
    private Integer allowance;
    private String card;
    private Integer costLimit;

    public Director(String pesel, String name, String surname, Integer salary, String phone,Integer allowance, String card, Integer costLimit){
        super(pesel, name, surname, salary, phone);
        this.position = "D";
        this.allowance = allowance;
        this.card = card;
        this.costLimit = costLimit;
    }

    private void internalPrint() {
        System.out.println("\tIdentyfikator PESEL         : " + this.pesel);
        System.out.println("\tDodatek sluzbowy (zł)       : " + this.allowance);
        System.out.println("\tKarta służbowa numer        : " + this.card);
        System.out.println("\tLimit kosztów/miesiąc (zł)  : " + this.costLimit);
    }

    @Override
    public void print() {
        this.commonPrint();
        this.internalPrint();
    }

    @Override
    public void printWithoutPesel() {
        this.commonPrintWithoutPesel();
        this.internalPrint();
    }

    @Override
    public void saveEmployee(Connection con){
        try {
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO employees VALUES (" +
                    addValue(pesel) + "," +addValue(name) +"," + addValue(surname) + "," +
                    salary +"," +addValue(phone) + ",'D')");
            System.out.println(ps1.toString());
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO directors VALUES (" +
                    addValue(pesel) + "," + allowance + "," + addValue(card) + "," + costLimit+ ")");

            ps1.execute();
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(333);
        }
    }
}
