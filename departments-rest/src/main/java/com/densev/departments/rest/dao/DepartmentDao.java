package com.densev.departments.rest.dao;

import java.util.List;

import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.view.DepartmentView;

/**
 * @author DENIS SEVOSTEENKO
 */
public interface DepartmentDao extends CRUD<Long, Department>{

    /**
     * provides a list of all departments, mainly for select options
     * @return list of all departments
     */
    List<Department> readAll();

    /**
     * provides a list of formatted for view departments, with employee count and average salary
     * @return list of formatted for view departments
     */
    List<DepartmentView> readAllViews();

}
