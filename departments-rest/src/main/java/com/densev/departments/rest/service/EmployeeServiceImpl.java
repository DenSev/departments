package com.densev.departments.rest.service;

import com.densev.departments.rest.dao.EmployeeDao;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link EmployeeService}, used for rest services
 *
 * @author DENIS SEVOSTEENKO
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() != 0) {
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
