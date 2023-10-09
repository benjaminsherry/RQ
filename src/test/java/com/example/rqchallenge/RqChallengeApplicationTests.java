package com.example.rqchallenge;

import com.example.rqchallenge.employees.controller.EmployeeControllerImpl;
import com.example.rqchallenge.employees.entity.Data;
import com.example.rqchallenge.employees.entity.Employee;
import com.example.rqchallenge.employees.entity.SimpleData;
import com.example.rqchallenge.employees.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RqChallengeApplicationTests {

    @InjectMocks
    EmployeeControllerImpl employeeControllerImpl;

    @Mock
    RestTemplate restTemplate;

    @Mock
    private EmployeeService employeeService;

    String url = "https://dummy.restapiexample.com/api/v1/";

    @Test
    void getAllEmployees() {
        Employee[] employees = new Employee[2];
        employees[0] = new Employee(1,"Ben",1234,25,null);
        employees[1] = new Employee(2,"Bob",124534,105,null);
        Data data = new Data("success",employees,"success message");
        when(restTemplate.getForEntity(url+ "employees", Data.class)).thenReturn(new ResponseEntity(data, HttpStatus.OK));
        when(employeeService.getAllEmployees(new ResponseEntity(data, HttpStatus.OK))).thenReturn(List.of(employees));
        ResponseEntity<List<Employee>> responseEntity =  employeeControllerImpl.getAllEmployees();
        Assertions.assertEquals(responseEntity.getBody(),List.of(employees));
    }

    @Test
    void getEmployeesByNameSearch() {
        Employee[] employees = new Employee[1];
        employees[0] = new Employee(1,"Ben",1234,25,null);
        Data data = new Data("success",employees,"success message");
        when(restTemplate.getForEntity(url+ "employees", Data.class)).thenReturn(new ResponseEntity(data, HttpStatus.OK));
        when(employeeService.getEmployeesByNameSearch(new ResponseEntity(data, HttpStatus.OK),"Ben")).thenReturn(List.of(employees));
        ResponseEntity<List<Employee>> responseEntity =  employeeControllerImpl.getEmployeesByNameSearch("Ben");
        Assertions.assertEquals(responseEntity.getBody(),List.of(employees));
    }

    @Test
    void getEmployeeById() {
        Employee[] employees = new Employee[1];
        employees[0] = new Employee(1,"Ben",1234,25,null);
        Data data = new Data("success",employees,"success message");
        when(restTemplate.getForEntity(url+ "employee/"+"1", Data.class)).thenReturn(new ResponseEntity(data, HttpStatus.OK));
        when(employeeService.getEmployeeById(new ResponseEntity(data, HttpStatus.OK),"1")).thenReturn(employees[0]);
        ResponseEntity<Employee> responseEntity =  employeeControllerImpl.getEmployeeById("1");
        Assertions.assertEquals(responseEntity.getBody(),employees[0]);
    }

    @Test
    void getHighestSalaryOfEmployees() {
        Employee[] employees = new Employee[2];
        employees[0] = new Employee(1,"Ben",1234,25,null);
        employees[1] = new Employee(2,"Bob",124534,105,null);
        Data data = new Data("success",employees,"success message");
        when(restTemplate.getForEntity(url+ "employees", Data.class)).thenReturn(new ResponseEntity(data, HttpStatus.OK));
        when(employeeService.getHighestSalaryOfEmployees(new ResponseEntity(data, HttpStatus.OK))).thenReturn(employees[1].getEmployee_salary());
        ResponseEntity<Integer> responseEntity =  employeeControllerImpl.getHighestSalaryOfEmployees();
        Assertions.assertEquals(responseEntity.getBody(),employees[1].getEmployee_salary());
    }

    @Test
    void getTopTenHighestEarningEmployeeNames() {
        Employee[] employees = new Employee[12];
        employees[0] = new Employee(1,"Ben",1234,25,null);
        employees[1] = new Employee(2,"Bob",124534,105,null);
        employees[2] = new Employee(3,"Anna",5,105,null);
        employees[3] = new Employee(4,"George",2,105,null);
        employees[4] = new Employee(5,"Charles",999,105,null);
        employees[5] = new Employee(6,"Ed",101,105,null);
        employees[6] = new Employee(7,"Arron",54277452,105,null);
        employees[7] = new Employee(8,"Callum",4245,105,null);
        employees[8] = new Employee(9,"Lydia",45544,105,null);
        employees[9] = new Employee(10,"Devon",7878,105,null);
        employees[10] = new Employee(11,"Sasha",8,105,null);
        employees[11] = new Employee(12,"Nicki",986,105,null);
        Data data = new Data("success",employees,"success message");
        Employee[] employeestop10  = new Employee[10];
        employeestop10[0] = new Employee(1,"Ben",1234,25,null);
        employeestop10[1] = new Employee(2,"Bob",124534,105,null);
        employeestop10[4] = new Employee(5,"Charles",999,105,null);
        employeestop10[5] = new Employee(6,"Ed",101,105,null);
        employeestop10[6] = new Employee(7,"Arron",54277452,105,null);
        employeestop10[7] = new Employee(8,"Callum",4245,105,null);
        employeestop10[8] = new Employee(9,"Lydia",45544,105,null);
        employeestop10[9] = new Employee(10,"Devon",7878,105,null);
        employeestop10[3] = new Employee(11,"Sasha",8,105,null);
        employeestop10[2] = new Employee(12,"Nicki",986,105,null);
        when(restTemplate.getForEntity(url+ "employees", Data.class)).thenReturn(new ResponseEntity(data, HttpStatus.OK));
        when(employeeService.getTopTenHighestEarningEmployeeNames(new ResponseEntity(data, HttpStatus.OK))).thenReturn(List.of(employeestop10));
        ResponseEntity<List<String>> responseEntity =  employeeControllerImpl.getTopTenHighestEarningEmployeeNames();
        Assertions.assertEquals(responseEntity.getBody(),List.of(employeestop10));
    }


    @Test
    void createEmployee() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", "Ben");
        map.put("age", 25);
        map.put("salary", 55555);
        String response = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"employee_name\": \"Ben\",\n" +
                "        \"employee_salary\": 6000,\n" +
                "        \"employee_age\": 25,\n" +
                "        \"id\": 189\n" +
                "    },\n" +
                "    \"message\": \"Successfully! Record has been added.\"\n" +
                "}";
        when(restTemplate.postForEntity(url + "create",map, String.class)).thenReturn(new ResponseEntity(response, HttpStatus.OK));
        when(employeeService.createEmployee(new ResponseEntity(response, HttpStatus.OK))).thenReturn("success");
        ResponseEntity<String> responseEntity =  employeeControllerImpl.createEmployee(map);
        Assertions.assertEquals(responseEntity.getBody(),"success");
    }

}
