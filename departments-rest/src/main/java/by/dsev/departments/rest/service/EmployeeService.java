package by.dsev.departments.rest.service;

import java.util.List;

import by.dsev.departments.rest.dao.EmployeeDao;
import by.dsev.departments.rest.entity.Employee;
import by.dsev.departments.rest.entity.SearchForm;
import by.dsev.departments.rest.entity.view.EmployeeView;
/**
 * Service interface for dao wrapper, provides a list of methods for interaction with {@link EmployeeDao}
 * @author DENIS SEVOSTEENKO
 */
public interface EmployeeService {

    void save(Employee employee);
    
    void remove(Long id);
    
    Employee find(Long id);
    
    List<Employee> findAll();
    
    List<EmployeeView> findAllViews();
    
    List<EmployeeView> findAllViewsByDepartmentId(Long id);

    List<EmployeeView> searchByDate(SearchForm form);
}
