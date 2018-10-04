package by.dsev.departments.rest.service;

import by.dsev.departments.rest.dao.DepartmentDao;
import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link DepartmentService}, used for rest services
 *
 * @author DENIS
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department save(Department department) {
        if (department.getId() != 0) {
            return departmentDao.update(department);
        } else {
            return departmentDao.create(department);
        }
    }

    @Override
    public boolean remove(Long id) {
        return departmentDao.delete(id);
    }

    @Override
    public Department find(Long id) {
        return departmentDao.read(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.readAll();
    }

    @Override
    public List<DepartmentView> findAllViews() {
        return departmentDao.readAllViews();
    }

}
