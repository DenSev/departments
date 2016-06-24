package by.dsev.departments.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
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

import by.dsev.departments.entity.Department;
import by.dsev.departments.entity.view.DepartmentView;
import by.dsev.departments.service.DepartmentServiceImpl;
import by.dsev.departments.web.Constants;
import by.dsev.departments.web.ResponseForm;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceTest {


    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockServer = MockRestServiceServer.createServer(departmentService.getRestTemplate());
    }

    @Test
    public void testFindAllViews() throws Exception{
        //build data
        DepartmentView department = new DepartmentView();
        department.setId(1l);
        department.setName("test department");
        List<DepartmentView> expected = new ArrayList<DepartmentView>();
        expected.add(department);
        ResponseForm<List<DepartmentView>> form = new ResponseForm<List<DepartmentView>>();
        form.setResponseData(expected);

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/departments.json"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        List<DepartmentView> got = departmentService.findAllViews();

        mockServer.verify();
        assertEquals(got, expected);
    }

    @Test
    public void testFindAll() throws Exception{
        //build data
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        List<Department> expected = new ArrayList<Department>();
        expected.add(department);
        ResponseForm<List<Department>> form = new ResponseForm<List<Department>>();
        form.setResponseData(expected);

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/departments_basic.json"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        List<Department> got = departmentService.findAll();

        mockServer.verify();
        assertEquals(got, expected);
    }

    
    @Test
    public void testSave() throws Exception{

        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        ResponseForm form = new ResponseForm();
        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/department.json"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andExpect(MockRestRequestMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockRestRequestMatchers.jsonPath("$.name", is("test department")))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));;

        departmentService.save(department);

        mockServer.verify();
        assertEquals(Constants.RESPONSE_CODE_SUCCESS, form.getResponseCode());

    }
    
    @Test
    public void testFind() throws Exception{
        //build data
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        ResponseForm<Department> form = new ResponseForm<Department>();
        form.setResponseData(department);

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/department.json?id=" + department.getId()))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        Department got = departmentService.find(department.getId());

        mockServer.verify();
        assertEquals(got, department);
    }
    
    @Test
    public void testRemove() throws Exception{
        //build data
        Long id = 1l;
        ResponseForm form = new ResponseForm();

        //prepare mock
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/department.json?id=" + id))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        departmentService.remove(id);

        mockServer.verify();

    }
    
    @Test(expected=DataIntegrityViolationException.class)
    public void testRemoveWithException() throws Exception{
        Long id = 1l;
        ResponseForm form = new ResponseForm();
        form.setResponseCode(Constants.RESPONSE_CODE_ERROR_DELETION);

        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/departments-rest/department.json?id=" + id))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess(mapper.writeValueAsString(form), MediaType.APPLICATION_JSON));

        departmentService.remove(id);

        mockServer.verify();

    }
}
