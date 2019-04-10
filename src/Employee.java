import java.io.Console;
import java.sql.Connection;

abstract class Employee {
    protected String pesel;
    protected String name;
    protected String surname;
    protected String position;
    protected Integer salary;
    protected String phone;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    protected void commonPrint(){

        System.out.println("\tIdentyfikator PESEL         : " + this.pesel);
        System.out.println("\tImię                        : " + this.name);
        System.out.println("\tNazwisko                    : " + this.surname);
        System.out.println("\tWynagrodzenie (zł)          : " + this.salary);
        System.out.println("\tTelefon służbowy numer      : " + this.phone);
    }

    protected void commonPrintWithoutPesel(){
        System.out.println("\tImię                        : " + this.name);
        System.out.println("\tNazwisko                    : " + this.surname);
        System.out.println("\tWynagrodzenie (zł)          : " + this.salary);
        System.out.println("\tTelefon służbowy numer      : " + this.phone);
    }

    public Employee(String pesel, String name, String surname, Integer salary, String phone) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.phone = phone;
    }

    abstract public void print();
    abstract public void printWithoutPesel();
    abstract public void saveEmployee(Connection con);

    protected String addValue(String val){
        return "'" + val +"'";
    }
}
