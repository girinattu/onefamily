package uk.onefamily.api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;


public class Employee {

    RestSetUp baseClass = new RestSetUp();
    RequestSpecBuilder specBuilder;

    String baseUrl = "https://dummy.restapiexample.com/api/v1/";

    public Employee() {
        specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    public Response getEmployees(String url) {
        Response response = null;
        return baseClass.sendRequest(specBuilder, url, Method.GET);
    }

    public Response getEmployeeById(String id) {
        Response response = null;
        String url = "/employee/"+id;
        return baseClass.sendRequest(specBuilder, url, Method.GET);
    }

    public Response addEmployees(String employeeStr){
        String url = "create";
        specBuilder.setBody(employeeStr).build();
        return baseClass.sendRequest(specBuilder, url, Method.POST);
    }

    public Response UpdateEmployee(EmployeeObj employee){
        String url = "update/"+employee.getId();

        specBuilder.setBody(employee).build();
        return baseClass.sendRequest(specBuilder, url, Method.PUT);
    }

    public Response deleteEmployee(EmployeeObj employee){
        String url = "delete/"+employee.getId();
        return baseClass.sendRequest(specBuilder, url, Method.DELETE);
    }
    public static void main(String[] args) {
        Employee ee = new Employee();

//        ResponseBody response = ee.getEmployees("employee");
//        List<EmployeeObj> listOfEmployees = response.jsonPath().getList("data",EmployeeObj.class);
//        System.out.println(listOfEmployees.size());
//        listOfEmployees.stream().forEach(emp-> System.out.println(emp.getEmployee_name()));

    }

}
