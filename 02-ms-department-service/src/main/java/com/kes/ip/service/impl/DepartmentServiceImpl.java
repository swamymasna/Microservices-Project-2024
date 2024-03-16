package com.kes.ip.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kes.ip.dto.DepartmentRequestDto;
import com.kes.ip.dto.DepartmentResponseDto;
import com.kes.ip.entity.Department;
import com.kes.ip.exception.DepartmentServiceBusinessException;
import com.kes.ip.exception.ResourceNotFoundException;
import com.kes.ip.props.AppProperties;
import com.kes.ip.repository.DepartmentRepository;
import com.kes.ip.service.DepartmentService;

import lombok.AllArgsConstructor;

import static com.kes.ip.utils.AppConstants.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository departmentRepository;

	private ModelMapper modelMapper;

	private AppProperties appProperties;

	@Override
	public DepartmentResponseDto saveDepartment(DepartmentRequestDto departmentReqDto) {

		Department department = null;

		DepartmentResponseDto departmentResponseDto = null;

		try {

			department = modelMapper.map(departmentReqDto, Department.class);

			department = departmentRepository.save(department);

			departmentResponseDto = modelMapper.map(department, DepartmentResponseDto.class);

		} catch (Exception e) {
			throw new DepartmentServiceBusinessException(
					appProperties.getMessages().get(String.format(SAVE_DEPARTMENT_EXCEPTION, e)));
		}

		return departmentResponseDto;
	}

	@Override
	public List<DepartmentResponseDto> getAllDepartments() {

		List<DepartmentResponseDto> listDepartments = null;

		try {

			List<Department> departments = departmentRepository.findAll();

			if (!departments.isEmpty()) {
				listDepartments = departments.stream()
						.map(department -> modelMapper.map(department, DepartmentResponseDto.class))
						.collect(Collectors.toList());
			} else {
				listDepartments = Collections.emptyList();
			}

		} catch (Exception e) {
			throw new DepartmentServiceBusinessException(
					appProperties.getMessages().get(String.format(FETCH_ALL_DEPARTMENTS_EXCEPTION, e)));
		}

		return listDepartments;
	}

	@Override
	public DepartmentResponseDto getDepartmentById(Integer departmentId) {

		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(appProperties.getMessages().get(DEPARTMENT_NOT_FOUND_EXCEPTION), departmentId)));

		return modelMapper.map(department, DepartmentResponseDto.class);
	}

	@Override
	public DepartmentResponseDto getDepartmentByCode(String departmentCode) {

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(DEPARTMENT_NOT_FOUND_BY_CODE_EXCEPTION), departmentCode)));

		return modelMapper.map(department, DepartmentResponseDto.class);
	}

	@Override
	public DepartmentResponseDto updateDepartment(String departmentCode, DepartmentRequestDto departmentReqDto) {

		DepartmentResponseDto departmentResponseDto = null;

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(DEPARTMENT_NOT_FOUND_BY_CODE_EXCEPTION), departmentCode)));

		try {

			department.setDepartmentName(departmentReqDto.getDepartmentName());
			department.setDepartmentCode(departmentReqDto.getDepartmentCode());
			department.setDepartmentDescription(departmentReqDto.getDepartmentDescription());

			department = departmentRepository.save(department);

			if (department.getDepartmentCode() != null) {
				departmentResponseDto = modelMapper.map(department, DepartmentResponseDto.class);
			} else {
				departmentResponseDto = null;
			}

		} catch (Exception e) {
			throw new DepartmentServiceBusinessException(
					appProperties.getMessages().get(String.format(UPDATE_DEPARTMENT_EXCEPTION, e)));

		}

		return departmentResponseDto;
	}

	@Override
	public String deleteDepartment(String departmentCode) {

		String message = null;

		Department department = departmentRepository.findByDepartmentCode(departmentCode)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(
						appProperties.getMessages().get(DEPARTMENT_NOT_FOUND_BY_CODE_EXCEPTION), departmentCode)));

		try {

			if (department.getDepartmentCode() != null) {
				departmentRepository.deleteById(department.getId());

				message = appProperties.getMessages().get(DEPARTMENT_DELETION_SUCCEEDED);

			} else {
				message = appProperties.getMessages().get(DEPARTMENT_DELETION_FAILED);
			}

		} catch (Exception e) {
			throw new DepartmentServiceBusinessException(
					appProperties.getMessages().get(String.format(DELETE_DEPARTMENT_EXCEPTION, e)));
		}

		return message;
	}

}
