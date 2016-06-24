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

import by.dsev.departments.rest.dao.EmployeeDao;
import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.Employee;
import by.dsev.departments.rest.entity.view.EmployeeView;
import by.dsev.departments.rest.service.EmployeeServiceImpl;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeDao employeeDao;
    
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    /**
    * set up mockito
    */
   @Before
   public void setUp(){
       MockitoAnnotations.initMocks(this);
   }


   /**
    * test saving a new employee (id == null), verify employeeDao.create was called and employeeDao.update wasn't
    */
   @Test
   public void testSaveNew(){
       ArgumentCaptor<Employee> arg = ArgumentCaptor.forClass(Employee.class);
       Employee employee = new Employee();
       employee.setFullName("test full name");
       employeeService.save(employee);
       //checks
       verify(employeeDao, Mockito.times(1)).create(arg.capture());
       verify(employeeDao, Mockito.times(0)).update(arg.capture());
       assertEquals(employee, arg.getValue());
       verifyNoMoreInteractions(employeeDao);
   }

   /**
    * test saving existing employee (id != null), verify employeeDao.update was called and employeeDao.create wasn't
    */
   @Test
   public void testSaveUpdate(){
       ArgumentCaptor<Employee> arg = ArgumentCaptor.forClass(Employee.class);
       Employee employee = new Employee();
       employee.setId(1l);
       employee.setFullName("test full name");
       employeeService.save(employee);
       //checks
       verify(employeeDao, Mockito.times(0)).create(arg.capture());
       verify(employeeDao, Mockito.times(1)).update(arg.capture());
       assertEquals(employee, arg.getValue());
       verifyNoMoreInteractions(employeeDao);
   }
   /**
    * test reading employee
    */
   @Test
   public void testFind(){
       ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
       Employee employee = new Employee();
       employee.setId(1l);
       employee.setFullName("test full name");
       when(employeeDao.read(arg.capture())).thenReturn(employee);
       employeeService.find(1l);
       //checks
       assertEquals(employee.getId(), arg.getValue());
       verify(employeeDao, times(1)).read(1l);
       verifyNoMoreInteractions(employeeDao);
   }
   /**
    * test deleting employee, verify dao method was called from service
    */
   @Test
   public void testRemove(){
       ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
       Employee employee = new Employee();
       employee.setId(1l);
       employee.setFullName("test full name");
       employeeService.remove(1l);
       //checks
       verify(employeeDao, Mockito.times(1)).delete(arg.capture());
       assertEquals(employee.getId(), arg.getValue());
       verifyNoMoreInteractions(employeeDao);
   }
   /**
    * test findAll comparing factual and expected results
    */
   @Test
   public void testFindAll(){
       Employee employee = new Employee();
       employee.setId(1l);
       employee.setFullName("test full name");
       List<Employee> expectedEmployees = new ArrayList<Employee>();
       expectedEmployees.add(employee);
       when(employeeDao.readAll()).thenReturn(expectedEmployees);
       List<Employee> gotEmployees = employeeService.findAll();
       //checks
       assertEquals(expectedEmployees, gotEmployees);
       verify(employeeDao, times(1)).readAll();
       verifyNoMoreInteractions(employeeDao);
   }
   
   /**
    * test findAllViews comparing factual and expected results
    */
   @Test
   public void testFindAllViews(){
       EmployeeView employee = new EmployeeView();
       employee.setId(1l);
       employee.setFullName("test full name");
       List<EmployeeView> expectedEmployees = new ArrayList<EmployeeView>();
       expectedEmployees.add(employee);
       when(employeeDao.readAllViews()).thenReturn(expectedEmployees);
       List<EmployeeView> gotEmployees = employeeService.findAllViews();
       //checks
       assertEquals(expectedEmployees, gotEmployees);
       verify(employeeDao, times(1)).readAllViews();
       verifyNoMoreInteractions(employeeDao);
   }
   
   /**
    * test findAllViewsByDepartmentId comparing factual and expected results and comparing name of department with department in results
    */
   @Test
   public void testFindAllByDepartmentId(){
       Department department = new Department();
       department.setId(2l);
       department.setName("test department");
       EmployeeView employee = new EmployeeView();
       employee.setId(1l);
       employee.setDepartment(department.getName());
       employee.setFullName("test full name");
       List<EmployeeView> expectedEmployees = new ArrayList<EmployeeView>();
       expectedEmployees.add(employee);
       when(employeeDao.readAllViewsByDepartmentId(department.getId())).thenReturn(expectedEmployees);
       List<EmployeeView> gotEmployees = employeeService.findAllViewsByDepartmentId(department.getId());
       //checks
       assertEquals(expectedEmployees, gotEmployees);
       assertEquals(department.getName(), gotEmployees.get(0).getDepartment());
       verify(employeeDao, times(1)).readAllViewsByDepartmentId(department.getId());
       verifyNoMoreInteractions(employeeDao);
   }
}
