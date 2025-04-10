package com.nkolte.employeeservice.service.impl;

import com.nkolte.employeeservice.dto.APIResponseDto;
import com.nkolte.employeeservice.dto.DepartmentDto;
import com.nkolte.employeeservice.dto.EmployeeDto;
import com.nkolte.employeeservice.dto.OrganizationDto;
import com.nkolte.employeeservice.entity.Employee;
import com.nkolte.employeeservice.repository.EmployeeRepository;
import com.nkolte.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
@Slf4j
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

        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    //   @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto findEmployeeById(Long id) {
        log.info("Inside findEmployeeById method.");
        log.info(" ");
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

        OrganizationDto organizationDto = webClient.get()
                .uri("http://127.0.0.1:8082/api/organizations/"+ employeeDto.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        // DepartmentDto departmentDto = apiClient.getDepartmentById(employeeDto.getDepartmentCode());

        return new APIResponseDto(employeeDto,departmentDto,organizationDto);
    }

    public APIResponseDto getDefaultDepartment(Long id, Throwable t) {
        log.info("Inside fallback method.");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String
                        .format("Employee with id:%s not found!",id)));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        DepartmentDto departmentDto = new DepartmentDto(0L,"fallback","This response is from fallback method as the 'getDepartment service is down'",
                "FALL_BACK00");
        OrganizationDto organizationDto = webClient.get()
                .uri("http://127.0.0.1:8082/api/organizations/"+ employeeDto.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        return new APIResponseDto(employeeDto,departmentDto,organizationDto);
    }
}
