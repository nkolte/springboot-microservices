package com.nkolte.departmentservice.controller;

import com.nkolte.departmentservice.dto.DepartmentDto;
import com.nkolte.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(
        name = "CURD of operations for Department.",
        description = "CURD REST APIs- Create Department, Get Department."
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/departments/")
public class DepartmentController {

    private DepartmentService departmentService;

    @Operation(
            summary = "Create Department REST API",
            description = "Create Department REST API is used to save Department to database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201, created."
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedDepartmentDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedDepartmentDto);
    }

    @Operation(
            summary = "GET Department REST API",
            description = "GET Department REST API is used to get the Department for given department-code."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code 200 OK."
    )
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto>getDepartmentByCode(@PathVariable("department-code")
                                                                String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(departmentDto);
    }
}
