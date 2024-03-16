package com.kes.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDto {

	private Integer id;

	private String departmentName;

	private String departmentCode;

	private String departmentDescription;
}
