package com.nkolte.organizationservice.controller;

import com.nkolte.organizationservice.dto.OrganizationDto;
import com.nkolte.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@Tag(
        name = "CURD operations for Organization.",
        description = "CURD operations to Create Organization, GET Organization."
)
@AllArgsConstructor
@RestController
@RequestMapping("api/organizations/")
public class OrganizationController {
    private OrganizationService organizationService;

    @Operation(
            summary = "Create Organization REST API.",
            description = "Save new organization to database."
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status 201, created."
    )
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

    @Operation(
            summary = "GET Organization REST API.",
            description = "GET Organization REST API is used to get the Organization for given organization-code."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status code 200, OK."
    )
    @GetMapping("{organization-code}")
    public ResponseEntity<OrganizationDto>getOrganizationByCode(@PathVariable("organization-code") String orgCode){
        OrganizationDto organizationDto = organizationService.findOraganization(orgCode);
        return ResponseEntity.ok(organizationDto);
    }
}
