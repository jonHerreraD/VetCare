package com.v2.vetcare.controller;

import com.v2.vetcare.model.Employee;
import com.v2.vetcare.response.ApiResponse;
import com.v2.vetcare.service.employee.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ApiResponse("Success",employeeList));
    }
}
