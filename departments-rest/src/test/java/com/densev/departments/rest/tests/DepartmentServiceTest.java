package com.densev.departments.rest.tests;

import com.densev.departments.rest.dao.DepartmentDao;
import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.view.DepartmentView;
import com.densev.departments.rest.service.DepartmentServiceImpl;
import org.mockito.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class DepartmentServiceTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    /**
     * set up mockito
     */
    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void reset() {
        Mockito.reset(departmentDao);
    }

    /**
     * test saving a new department (id == null), verify departmentDao.create was called and departmentDao.update wasn't
     */
    @Test
    public void testSaveNew() {
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);
        Department department = new Department();
        department.setName("test department");
        departmentService.save(department);
        //checks
        verify(departmentDao, times(1)).create(arg.capture());
        verify(departmentDao, times(0)).update(arg.capture());
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
        verify(departmentDao, times(0)).create(arg.capture());
        verify(departmentDao, times(1)).update(arg.capture());
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
        List<Department> expectedDepartments = new ArrayList<>();
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
        department.setId(1L);
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
