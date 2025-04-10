package com.nkolte.organizationservice.service.impl;

import com.nkolte.organizationservice.dto.OrganizationDto;
import com.nkolte.organizationservice.entity.Organization;
import com.nkolte.organizationservice.repository.OrganizationRepository;
import com.nkolte.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;
    private ModelMapper modelMapper;

    public OrganizationDto saveOraganization(OrganizationDto organizationDto){
        Organization organization = modelMapper.map(organizationDto,Organization.class);
        Organization savedOrg = organizationRepository.save(organization);

        return modelMapper.map(savedOrg,OrganizationDto.class);
    }

    @Override
    public OrganizationDto findOraganization(String orgCode) {
        Organization organization = organizationRepository.findByOrganizationCode(orgCode);
        return modelMapper.map(organization,OrganizationDto.class);
    }
}
