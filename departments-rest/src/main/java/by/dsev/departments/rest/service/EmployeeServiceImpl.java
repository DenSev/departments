package by.dsev.departments.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dsev.departments.rest.dao.EmployeeDao;
import by.dsev.departments.rest.entity.Employee;
import by.dsev.departments.rest.entity.SearchForm;
import by.dsev.departments.rest.entity.view.EmployeeView;

/**
 * Implementation of {@link EmployeeService}, used for rest services
 * @author DENIS SEVOSTEENKO
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;
    
    @Override
    public void save(Employee employee) {
        if(employee.getId() != null) {
            employeeDao.update(employee);
        } else {
            employeeDao.create(employee);
        }
    }

    @Override
    public void remove(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee find(Long id) {
        return employeeDao.read(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.readAll();
    }

    @Override
    public List<EmployeeView> findAllViews() {
        return employeeDao.readAllViews();
    }

    @Override
    public List<EmployeeView> findAllViewsByDepartmentId(Long id) {
        return employeeDao.readAllViewsByDepartmentId(id);
    }

    @Override
    public List<EmployeeView> searchByDate(SearchForm form) {
        return employeeDao.searchByDate(form);
    }

}
