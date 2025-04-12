package com.nkolte.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "EmployeeDto model information."
)
public class EmployeeDto {
    @Schema(description = "Employee's id.")
    private Long id;
    @Schema(description = "Employee's first name.")
    private String firstName;
    @Schema(description = "Employee's last name.")
    private String lastName;
    @Schema(description = "Employee's email.")
    private String email;
    @Schema(description = "Employee's department code.")
    private String departmentCode;
    @Schema(description = "Employee's organization code.")
    private String organizationCode;
}
