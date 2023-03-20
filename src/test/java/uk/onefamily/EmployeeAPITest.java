package uk.onefamily;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import uk.onefamily.api.Employee;
import uk.onefamily.api.EmployeeObj;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EmployeeAPITest {
    Employee employee = new Employee();
    List<EmployeeObj> listOfEmployees;
    EmployeeObj emp1, emp2;

    @Test(groups = "employeeCount")
    public void printEmployeeCount() throws Exception {
        Response response = employee.getEmployees("employees");
        assertEquals(response.statusCode(), 200);
        listOfEmployees = response.jsonPath().getList("data", EmployeeObj.class);
        assertTrue(listOfEmployees.size() > 0, "Employee count is 0");
        System.out.println("Number of Employees " + listOfEmployees.size());
    }

    @Test(groups = "employeeNames", dependsOnGroups = "employeeCount")
    public void printEmployeeNames() {
        System.out.println("Names of Employees:");
        listOfEmployees.stream().forEach(emp -> System.out.println(emp.getEmployee_name()));
    }

    @Test(groups = "createemployees")
    public void createEmployees() throws Exception {
        String jsonStrCreateEmp1 = "{\"name\":\"william Rose\", \"age\":\"36\", \"salary\":15000}";
        String jsonStrCreateEmp2 = "{\"name\":\"mary Rose\", \"age\":\"37\", \"salary\":10000}";
        Response createEmpResp = employee.addEmployees(jsonStrCreateEmp1);
        System.out.println(createEmpResp.getBody().asPrettyString());
        emp1 = createEmpResp.jsonPath().getObject("data", EmployeeObj.class);
        assertEquals(createEmpResp.statusCode(), 200);
        createEmpResp = employee.addEmployees(jsonStrCreateEmp2);
        System.out.println(createEmpResp.getBody().asPrettyString());
        emp2 = createEmpResp.jsonPath().getObject("data", EmployeeObj.class);
        assertEquals(createEmpResp.statusCode(), 200);

    }

    @Test(groups = "updateemployee", dependsOnGroups = "createemployees")
    public void updateEmp2Sal() throws Exception {
        String salary = emp1.getEmployee_salary();
        String new_salary = String.valueOf(Integer.valueOf(salary) + 10000);
        emp1.setEmployee_salary(new_salary);
        Response updateResponse = employee.UpdateEmployee(emp1);
        assertTrue(updateResponse.statusCode() == 200);
        Response response = employee.getEmployees(String.valueOf(emp1.getId()));
        assertTrue(response.getBody().jsonPath().get("data.salary").equals(emp1.getEmployee_salary()));
    }

    @Test(groups = "deleteemployees", dependsOnGroups = "createemployees")
    public void deleteAnEmployee() throws Exception {
        Response deleteResp = employee.deleteEmployee(emp2);
        assertTrue(deleteResp.statusCode() == 200);
        assertTrue(deleteResp.getBody().jsonPath().get("message").equals("Successfully! Record has been deleted"));

    }

}
