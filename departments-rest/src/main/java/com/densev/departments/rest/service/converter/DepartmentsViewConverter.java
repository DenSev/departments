package com.densev.departments.rest.service.converter;

import com.densev.departments.rest.api.dto.DepartmentDTO;
import com.densev.departments.rest.entity.view.DepartmentView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on: 10/08/18
 */
@Component
public class DepartmentsViewConverter implements Converter<DepartmentView, DepartmentDTO> {

    @Override
    public DepartmentDTO convert(DepartmentView source) {
        DepartmentDTO departmentDTO = DepartmentDTO.builder()
            .id(source.getId())
            .name(source.getName())
            .count(source.getCount())
            .salary(source.getSalary())
            .build();
        return departmentDTO;
    }
}
