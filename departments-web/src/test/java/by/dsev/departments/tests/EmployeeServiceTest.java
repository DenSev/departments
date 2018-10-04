package by.dsev.departments.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.dsev.departments.entity.Employee;
import by.dsev.departments.entity.view.EmployeeView;
import by.dsev.departments.service.EmployeeServiceImpl;
import by.dsev.departments.web.Constants;
import by.dsev.departments.web.ResponseForm;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {


    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockServer = MockRestServiceServer.createServer(employeeService.getRestTemplate());
    }

    @Test
    public void testFindAllViews() throws Exception{
        //build data
        EmployeeView employee = new EmployeeView();
        employee.setId(1l);
        employee.setFullName("test employee");
        employee.setDob("1999-01-01");
        employee.setDepartment("dep");
        employee.setSalary(1000);
        List<EmployeeView> expected = new ArrayList<EmployeeView>();
        expected.add(employee);
        ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
        form.setResponseData(expected);

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/emp/find/views"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        List<EmployeeView> got = employeeService.findAllViews();

        mockServer.verify();
        assertEquals(got, expected);
    }

    
    @Test
    public void testSave() throws Exception{

        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFullName("test employee");
        ResponseForm form = new ResponseForm();
        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/emp/save"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andExpect(MockRestRequestMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockRestRequestMatchers.jsonPath("$.fullName", is("test employee")))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));;

        employeeService.save(employee);

        mockServer.verify();
        assertEquals(Constants.RESPONSE_CODE_SUCCESS, form.getResponseCode());

    }
    
    @Test
    public void testFind() throws Exception{
        //build data
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFullName("test employee");
        ResponseForm<Employee> form = new ResponseForm<Employee>();
        form.setResponseData(employee);

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/emp/find/" + employee.getId()))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        Employee got = employeeService.find(employee.getId());

        mockServer.verify();
        assertEquals(got, employee);
    }
    
    @Test
    public void testRemove() throws Exception{
        //build data
        Long id = 1l;
        ResponseForm form = new ResponseForm();


        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/emp/remove/" + id))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        employeeService.remove(id);

        mockServer.verify();

    }
    
    @Test(expected=DataIntegrityViolationException.class)
    public void testRemoveWithException() throws Exception{
        Long id = 1l;
        ResponseForm form = new ResponseForm();
        form.setResponseCode(Constants.RESPONSE_CODE_ERROR_DELETION);
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/emp/remove/" + id))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));
        employeeService.remove(id);
        mockServer.verify();
    }

}
