package com.nkolte.employeeservice.service;


import com.nkolte.employeeservice.dto.APIResponseDto;
import com.nkolte.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    APIResponseDto findEmployeeById(Long id);
}
