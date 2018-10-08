package com.densev.departments.rest.service.converter;

import com.densev.departments.rest.api.dto.EmployeeDTO;
import com.densev.departments.rest.entity.Employee;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on: 10/08/18
 */
@Component
public class EmployeeConverter implements Converter<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO convert(Employee source) {
        EmployeeDTO.Builder employeeDTOBuilder = EmployeeDTO.builder()
            .id(source.getId())
            .dateOfBirth(new DateTime(source.getDateOfBirth()))
            .fullName(source.getFullName())
            .salary(source.getSalary());
        if (source.getDepartment() != null) {
            employeeDTOBuilder
                .department(source.getDepartment().getName());
                //.departmentId(String.valueOf(source.getDepartment().getId()));
        }
        return employeeDTOBuilder.build();
    }
}
