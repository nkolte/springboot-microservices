package com.nkolte.employeeservice.controller;

import com.nkolte.employeeservice.dto.APIResponseDto;
import com.nkolte.employeeservice.dto.EmployeeDto;
import com.nkolte.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees/")
@Tag(
        name = "CURD operations for Employee.",
        description = "CURD operations to create Employee, Get Employee."
)
public class EmployeeController {
    private EmployeeService employeeService;

    @Operation(
            summary = "Create employee REST API.",
            description = "Saves new employee to Database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201, created."
    )
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
@Operation(
        summary = "GET Employee REST API.",
        description = "GET Employee REST API is used to get the Employee for given ID."
)
@ApiResponse(
        responseCode = "200",
        description = "HTTP status code 200, OK."
)
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto>findEmployeeById(@PathVariable("id") Long id){
        APIResponseDto apiResponseDto = employeeService.findEmployeeById(id);

        return ResponseEntity.ok(apiResponseDto);
    }
}
