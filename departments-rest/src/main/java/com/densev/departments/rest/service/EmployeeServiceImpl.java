package com.densev.departments.rest.service;

import com.densev.departments.rest.api.dto.EmployeeDTO;
import com.densev.departments.rest.dao.EmployeeDao;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.api.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import com.densev.departments.rest.service.converter.ConversionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link EmployeeService}, used for rest services
 *
 * @author DENIS SEVOSTEENKO
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDao employeeDao;
    private final ConversionService conversionService;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao,
                               ConversionService conversionService) {
        this.employeeDao = employeeDao;
        this.conversionService = conversionService;
    }

    @Override
    public EmployeeDTO save(Employee employee) {
        if (employee.getId() != 0) {
            employee = employeeDao.update(employee);
        } else {
            employee = employeeDao.create(employee);
        }
        return conversionService.convert(employee, EmployeeDTO.class);
    }

    @Override
    public boolean remove(Long id) {
        return employeeDao.delete(id);
    }

    @Override
    public EmployeeDTO find(Long id) {
        Employee employee = employeeDao.read(id);
        return conversionService.convert(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeDao.readAll();
        return (List<EmployeeDTO>) conversionService.convert(employees,
            ConversionConstants.EMPLOYEE_LIST,
            ConversionConstants.EMPLOYEE_DTO_LIST);
    }

    @Override
    public List<EmployeeDTO> findAllViews() {
        List<EmployeeView> employeeViews = employeeDao.readAllViews();
        return (List<EmployeeDTO>) conversionService.convert(employeeViews,
            ConversionConstants.EMPLOYEE_VIEW_LIST,
            ConversionConstants.EMPLOYEE_DTO_LIST);
    }

    @Override
    public List<EmployeeDTO> findAllViewsByDepartmentId(Long id) {
        List<EmployeeView> employeeViews = employeeDao.readAllViewsByDepartmentId(id);
        return (List<EmployeeDTO>) conversionService.convert(employeeViews,
            ConversionConstants.EMPLOYEE_VIEW_LIST,
            ConversionConstants.EMPLOYEE_DTO_LIST);

    }

    @Override
    public List<EmployeeDTO> searchByDate(SearchForm form) {
        List<EmployeeView> employeeViews = employeeDao.searchByDate(form);
        return (List<EmployeeDTO>) conversionService.convert(employeeViews,
            ConversionConstants.EMPLOYEE_VIEW_LIST,
            ConversionConstants.EMPLOYEE_DTO_LIST);

    }

}
