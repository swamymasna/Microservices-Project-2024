package com.kes.ip.dto;

import lombok.Data;

@Data
public class ApiResponseDto {

	private EmployeeResponseDto employeeResponseDto;

	private DepartmentResponseDto departmentResponseDto;

	private OrganizationResponseDto organizationResponseDto;
}
