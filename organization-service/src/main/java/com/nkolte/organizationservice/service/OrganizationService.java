package com.nkolte.organizationservice.service;

import com.nkolte.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOraganization(OrganizationDto organizationDto);
    OrganizationDto findOraganization(String orgCode);
}
