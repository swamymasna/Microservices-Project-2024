package com.kes.ip.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrganizationRequestDto {

	@NotEmpty(message = "Organization Name must not be Null or Empty")
	@Size(min = 2, message = "Organization Name should have atleast 2 charecters")
	private String organizationName;

	@NotEmpty(message = "Organization Code must not be Null or Empty")
	@Size(min = 2, message = "Organization Code should have atleast 2 charecters")
	private String organizationCode;

	@NotEmpty(message = "Organization Description must not be Null or Empty")
	@Size(min = 2, message = "Organization Description should have atleast 2 charecters")
	private String organizationDescription;
}
