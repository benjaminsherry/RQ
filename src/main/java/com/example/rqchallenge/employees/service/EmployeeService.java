package com.example.rqchallenge.employees.service;

import com.example.rqchallenge.employees.entity.Data;
import com.example.rqchallenge.employees.entity.Employee;
import com.example.rqchallenge.employees.entity.SimpleData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public List<Employee> getAllEmployees(ResponseEntity<Data> data) {
        List<Employee> list = new ArrayList<>(List.of(data.getBody().getData()));
        return list;
    }

    public List<Employee> getEmployeesByNameSearch(ResponseEntity<Data> data, String searchString) {
        List<Employee> list = new ArrayList<>(List.of(data.getBody().getData()));
        List<Employee> newlist = new ArrayList<>();
        for (Employee employee : list) {
            if(employee.getEmployee_name().contains(searchString)){
                newlist.add(employee);
            }
        }
        return newlist;
    }

    public Employee getEmployeeById(ResponseEntity<Data> data, String id) {
        Employee employee = data.getBody().getData()[0];
        return employee;
    }

    public Integer getHighestSalaryOfEmployees(ResponseEntity<Data> data) {
        List<Employee> list = new ArrayList<>(List.of(data.getBody().getData()));
        List<Integer> salaries = new ArrayList<>();
        for (Employee employee : list) {
            salaries.add(employee.getEmployee_salary());
        }

        return Collections.max(salaries);
    }

    public List<String> getTopTenHighestEarningEmployeeNames(ResponseEntity<Data> data) {
        List<Employee> employeesList = new ArrayList<>(List.of(data.getBody().getData()));
        List<Employee> top10employees = employeesList.stream().sorted(Comparator.comparingInt(Employee::getEmployee_salary).reversed()).limit(10).collect(Collectors.toList());

        List<String> names = new ArrayList<>();
        for (Employee employee : top10employees) {
            names.add(employee.getEmployee_name());
        }
        return names;
    }


    public String createEmployee(ResponseEntity<String> response) {
        Gson gson = new GsonBuilder().create();
        SimpleData data = gson.fromJson(response.getBody(),SimpleData.class);
        return data.getStatus();
    }

    public String deleteEmployee(Employee employee, ResponseEntity<SimpleData> response) {
        if(response.getBody().getStatus()=="success"){
            return employee.getEmployee_name();
        }
        else{
            return "Failed to delete record";
        }

    }


}