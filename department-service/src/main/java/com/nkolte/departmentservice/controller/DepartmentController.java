package com.nkolte.departmentservice.controller;

import com.nkolte.departmentservice.dto.DepartmentDto;
import com.nkolte.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departments/")
public class DepartmentController {

    private DepartmentService departmentService;

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

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto>getDepartmentByCode(@PathVariable("department-code")
                                                                String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        return ResponseEntity.ok(departmentDto);
    }
}
