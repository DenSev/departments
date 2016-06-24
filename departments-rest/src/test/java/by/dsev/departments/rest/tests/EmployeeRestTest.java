package by.dsev.departments.rest.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.Employee;
import by.dsev.departments.rest.entity.view.EmployeeView;
import by.dsev.departments.rest.service.EmployeeService;
import by.dsev.departments.rest.web.Constants;
import by.dsev.departments.rest.web.EmployeeRest;


@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class EmployeeRestTest {


        

        private MockMvc mockMvc;


        @Mock
        private EmployeeService employeeService;
        
        @InjectMocks
        private EmployeeRest employeeRest;

        /**
         * set up mockito
         */
        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            Mockito.reset(employeeService);
            mockMvc = MockMvcBuilders.standaloneSetup(employeeRest).build();
        }

        /**
         * 
         * @throws Exception 
         */
        @Test
        public void testFindAllViews() throws Exception{

            EmployeeView emp1 = new EmployeeView();
            emp1.setId(1l);
            emp1.setFullName("test 1");
            emp1.setDob("1999-01-01");
            emp1.setSalary(1000);
            emp1.setDepartment("department 1");
            
            EmployeeView emp2 = new EmployeeView();
            emp2.setId(2l);
            emp2.setFullName("test 2");
            emp2.setDob("1999-02-02");
            emp2.setSalary(2000);
            emp2.setDepartment("department 2");

            when(employeeService.findAllViews()).thenReturn(Arrays.asList(emp1, emp2));

            mockMvc.perform(get("/employees.json"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                    .andExpect(jsonPath("$.responseData", hasSize(2)))
                    .andExpect(jsonPath("$.responseData[0].id", is(1)))
                    .andExpect(jsonPath("$.responseData[0].fullName", is("test 1")))
                    .andExpect(jsonPath("$.responseData[0].dob", is("1999-01-01")))
                    .andExpect(jsonPath("$.responseData[0].salary", is(1000)))
                    .andExpect(jsonPath("$.responseData[0].department", is("department 1")))
                    .andExpect(jsonPath("$.responseData[1].id", is(2)))
                    .andExpect(jsonPath("$.responseData[1].fullName", is("test 2")))
                    .andExpect(jsonPath("$.responseData[1].dob", is("1999-02-02")))
                    .andExpect(jsonPath("$.responseData[1].salary", is(2000)))
                    .andExpect(jsonPath("$.responseData[1].department", is("department 2")));

            verify(employeeService, times(1)).findAllViews();
            verifyNoMoreInteractions(employeeService);
        }

        @Test
        public void testFindAllViewsByDepartmentId() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
            ObjectMapper mapper = new ObjectMapper();
            
            Department dep = new Department();
            dep.setId(1l);
            dep.setName("department 1");
            
            EmployeeView emp1 = new EmployeeView();
            emp1.setId(1l);
            emp1.setFullName("test 1");
            emp1.setDob("1999-01-01");
            emp1.setSalary(1000);
            emp1.setDepartment(dep.getName());
            
            EmployeeView emp2 = new EmployeeView();
            emp2.setId(2l);
            emp2.setFullName("test 2");
            emp2.setDob("1999-02-02");
            emp2.setSalary(2000);
            emp2.setDepartment(dep.getName());

            when(employeeService.findAllViewsByDepartmentId(dep.getId())).thenReturn(Arrays.asList(emp1, emp2));

            mockMvc.perform(post("/employees.json")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(dep.getId())))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                    .andExpect(jsonPath("$.responseData", hasSize(2)))
                    .andExpect(jsonPath("$.responseData[0].id", is(1)))
                    .andExpect(jsonPath("$.responseData[0].fullName", is("test 1")))
                    .andExpect(jsonPath("$.responseData[0].dob", is("1999-01-01")))
                    .andExpect(jsonPath("$.responseData[0].salary", is(1000)))
                    .andExpect(jsonPath("$.responseData[0].department", is("department 1")))
                    .andExpect(jsonPath("$.responseData[1].id", is(2)))
                    .andExpect(jsonPath("$.responseData[1].fullName", is("test 2")))
                    .andExpect(jsonPath("$.responseData[1].dob", is("1999-02-02")))
                    .andExpect(jsonPath("$.responseData[1].salary", is(2000)))
                    .andExpect(jsonPath("$.responseData[1].department", is("department 1")));

            verify(employeeService, times(1)).findAllViewsByDepartmentId(arg.capture());
            verifyNoMoreInteractions(employeeService);
            assertEquals(dep.getId(), arg.getValue());
        }

        @Test
        public void testFind() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

            Date dob = new Date();
            Employee emp = new Employee();
            emp.setId(1l);
            emp.setFullName("test 1");
            emp.setDob(dob);
            emp.setSalary(1000);
            emp.setDepartmentId(1l);

            when(employeeService.find(emp.getId())).thenReturn(emp);

            mockMvc.perform(get("/employee.json?id=" + emp.getId()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                    .andExpect(jsonPath("$.responseData.id", is(1)))
                    .andExpect(jsonPath("$.responseData.fullName", is("test 1")))
                    .andExpect(jsonPath("$.responseData.dob", is(dob.getTime())))
                    .andExpect(jsonPath("$.responseData.departmentId", is(1)))
                    .andExpect(jsonPath("$.responseData.salary", is(1000)));

            verify(employeeService, times(1)).find(arg.capture());
            verifyNoMoreInteractions(employeeService);
            assertEquals(emp.getId(), arg.getValue());

        }
        
        @Test
        public void testSave() throws Exception{
            ArgumentCaptor<Employee> arg = ArgumentCaptor.forClass(Employee.class);
            ObjectMapper mapper = new ObjectMapper();

            Employee emp = new Employee();
            emp.setId(1l);
            emp.setFullName("test 1");
            emp.setDob(new Date());
            emp.setDepartmentId(1l);
            emp.setSalary(1000);

            mockMvc.perform(post("/employee.json")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsBytes(emp)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)));
            
            verify(employeeService, times(1)).save(arg.capture());
            assertEquals(emp, arg.getValue());
        }

        @Test
        public void testDelete() throws Exception{
            ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
            Long id = 1l;
            
            mockMvc.perform(delete("/employee.json?id=" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)));
            
            verify(employeeService, times(1)).remove(arg.capture());
            assertEquals(id, arg.getValue());
        }

}
