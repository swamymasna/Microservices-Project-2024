package com.kes.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeResponseDto {

	private Integer id;

	private String employeeName;

	private Double employeeSalary;

	private String employeeAddress;

	private String departmentCode;

	private String organizationCode;
}
