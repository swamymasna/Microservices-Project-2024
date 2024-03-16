package com.kes.ip.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kes.ip.dto.OrganizationResponseDto;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationClient {

	@GetMapping("/api/organizations/code/{org-code}")
	public OrganizationResponseDto fetchOrganizationByCode(@PathVariable("org-code") String organizationCode);
}
