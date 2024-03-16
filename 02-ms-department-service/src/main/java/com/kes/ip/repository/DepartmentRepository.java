package com.kes.ip.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kes.ip.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findByDepartmentCode(String departmentCode);
}
