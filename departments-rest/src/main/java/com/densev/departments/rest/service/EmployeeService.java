package com.densev.departments.rest.service;

import java.util.List;

import com.densev.departments.rest.api.dto.EmployeeDTO;
import com.densev.departments.rest.dao.EmployeeDao;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.api.SearchForm;

/**
 * Service interface for dao wrapper, provides a list of methods for interaction with {@link EmployeeDao}
 * @author DENIS SEVOSTEENKO
 */
public interface EmployeeService {

    EmployeeDTO save(Employee employee);
    
    boolean remove(Long id);

    EmployeeDTO find(Long id);
    
    List<EmployeeDTO> findAll();
    
    List<EmployeeDTO> findAllViews();
    
    List<EmployeeDTO> findAllViewsByDepartmentId(Long id);

    List<EmployeeDTO> searchByDate(SearchForm form);
}
