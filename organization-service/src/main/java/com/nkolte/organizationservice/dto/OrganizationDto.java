package com.nkolte.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
}
