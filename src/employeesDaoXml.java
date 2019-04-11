import org.w3c.dom.DocumentType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class employeesDaoXml implements employeesDao {
    private generated.Employees eList;
    private String filename;

    public employeesDaoXml(String filename){
        this.filename = filename;
    }

    @Override
    public List<Employee> getEmployees(){
        List<Employee> newList = new ArrayList<Employee>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(generated.ObjectFactory.class);
            InputStream inStream = new FileInputStream(this.filename);
            //DocumentType documentType = ((JAXBElement<DocumentType>) jaxbContext.createUnmarshaller().unmarshal(inStream)).getValue();
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            eList = (generated.Employees) jaxbUnmarshaller.unmarshal(inStream);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(973);
        }

        for (generated.Employee e : eList.getEmployee()) {
            Employee emp;
            if (e instanceof generated.Director) {
                emp = new Director(e.getPesel(),e.getName(),e.getSurname(),e.getSalary(),e.getPhone(),
                        ((generated.Director) e).getAllowance(),((generated.Director) e).getCard(),
                        ((generated.Director) e).getCostLimit());
            } else {
                emp = new Merchant(e.getPesel(),e.getName(),e.getSurname(),e.getSalary(),e.getPhone(),
                        ((generated.Merchant) e).getCommision(),((generated.Merchant) e).getCommisionLimit());
            }
            newList.add(emp);
        }

        return newList;
    }

    @Override
    public void saveEmployees(){

    }
}
