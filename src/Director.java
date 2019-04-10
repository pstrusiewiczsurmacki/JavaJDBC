import java.sql.Connection;

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
    public void saveEmployee(Connection con){}
}
