package com.kes.ip.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeApiResponseDto {

	private List<EmployeeResponseDto> employeesList;
	private Integer pageNo;
	private Integer pageSize;
	private String sortBy;
	private Long totalElements;
	private Integer totalPages;
	private Boolean first;
	private Boolean last;
}
