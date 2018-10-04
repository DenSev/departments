package com.densev.departments.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.densev.departments.entity.Department;
import com.densev.departments.entity.view.DepartmentView;

/**
 * Interface for interactions with REST services server and {@link Department} and {@link DepartmentView} objects
 * @author DENIS SEVOSTEENKO
 */
public interface DepartmentService {

    /**
     * Saves a department, creates if id == null and updates otherwise
     * @param department - department to be saved
     */
    void save(Department department);

    /**
     * Deletes department by specified id
     * throws exception if REST service responds with {@link Constants.RESPONSE_CODE_ERROR_DELETION}
     * @param id - of department to be deleted
     * @throws DataIntegrityViolationException - exception thrown if the desired object cannot be deleted
     */
    void remove(Long id) throws DataIntegrityViolationException;
    
    /**
     * retrieves department by specified id
     * @param id - id of desired department
     * @return {@link Department} object
     */
    Department find(Long id);

    /**
     * retrieves all departments as a list of {@link Department} 
     * @return - a list of {@link Department} objects
     */
    List<Department> findAll();
    
    /**
     * retrieves all departments as a list of {@link DepartmentView} objects
     * @return - a list of {@link DepartmentView} objects
     */
    List<DepartmentView> findAllViews();
}
