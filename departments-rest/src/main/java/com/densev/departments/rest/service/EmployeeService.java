package com.densev.departments.rest.service;

import java.util.List;

import com.densev.departments.rest.dao.EmployeeDao;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;

/**
 * Service interface for dao wrapper, provides a list of methods for interaction with {@link EmployeeDao}
 * @author DENIS SEVOSTEENKO
 */
public interface EmployeeService {

    Employee save(Employee employee);
    
    boolean remove(Long id);
    
    Employee find(Long id);
    
    List<Employee> findAll();
    
    List<EmployeeView> findAllViews();
    
    List<EmployeeView> findAllViewsByDepartmentId(Long id);

    List<EmployeeView> searchByDate(SearchForm form);
}
