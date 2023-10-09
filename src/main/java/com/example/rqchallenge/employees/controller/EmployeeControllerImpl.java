package com.example.rqchallenge.employees.controller;

import com.example.rqchallenge.RqChallengeApplication;
import com.example.rqchallenge.employees.entity.Data;
import com.example.rqchallenge.employees.entity.Employee;
import com.example.rqchallenge.employees.entity.SimpleData;
import com.example.rqchallenge.employees.service.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


@RestController
public class EmployeeControllerImpl implements EmployeeController{

    String url = "https://dummy.restapiexample.com/api/v1/";

    RestTemplate restTemplate =  new RestTemplate();

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        ResponseEntity<Data> response = restTemplate.getForEntity(url+ "employees", Data.class);
        return new ResponseEntity<>(employeeService.getAllEmployees(response), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<Employee>> getEmployeesByNameSearch(String searchString) {
        ResponseEntity<Data> response = restTemplate.getForEntity(url+ "employees", Data.class);
        return new ResponseEntity<>(employeeService.getEmployeesByNameSearch(response,searchString), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(String id) {
        ResponseEntity<Data> response = restTemplate.getForEntity(url+ "employee/"+id, Data.class);
        return new ResponseEntity<>(employeeService.getEmployeeById(response,id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getHighestSalaryOfEmployees() {
        ResponseEntity<Data> response = restTemplate.getForEntity(url+ "employees", Data.class);
        return new ResponseEntity<>(employeeService.getHighestSalaryOfEmployees(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames() {
        ResponseEntity<Data> response = restTemplate.getForEntity(url+ "employees", Data.class);
        return new ResponseEntity<>(employeeService.getTopTenHighestEarningEmployeeNames(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createEmployee(Map<String, Object> employeeInput) {
        try{
            ResponseEntity<String> response = restTemplate.postForEntity(url + "create", employeeInput, String.class);
            return new ResponseEntity<>(employeeService.createEmployee(response), HttpStatus.CREATED);
        } catch (Exception e){
                return new ResponseEntity<>("failure", HttpStatus.NOT_IMPLEMENTED);
            }
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(String id) {
        Employee employee = getEmployeeById(id).getBody();
        //should add delay as sometimes get 429 error due to double call
        ResponseEntity<SimpleData> response = restTemplate.exchange(url+"delete/{id}",HttpMethod.DELETE, new HttpEntity<>(new Data(null,null,null)), SimpleData.class,id);
        return new ResponseEntity<>(employeeService.deleteEmployee(employee,response),HttpStatus.OK);
    }

}
