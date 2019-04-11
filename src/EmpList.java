import java.util.ArrayList;
import java.util.List;

public class EmpList {

    private List<Employee> employees;

    private EmpList() {
        this.employees = new ArrayList<Employee>();
    }

    public static EmpList getInstance() {
        return EmpListHolder.INSTANCE;
    }

    private static class EmpListHolder {
        private static final EmpList INSTANCE = new EmpList();
    }

    public static void addEmployee(Employee e){
        getInstance().employees.add(e);
    }

    public static void print(){
        for (Employee e : getInstance().employees){
            e.print();
            System.out.println("");
        }
    }

    public static void print(Integer i){
        getInstance().employees.get(i).print();
    }

    public static int size(){
        return getInstance().employees.size();
    }

    public static void removeEmployeeWithPesel(String pesel) {
        int idx = findEmployeeWithPesel(pesel);
        if (idx > -1)
            getInstance().employees.remove(idx);
    }

    public static void removeEmployee(int index) {
        getInstance().employees.remove(index);
    }

    public static int findEmployeeWithPesel(String pesel) {
        EmpList empList = getInstance();
        int idx = 0;
        for (Employee e : empList.employees){
            if (e.pesel.equals(pesel)) {
                return idx;
            }
            idx++;
        }
        return -1;
    }

    public static Employee getEmployee(int index) {
        return getInstance().employees.get(index);
    }

    public static Employee getEmployeeWithPesel(String pesel){
        int idx = findEmployeeWithPesel(pesel);
        if (idx == -1) {
            return null;
        }
        EmpList empList = getInstance();
        return empList.employees.get(idx);
    }

    public static List<Employee> getEmployees() {
        return EmpList.getInstance().employees;
    }

    public static void changeList(List<Employee> newList) {
        getInstance().employees = newList;
    }
}
