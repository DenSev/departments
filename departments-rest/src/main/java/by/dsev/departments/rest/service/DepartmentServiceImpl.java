package by.dsev.departments.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.dsev.departments.rest.dao.DepartmentDao;
import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;

/**
 * Implementation of {@link DepartmentService}, used for rest services
 * @author DENIS
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao ;
    
    /*@Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }*/

    @Override
    public void save(Department department) {
        if(department.getId() != 0){
            departmentDao.update(department);
        } else {
            departmentDao.create(department);
        }
    }

    @Override
    public void remove(Long id) {
        departmentDao.delete(id);
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
