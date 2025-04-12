package com.nkolte.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "OrganizationDto model information."
)
public class OrganizationDto {

    @Schema(description = "Organization id.")
    private long id;
    @Schema(description = "Organization name.")
    private String organizationName;
    @Schema(description = "Organization description.")
    private String organizationDescription;
    @Schema(description = "Organization code.")
    private String organizationCode;
}
