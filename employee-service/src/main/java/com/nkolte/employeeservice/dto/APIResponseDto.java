package com.nkolte.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "APIResponseDto Model information.")
public record APIResponseDto(EmployeeDto employeeDto, DepartmentDto departmentDto, OrganizationDto organizationDto) {
}
