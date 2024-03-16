package com.kes.ip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kes.ip.dto.DepartmentRequestDto;
import com.kes.ip.dto.DepartmentResponseDto;
import com.kes.ip.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

	private DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentResponseDto> createDepartment(
			@RequestBody DepartmentRequestDto departmentRequestDto) {
		return new ResponseEntity<>(departmentService.saveDepartment(departmentRequestDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<DepartmentResponseDto>> fetchAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponseDto> fetchDepartmentById(@PathVariable("id") Integer departmentId) {
		return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
	}

	@GetMapping("/code/{dept-code}")
	public ResponseEntity<DepartmentResponseDto> fetchDepartmentByCode(
			@PathVariable("dept-code") String departmentCode) {
		return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode), HttpStatus.OK);
	}

	@PutMapping("/{dept-code}")
	public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable("dept-code") String departmentCode,
			@RequestBody DepartmentRequestDto departmentRequestDto) {
		return new ResponseEntity<>(departmentService.updateDepartment(departmentCode, departmentRequestDto),
				HttpStatus.OK);
	}

	@DeleteMapping("/{dept-code}")
	public ResponseEntity<String> removeDepartmentByCode(@PathVariable("dept-code") String departmentCode) {
		return new ResponseEntity<>(departmentService.deleteDepartment(departmentCode), HttpStatus.OK);
	}
}
