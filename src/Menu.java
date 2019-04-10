import java.util.Scanner;

public class Menu {

    static private Scanner reader = new Scanner(System.in);

    private Menu() {
    }

    private static Integer currentMenu = 0;
    private static Integer currentEmpl = 0;

    public static Menu getInstance() {
        return MenuHolder.INSTANCE;
    }

    private static class MenuHolder {
        private static final Menu INSTANCE = new Menu();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(){
        while (true){
            Menu.clearScreen();
            switch (Menu.currentMenu){
                case 1:
                    Menu.printEmployees();
                    break;
                case 2:
                    Menu.addEmployee();
                    break;
                case 3:
                    Menu.removeEmployee();
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 0:
                default:
                    Menu.printMain();
                    break;
            }
        }
    }

    private static void printMain(){
        System.out.println("MENU");
        System.out.println("\t1. Lista pracowników");
        System.out.println("\t2. Dodaj pracownika");
        System.out.println("\t3. Usuń pracownika");
        System.out.println("\t4. Kopia zapasowa");
        System.out.println("\t5. Wyjście");
        System.out.print("Wybór> ");
        Menu.currentMenu = reader.nextInt();
    }

    private static void printEmployees(){
        System.out.println("1. Lista pracowników\n");
        EmpList.print(Menu.currentEmpl);
        System.out.println("");
        for (int i = 0; i < 60; ++i){
            System.out.print(" ");
        }
        System.out.println("[Pozycja: " + (Menu.currentEmpl + 1) + "/" + EmpList.size() + "]");
        System.out.println("[N] - następny");
        System.out.println("[Q] - powrót");
        String t = reader.next();
        if ((t.equals("n") || t.equals("N")) && Menu.currentEmpl  < (EmpList.size() - 1))
            Menu.currentEmpl += 1;
        else if ((t.equals("n") || t.equals("N")) && Menu.currentEmpl == EmpList.size() - 1)
            Menu.currentEmpl = 0;
        else if (t.equals("q") || t.equals("Q")){
            Menu.currentEmpl = 0;
            Menu.currentMenu = 0;
        }
    }

    private static void addEmployee() {
        System.out.println("2. Dodaj pracownika\n");
        System.out.print("\t[D]yrektor/[H]andlowiec: ");
        String opt = reader.next();
        if (!opt.equals("D") && !opt.equals("d") && !opt.equals("H") && !opt.equals("H"))
            return;

        Employee emp;
        for (int i = 0; i < 60; ++i)
            System.out.print("-");
        System.out.println("");

        System.out.print("\tIdentyfikator PESEL         : ");
        String pesel = reader.next();
        System.out.print("\tImię                        : ");
        String name = reader.next();
        System.out.print("\tNazwisko                    : ");
        String lastname = reader.next();
        System.out.print("\tWynagrodzenie (zł)          : ");
        String sSalary = reader.next();
        Integer iSalary = 0;
        System.out.print("\tTelefon służbowy numer      : ");
        String phone = reader.next();
        try {
            iSalary = Integer.parseInt(sSalary);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(1);
        }
        if (opt.equals("D") || opt.equals("d")) {
            System.out.print("\tDodatek sluzbowy (zł)       : ");
            int iAllowance = 0;
            int iCostLimit = 0;
            try {
                String sAllowance = reader.next();
                iAllowance = Integer.parseInt(sAllowance);
            } catch (Exception e){
                System.out.print(e.toString());
                System.exit(2);
            }
            System.out.print("\tKarta służbowa numer        : ");
            String cardNumber = reader.next();

            System.out.print("\tLimit kosztów/miesiąc (zł)  : ");

            try {
                String sCostLimit = reader.next();
                iCostLimit = Integer.parseInt(sCostLimit);
            } catch (Exception e){
                System.out.println(e.toString());
                System.exit(3);
            }

            emp = new Director(pesel,name,lastname, iSalary, phone,iAllowance, cardNumber, iCostLimit);

        } else {
            System.out.print("\tProwizja (%)                : ");
            int iCommision = 0;
            try {
                String sCommision = reader.next();
                iCommision = Integer.parseInt(sCommision);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.exit(4);
            }
            System.out.print("\tLimit prowizji/miesiąc (zł) : ");
            int iCommisionLimit = 0;
            try {
                String sCommisionLimit = reader.next();
                iCommisionLimit = Integer.parseInt(sCommisionLimit);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.exit(5);
            }
            emp = new Merchant(pesel,name,lastname, iSalary, phone,iCommision,iCommisionLimit);
        }
        System.out.println("\n[Z] - zapisz");
        System.out.println("[Q] - porzuć");
        String save = "";
        while (!save.equals("z") && !save.equals("Z") && !(save.equals("q") && !save.equals("Q"))){
            save = reader.next();
        }

        if (save.equals("z") || save.equals("Z")) {
            EmpList.addEmployee(emp);
        }
        Menu.currentMenu = 0;
    }

    private static void removeEmployee(){
        System.out.println("3. Usuń pracownika\n");
        System.out.print("\tPodaj identyfikator PESEL: ");
        String pesel = reader.next();

        int idx = EmpList.findEmployeeWithPesel(pesel);
        Employee emp = null;

        if (idx > -1) {
            emp = EmpList.getEmployee(idx);
        }

        if (emp == null) {
            System.out.println("\nBłąd: Nie znaleziono pracownika o podanym numerze PESEL");
            System.out.println("\n[P] - Pownów");
            System.out.println("[Q] - Porzuć");
            String save = "";
            while (!save.equals("p") && !save.equals("P") && !(save.equals("q") && !save.equals("Q"))){
                save = reader.next();
            }
            if (save.equals("q") || save.equals("Q")) {
                Menu.currentMenu = 0;
            }
        } else {
            for (int i = 0; i < 60; ++i)
                System.out.print("-");
            System.out.println("");
            emp.printWithoutPesel();
            for (int i = 0; i < 60; ++i)
                System.out.print("-");
            System.out.println("\n\n[U] - Usuń");
            System.out.println("[Q] - Porzuć");
            String save = "";
            while (!save.equals("u") && !save.equals("U") && !save.equals("q") && !save.equals("Q")){
                save = reader.next();
            }
            if (save.equals("u") || save.equals("U")) {
                EmpList.removeEmployee(idx);
            }
            Menu.currentMenu = 0;
        }

    }
}
