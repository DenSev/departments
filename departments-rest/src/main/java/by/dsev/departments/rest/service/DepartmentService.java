package by.dsev.departments.rest.service;

import java.util.List;

import by.dsev.departments.rest.dao.DepartmentDao;
import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;

/**
 * Service interface for dao wrapper, provides a list of methods for interaction with {@link DepartmentDao}
 * @author DENIS SEVOSTEENKO
 */
public interface DepartmentService {

    void save(Department department);

    void remove(Long id);

    Department find(Long id);

    List<Department> findAll();

    List<DepartmentView> findAllViews();
}
