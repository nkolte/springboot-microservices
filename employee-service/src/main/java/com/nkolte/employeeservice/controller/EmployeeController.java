package com.nkolte.employeeservice.controller;

import com.nkolte.employeeservice.dto.APIResponseDto;
import com.nkolte.employeeservice.dto.EmployeeDto;
import com.nkolte.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees/")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto>createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedEmployeeDto.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(savedEmployeeDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto>findEmployeeById(@PathVariable("id") Long id){
        APIResponseDto apiResponseDto = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(apiResponseDto);
    }
}
