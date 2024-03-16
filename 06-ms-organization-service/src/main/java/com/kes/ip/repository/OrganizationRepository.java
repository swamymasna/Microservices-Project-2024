package com.kes.ip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kes.ip.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

	Optional<Organization> findByOrganizationCode(String orgCode);
}
