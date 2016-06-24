package by.dsev.departments.rest.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import by.dsev.departments.rest.dao.DepartmentDao;
import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import by.dsev.departments.rest.service.DepartmentServiceImpl;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    /**
     * set up mockito
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    /**
     * test saving a new department (id == null), verify departmentDao.create was called and departmentDao.update wasn't
     */
    @Test
    public void testSaveNew(){
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);
        Department department = new Department();
        department.setName("test department");
        departmentService.save(department);
        //checks
        verify(departmentDao, Mockito.times(1)).create(arg.capture());
        verify(departmentDao, Mockito.times(0)).update(arg.capture());
        assertEquals(department, arg.getValue());
        verifyNoMoreInteractions(departmentDao);
    }
    
    /**
     * test saving existing department (id != null), verify departmentDao.update was called and departmentDao.create wasn't
     */
    @Test
    public void testSaveUpdate(){
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        departmentService.save(department);
        //checks
        verify(departmentDao, Mockito.times(0)).create(arg.capture());
        verify(departmentDao, Mockito.times(1)).update(arg.capture());
        assertEquals(department, arg.getValue());
        verifyNoMoreInteractions(departmentDao);
    }

    /**
     * test reading department
     */
    @Test
    public void testFind(){
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        when(departmentDao.read(arg.capture())).thenReturn(department);
        departmentService.find(1l);
        //checks
        assertEquals(department.getId(), arg.getValue());
        verify(departmentDao, times(1)).read(1l);
        verifyNoMoreInteractions(departmentDao);
    }

    /**
     * test deleting department, verify dao method was called from service
     */
    @Test
    public void testRemove(){
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        departmentService.remove(1l);
        //checks
        verify(departmentDao, Mockito.times(1)).delete(arg.capture());
        assertEquals(department.getId(), arg.getValue());
        verifyNoMoreInteractions(departmentDao);
    }

    /**
     * test findAll comparing factual and expected results
     */
    @Test
    public void testFindAll(){
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        List<Department> expectedDepartments = new ArrayList<Department>();
        expectedDepartments.add(department);
        when(departmentDao.readAll()).thenReturn(expectedDepartments);
        List<Department> gotDepartments = departmentService.findAll();
        //checks
        assertEquals(expectedDepartments, gotDepartments);
        verify(departmentDao, times(1)).readAll();
        verifyNoMoreInteractions(departmentDao);
    }
    
    /**
     * test findAllViews comparing factual and expected results
     */
    @Test
    public void testFindAllViews(){
        DepartmentView department = new DepartmentView();
        department.setId(1l);
        department.setName("test department");
        List<DepartmentView> expectedDepartments = new ArrayList<DepartmentView>();
        expectedDepartments.add(department);
        when(departmentDao.readAllViews()).thenReturn(expectedDepartments);
        List<DepartmentView> gotDepartments = departmentService.findAllViews();
        //checks
        assertEquals(expectedDepartments, gotDepartments);
        verify(departmentDao, times(1)).readAllViews();
        verifyNoMoreInteractions(departmentDao);
    }
}
