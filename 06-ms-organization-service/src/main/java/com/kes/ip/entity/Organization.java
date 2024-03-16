package com.kes.ip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ORGANIZATION_TBL")
public class Organization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ORG_NAME")
	private String organizationName;

	@Column(name = "ORG_CODE")
	private String organizationCode;

	@Column(name = "ORG_DESC")
	private String organizationDescription;
}
