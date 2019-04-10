public class Main {

    public static void main(String[] args) {

        //Director d = new Director("999","Jan", "Kowalski",  3000, "500100100", 5000, "888", 345);
        Merchant m = new Merchant("123", "Adam", "Nowak", 4000, "888100200", 600, 6000);
        //EmpList.addEmployee(d);
        EmpList.addEmployee(m);
        //EmpList.print();
        //Menu.print();
        employeesDao ed = new employeesDaoImpl();
        ed.saveEmplyees();
        Menu.print();
    }
}
