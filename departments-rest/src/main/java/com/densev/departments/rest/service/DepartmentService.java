package com.densev.departments.rest.service;

import java.util.List;

import com.densev.departments.rest.dao.DepartmentDao;
import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.view.DepartmentView;

/**
 * Service interface for dao wrapper, provides a list of methods for interaction with {@link DepartmentDao}
 * @author DENIS SEVOSTEENKO
 */
public interface DepartmentService {

    Department save(Department department);

    boolean remove(Long id);

    Department find(Long id);

    List<Department> findAll();

    List<DepartmentView> findAllViews();
}
