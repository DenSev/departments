package com.densev.departments.rest.service.converter;

import com.densev.departments.rest.api.dto.EmployeeDTO;
import com.densev.departments.rest.entity.view.EmployeeView;
import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on: 10/08/18
 */
@Component
public class EmployeeViewConverter implements Converter<EmployeeView, EmployeeDTO> {

    @Override
    public EmployeeDTO convert(EmployeeView source) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
            .id(source.getId())
            .fullName(source.getFullName())
            .dateOfBirth(DateTime.parse(source.getDateOfBirth()))
            .salary(source.getSalary())
            .department(source.getDepartment())
            .build();
        return employeeDTO;
    }
}
