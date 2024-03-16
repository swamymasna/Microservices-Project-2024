package com.kes.ip.service.impl;

import static com.kes.ip.utils.AppConstants.DELETE_ORGANIZATION_EXCEPTION;
import static com.kes.ip.utils.AppConstants.FETCH_ALL_ORGANIZATIONS_EXCEPTION;
import static com.kes.ip.utils.AppConstants.ORGANIZATION_DELETION_FAILED;
import static com.kes.ip.utils.AppConstants.ORGANIZATION_DELETION_SUCCEEDED;
import static com.kes.ip.utils.AppConstants.ORGANIZATION_NOT_FOUND_BY_CODE_EXCEPTION;
import static com.kes.ip.utils.AppConstants.ORGANIZATION_NOT_FOUND_EXCEPTION;
import static com.kes.ip.utils.AppConstants.SAVE_ORGANIZATION_EXCEPTION;
import static com.kes.ip.utils.AppConstants.UPDATE_ORGANIZATION_EXCEPTION;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kes.ip.dto.OrganizationRequestDto;
import com.kes.ip.dto.OrganizationResponseDto;
import com.kes.ip.entity.Organization;
import com.kes.ip.exception.OrganizationServiceBusinessException;
import com.kes.ip.exception.ResourceNotFoundException;
import com.kes.ip.props.AppProperties;
import com.kes.ip.repository.OrganizationRepository;
import com.kes.ip.service.OrganizationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

	private ModelMapper modelMapper;

	private OrganizationRepository organizationRepository;

	private AppProperties appProperties;

	@Override
	public OrganizationResponseDto saveOrganization(OrganizationRequestDto orgReqDto) {

		OrganizationResponseDto organizationResponseDto = null;

		Organization organization = null;

		try {
			organization = modelMapper.map(orgReqDto, Organization.class);

			organization = organizationRepository.save(organization);

			if (organization.getId() != null) {
				organizationResponseDto = modelMapper.map(organization, OrganizationResponseDto.class);
			} else {
				organizationResponseDto = null;
			}

		} catch (Exception e) {
			throw new OrganizationServiceBusinessException(
					String.format(appProperties.getMessages().get(SAVE_ORGANIZATION_EXCEPTION), e));
		}

		return organizationResponseDto;
	}

	@Override
	public List<OrganizationResponseDto> getAllOrganizations() {

		List<OrganizationResponseDto> listOrganizations = null;

		List<Organization> organizations = null;

		try {

			organizations = organizationRepository.findAll();

			if (!organizations.isEmpty()) {

				listOrganizations = organizations.stream()
						.map(org -> modelMapper.map(org, OrganizationResponseDto.class)).collect(Collectors.toList());

			} else {
				listOrganizations = Collections.emptyList();
			}

		} catch (Exception e) {
			throw new OrganizationServiceBusinessException(
					String.format(appProperties.getMessages().get(FETCH_ALL_ORGANIZATIONS_EXCEPTION), e));
		}

		return listOrganizations;
	}

	@Override
	public OrganizationResponseDto getOrganizationById(Integer organizationId) {

		Organization organization = organizationRepository.findById(organizationId)
				.orElseThrow(() -> new ResourceNotFoundException(String
						.format(appProperties.getMessages().get(ORGANIZATION_NOT_FOUND_EXCEPTION), organizationId)));

		return modelMapper.map(organization, OrganizationResponseDto.class);
	}

	@Override
	public OrganizationResponseDto getOrganizationByCode(String organizationCode) {

		Organization organization = organizationRepository.findByOrganizationCode(organizationCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(ORGANIZATION_NOT_FOUND_BY_CODE_EXCEPTION), organizationCode)));

		return modelMapper.map(organization, OrganizationResponseDto.class);
	}

	@Override
	public OrganizationResponseDto updateOrganization(String organizationCode, OrganizationRequestDto orgReqDto) {

		OrganizationResponseDto organizationResponseDto = null;

		Organization organization = organizationRepository.findByOrganizationCode(organizationCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(ORGANIZATION_NOT_FOUND_BY_CODE_EXCEPTION), organizationCode)));

		try {

			organization.setOrganizationName(orgReqDto.getOrganizationName());
			organization.setOrganizationCode(orgReqDto.getOrganizationCode());
			organization.setOrganizationDescription(orgReqDto.getOrganizationDescription());

			organization = organizationRepository.save(organization);

			if (organization.getOrganizationCode() != null) {
				organizationResponseDto = modelMapper.map(organization, OrganizationResponseDto.class);
			} else {
				organizationResponseDto = null;
			}

		} catch (Exception e) {
			throw new OrganizationServiceBusinessException(
					String.format(appProperties.getMessages().get(UPDATE_ORGANIZATION_EXCEPTION), e));
		}

		return organizationResponseDto;
	}

	@Override
	public String deleteOrganizationByCode(String organizationCode) {

		String message = null;

		Organization organization = organizationRepository.findByOrganizationCode(organizationCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(ORGANIZATION_NOT_FOUND_BY_CODE_EXCEPTION), organizationCode)));

		try {

			if (organization.getOrganizationCode() != null && organizationCode != null) {
				organizationRepository.deleteById(organization.getId());
				message = appProperties.getMessages().get(ORGANIZATION_DELETION_SUCCEEDED);
			} else {
				message = appProperties.getMessages().get(ORGANIZATION_DELETION_FAILED);
			}

		} catch (Exception e) {
			throw new OrganizationServiceBusinessException(
					String.format(appProperties.getMessages().get(DELETE_ORGANIZATION_EXCEPTION), e));
		}

		return message;
	}

}
