package com.kes.ip.service;

import java.util.List;

import com.kes.ip.dto.OrganizationRequestDto;
import com.kes.ip.dto.OrganizationResponseDto;

public interface OrganizationService {

	OrganizationResponseDto saveOrganization(OrganizationRequestDto orgReqDto);

	List<OrganizationResponseDto> getAllOrganizations();

	OrganizationResponseDto getOrganizationById(Integer organizationId);

	OrganizationResponseDto getOrganizationByCode(String organizationCode);

	OrganizationResponseDto updateOrganization(String organizationCode, OrganizationRequestDto orgReqDto);

	String deleteOrganizationByCode(String organizationCode);
}
