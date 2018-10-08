package com.densev.departments.rest.service.converter;

import com.densev.departments.rest.api.dto.DepartmentDTO;
import com.densev.departments.rest.entity.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on: 10/08/18
 */
@Component
public class DepartmentConverter implements Converter<Department, DepartmentDTO> {

    @Override
    public DepartmentDTO convert(Department source) {
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
            .id(source.getId())
            .name(source.getName())
            .build();
        return departmentDTO;
    }
}
