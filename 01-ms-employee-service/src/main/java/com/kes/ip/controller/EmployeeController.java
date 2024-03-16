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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kes.ip.dto.ApiResponseDto;
import com.kes.ip.dto.EmployeeApiResponseDto;
import com.kes.ip.dto.EmployeeRequestDto;
import com.kes.ip.dto.EmployeeResponseDto;
import com.kes.ip.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<EmployeeResponseDto> createEmployee(
			@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
		return new ResponseEntity<>(employeeService.saveEmployee(employeeRequestDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponseDto>> fetchAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto> fetchEmployeeById(@PathVariable("id") Integer employeeId) {
		return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable("id") Integer employeeId,
			@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
		return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employeeRequestDto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeEmployee(@PathVariable("id") Integer employeeId) {
		return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<EmployeeApiResponseDto> fetchAllEmployees(

			@RequestParam(name = "pageNo", defaultValue = "0", required = false) Integer pageNo,
			@RequestParam(name = "pageSize", defaultValue = "3", required = false) Integer pageSize,
			@RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy

	) {
		return ResponseEntity.ok(employeeService.getAllEmployees(pageNo, pageSize, sortBy));
	}
}
