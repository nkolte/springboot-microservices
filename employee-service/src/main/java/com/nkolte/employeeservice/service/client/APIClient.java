package com.nkolte.employeeservice.service.client;

import com.nkolte.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartmentById(@PathVariable("department-code") String departmentCode);
}
