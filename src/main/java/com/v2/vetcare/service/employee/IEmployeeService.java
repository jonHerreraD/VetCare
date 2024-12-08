package com.v2.vetcare.service.employee;

import com.v2.vetcare.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEmployeeService {
    List<Employee> getAllEmployees();
}
