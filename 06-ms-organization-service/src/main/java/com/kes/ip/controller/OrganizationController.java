package com.kes.ip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kes.ip.dto.OrganizationRequestDto;
import com.kes.ip.dto.OrganizationResponseDto;
import com.kes.ip.service.OrganizationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/organizations")
@AllArgsConstructor
public class OrganizationController {

	private OrganizationService organizationService;

	@PostMapping
	public ResponseEntity<OrganizationResponseDto> createOrganization(
			@RequestBody @Valid OrganizationRequestDto orgRequestDto) {
		return new ResponseEntity<>(organizationService.saveOrganization(orgRequestDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrganizationResponseDto>> fetchAllOrganizations() {
		return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrganizationResponseDto> fetchOrganizationById(@PathVariable("id") Integer organizationId) {
		return new ResponseEntity<>(organizationService.getOrganizationById(organizationId), HttpStatus.OK);
	}

	@GetMapping("/code/{org-code}")
	public ResponseEntity<OrganizationResponseDto> fetchOrganizationByCode(
			@PathVariable("org-code") String organizationCode) {
		return new ResponseEntity<>(organizationService.getOrganizationByCode(organizationCode), HttpStatus.OK);
	}

	@PutMapping("/{org-code}")
	public ResponseEntity<OrganizationResponseDto> updateOrganizationByCode(
			@PathVariable("org-code") String organizationCode,
			@RequestBody @Valid OrganizationRequestDto orgRequestDto) {
		return new ResponseEntity<>(organizationService.updateOrganization(organizationCode, orgRequestDto),
				HttpStatus.OK);
	}

	@DeleteMapping("/{org-code}")
	public ResponseEntity<String> deleteOrganizationByCode(@PathVariable("org-code") String organizationCode) {
		return new ResponseEntity<>(organizationService.deleteOrganizationByCode(organizationCode), HttpStatus.OK);
	}

}
