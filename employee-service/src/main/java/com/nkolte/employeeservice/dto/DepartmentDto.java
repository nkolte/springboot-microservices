package com.nkolte.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "DepartmentDto model information."
)
public class DepartmentDto {
    @Schema(description = "Department id.")
    private Long id;
    @Schema(description = "Department Name.")
    private String departmentName;
    @Schema(description = "Department Description.")
    private String departmentDescription;
    @Schema(description = "Department Code.")
    private String departmentCode;
}
