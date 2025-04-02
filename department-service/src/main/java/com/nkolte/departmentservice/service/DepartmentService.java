package com.nkolte.departmentservice.service;

import com.nkolte.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);
}
