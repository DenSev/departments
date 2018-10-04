package com.densev.departments.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.densev.departments.entity.Employee;
import com.densev.departments.entity.SearchForm;
import com.densev.departments.entity.view.EmployeeView;

/**
 * Interface for interactions with REST services server and {@link Employee} and {@link EmployeeView} objects
 * @author DENIS SEVOSTEENKO
 */
public interface EmployeeService {

    /**
     * Saves a employee, creates if id == null and updates otherwise
     * @param employee - employee to be saved
     */
    void save(Employee employee);

    /**
     * Deletes employee by specified id
     * throws exception if REST service responds with {@link Constants.RESPONSE_CODE_ERROR_DELETION}
     * @param id - of employee to be deleted
     * @throws DataIntegrityViolationException - exception thrown if the desired object cannot be deleted
     */
    void remove(Long id);

    /**
     * retrieves employee by specified id
     * @param id - id of desired employee
     * @return {@link Employee} object
     */
    Employee find(Long id);

    /**
     * retrieves all employees as a list of {@link EmployeeView} objects
     * @return - a list of {@link EmployeeView} objects
     */
    List<EmployeeView> findAllViews();

    /**
     * Sends a query to REST service that looks up employees falling within specified 
     * parameters: date of birth must be either equal to one of the dates if the other 
     * is null or fall between two if neither are
     * @param form - {@link SearchForm} query, fields may be null, if one of the fields is null 
     * and other isn't searches by equality comparison rather than interval
     * @return list of {@link EmployeeView} results of search query
     */
    List<EmployeeView> searchByDate(SearchForm form);

}
