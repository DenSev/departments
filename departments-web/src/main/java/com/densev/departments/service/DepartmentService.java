package com.densev.departments.service;

import com.densev.departments.rest.api.dto.DepartmentDTO;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * Interface for interactions with REST services server and {@link DepartmentDTO} objects
 *
 * @author DENIS SEVOSTEENKO
 */
public interface DepartmentService {

    /**
     * Saves a department, creates if id == null and updates otherwise
     *
     * @param department - department to be saved
     */
    void save(DepartmentDTO department);

    /**
     * Deletes department by specified id
     * throws exception if REST service responds with
     *
     * @param id - of department to be deleted
     * @throws DataIntegrityViolationException - exception thrown if the desired object cannot be deleted
     */
    void remove(Long id) throws DataIntegrityViolationException;

    /**
     * retrieves department by specified id
     *
     * @param id - id of desired department
     * @return {@link DepartmentDTO} object
     */
    DepartmentDTO find(Long id);

    /**
     * retrieves all departments as a list of {@link DepartmentDTO}
     *
     * @return - a list of {@link DepartmentDTO} objects
     */
    List<DepartmentDTO> findAll();

    /**
     * retrieves all departments as a list of {@link DepartmentDTO} objects
     *
     * @return - a list of {@link DepartmentDTO} objects
     */
    List<DepartmentDTO> findAllViews();
}
