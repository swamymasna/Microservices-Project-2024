package com.kes.ip.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequestDto {

	@NotEmpty(message = "Employee Name must not be Empty or Null")
	@Size(min = 2, message = "Employee Name should have atleast 2 charecters")
	private String employeeName;

	@NotNull(message = "Employee Salary Should Not be Null")
	private Double employeeSalary;

	@NotEmpty(message = "Employee Address must not be Empty or Null")
	@Size(min = 2, message = "Employee Address should have atleast 2 charecters")
	private String employeeAddress;

	@NotEmpty(message = "Department Code must not be Empty or Null")
	@Size(min = 2, message = "Department Code should have atleast 2 charecters")
	private String departmentCode;
	
	@NotEmpty(message = "Organization Code must not be Empty or Null")
	@Size(min = 2, message = "Organization Code should have atleast 2 charecters")
	private String organizationCode;
}
