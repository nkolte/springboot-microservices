package com.nkolte.organizationservice.controller;

import com.nkolte.organizationservice.dto.OrganizationDto;
import com.nkolte.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("api/organizations/")
public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto>createOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOraganization(organizationDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(organizationDto.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(savedOrganizationDto);
    }

    @GetMapping("{org-code}")
    public ResponseEntity<OrganizationDto>getOrganizationByCode(@PathVariable("org-code") String orgCode){
        OrganizationDto organizationDto = organizationService.findOraganization(orgCode);
        return ResponseEntity.ok(organizationDto);
    }
}
