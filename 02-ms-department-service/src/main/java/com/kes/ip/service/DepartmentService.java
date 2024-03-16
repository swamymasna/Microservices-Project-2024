package com.kes.ip.service;

import java.util.List;

import com.kes.ip.dto.DepartmentRequestDto;
import com.kes.ip.dto.DepartmentResponseDto;

public interface DepartmentService {

	DepartmentResponseDto saveDepartment(DepartmentRequestDto departmentReqDto);

	List<DepartmentResponseDto> getAllDepartments();

	DepartmentResponseDto getDepartmentById(Integer departmentId);

	DepartmentResponseDto getDepartmentByCode(String departmentCode);

	DepartmentResponseDto updateDepartment(String departmentCode, DepartmentRequestDto departmentReqDto);

	String deleteDepartment(String departmentCode);
}
