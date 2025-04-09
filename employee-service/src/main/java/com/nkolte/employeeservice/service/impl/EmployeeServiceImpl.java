package com.nkolte.employeeservice.service.impl;

import com.nkolte.employeeservice.dto.APIResponseDto;
import com.nkolte.employeeservice.dto.DepartmentDto;
import com.nkolte.employeeservice.dto.EmployeeDto;
import com.nkolte.employeeservice.entity.Employee;
import com.nkolte.employeeservice.repository.EmployeeRepository;
import com.nkolte.employeeservice.service.EmployeeService;
import com.nkolte.employeeservice.service.client.APIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    //private RestTemplate restTemplate;
    private WebClient webClient;
   // private APIClient apiClient;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        return savedEmployeeDto;
    }

   @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String
                        .format("Employee with id:%s not found!",id)));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        /*ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.getForEntity(
                "http://localhost:8080/api/departments/"+ employeeDto.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();*/

        DepartmentDto departmentDto  = webClient.get()
                .uri("http://localhost:8080/api/departments/"+ employeeDto.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

       // DepartmentDto departmentDto = apiClient.getDepartmentById(employeeDto.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto,departmentDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id, Throwable t) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String
                        .format("Employee with id:%s not found!",id)));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        DepartmentDto departmentDto = new DepartmentDto(0L,"fallback","This response is from fallback method as the 'getDepartment service is down'",
                "FALL_BACK00");

        APIResponseDto apiResponseDto = new APIResponseDto(employeeDto,departmentDto);

        return apiResponseDto;
    }
}
