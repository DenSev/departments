package com.densev.departments.rest.service;

import com.densev.departments.rest.api.dto.DepartmentDTO;
import com.densev.departments.rest.dao.DepartmentDao;
import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.view.DepartmentView;
import com.densev.departments.rest.service.converter.ConversionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DepartmentService}, used for rest services
 *
 * @author DENIS
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;
    private final ConversionService conversionService;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao,
                                 ConversionService conversionService) {
        this.conversionService = conversionService;
        this.departmentDao = departmentDao;
    }

    @Override
    public DepartmentDTO save(Department department) {
        if (department.getId() != 0) {
            department = departmentDao.update(department);
        } else {
            department = departmentDao.create(department);
        }
        return conversionService.convert(department, DepartmentDTO.class);
    }

    @Override
    public boolean remove(Long id) {
        return departmentDao.delete(id);
    }

    @Override
    public DepartmentDTO find(Long id) {
        Department department = departmentDao.read(id);
        return conversionService.convert(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<Department> departments = departmentDao.readAll();
        return (List<DepartmentDTO>) conversionService.convert(departments,
            ConversionConstants.DEPARTMENT_LIST,
            ConversionConstants.DEPARTMENT_DTO_LIST);
    }

    @Override
    public List<DepartmentDTO> findAllViews() {
        List<DepartmentView> departments = departmentDao.readAllViews();
        return (List<DepartmentDTO>) conversionService.convert(departments,
            ConversionConstants.DEPARTMENT_VIEW_LIST,
            ConversionConstants.DEPARTMENT_DTO_LIST);

    }

}
