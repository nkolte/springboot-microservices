package com.nkolte.departmentservice.service.impl;

import com.nkolte.departmentservice.dto.DepartmentDto;
import com.nkolte.departmentservice.entity.Department;
import com.nkolte.departmentservice.repository.DepartmentRepository;
import com.nkolte.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = modelMapper.map(department, DepartmentDto.class);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

        return departmentDto;
    }
}
