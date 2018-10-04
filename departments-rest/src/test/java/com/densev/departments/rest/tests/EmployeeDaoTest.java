package com.densev.departments.rest.tests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.densev.departments.rest.dao.DepartmentDao;
import com.densev.departments.rest.dao.EmployeeDao;
import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeDaoTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoTest.class);
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testMethodChain() throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Date date = fmt.parse("2000-01-01");

        Department d = new Department();
        d.setName("test dep");

        LOG.debug("Calling departmentDao.create(d) to create department for test employee");
        departmentDao.create(d);

        Employee e1 = new Employee();
        e1.setFullName("test");
        e1.setDateOfBirth(date);
        e1.setSalary(1000);
        e1.setDepartmentId(d.getId());

        LOG.debug("Calling employeeDao.create(e1)");
        employeeDao.create(e1);

        LOG.debug("Call employeeDao.read(e1.getId) and assert that result equals e1"); 
        Employee e2 = employeeDao.read(e1.getId());
        assertEquals(e1, e2);

        LOG.debug("Call employeeDao.update(e1) then .read() and assert that result equals e1");
        e1.setFullName("new name");
        employeeDao.update(e1);
        Employee e3 = employeeDao.read(e1.getId());
        assertEquals(e1, e3);

        LOG.debug("Call employeeDao.readAll and assert that result contains e1");
        List<Employee> deps = employeeDao.readAll();
        assertTrue(deps.contains(e1));

        LOG.debug("Create EmployeeView that should represend what e1 view wrapper would be and assert that employeeDao.readAllViews contains it");
        EmployeeView ev = new EmployeeView();
        ev.setId(e1.getId());
        ev.setFullName(e1.getFullName());
        ev.setSalary(e1.getSalary());
        ev.setDepartment(d.getName());
        ev.setDateOfBirth(fmt.format(e1.getDateOfBirth()));

        List<EmployeeView> views = employeeDao.readAllViews();
        assertTrue(views.contains(ev));

        LOG.debug("Call employeeDao.readAllByDepartmentId(d.getId) and assert that it contains e1 view wrapper");
        views = employeeDao.readAllViewsByDepartmentId(d.getId());
        assertTrue(views.contains(ev));

        LOG.debug("Create a search form and call employeeDao.searchByDate(form) and assert that it contains e1 view wrapper");
        SearchForm form = new SearchForm();
        form.setEndDate(date);
        views = employeeDao.searchByDate(form);
        assertTrue(views.contains(ev));

        LOG.debug("Finally, delete e1 and assert that employeeDao.readAll result does not contain it");
        employeeDao.delete(e1.getId());
        deps = employeeDao.readAll();
        assertTrue(!deps.contains(e1));
    }

}
