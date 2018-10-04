package com.densev.departments.rest.dao;

import java.util.List;

import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;

/**
 * @author DENIS SEVOSTEENKO
 */
public interface EmployeeDao extends CRUD<Long, Employee>{
    
    /**
     * provides a list of all employees
     * @return list of all employees
     */
    List<Employee> readAll();
    
    /**
     * provides a list of all employees formatted for view with date of birth as string and department name instead of departmentId
     * @return list of all formatted for view employees
     */
    List<EmployeeView> readAllViews();
    
    /**
     * provides a list of all formatted for view employees for specified department with date of birth as string and department name instead of departmentId
     * @param id - departmentId
     * @return list of all formatted for view employees for specified department
     */
    List<EmployeeView> readAllViewsByDepartmentId(Long id);
    
    /**
     * provides a list of all formatted for view employees selected 
     * by date of birth in interval between start date and end date, 
     * if either of dates is null the search query compares the other 
     * date and date_of_birth by equality
     * @param form - search form with start and end dates
     * @return list of all formatted for view employees
     */
    List<EmployeeView> searchByDate(SearchForm form);
}
