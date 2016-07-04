package by.dsev.departments.rest.tests;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import by.dsev.departments.rest.service.DepartmentService;
import by.dsev.departments.rest.service.DepartmentServiceImpl;
import by.dsev.departments.rest.web.DepartmentRest;
import by.dsev.departments.rest.web.ResponseForm;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DepartmentRestTest {


    @Mock
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @InjectMocks
    private DepartmentRest departmentRest;

    private Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * set up mockito and other stuff
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(departmentService);
        dispatcher.getRegistry().addSingletonResource(departmentRest);
    }

    @Test 
    public void testFindAll() throws Exception{

        MockHttpRequest request = MockHttpRequest.get("/dep/find/all");
        MockHttpResponse response = new MockHttpResponse();
        //prepare mocks
        Department department = new Department();
        department.setId(1l);
        department.setName("test department");
        List<Department> expectedDepartments = new ArrayList<Department>();
        expectedDepartments.add(department);
        when(departmentService.findAll()).thenReturn(expectedDepartments);
        ResponseForm<List<Department>> form = new ResponseForm<List<Department>>();
        form.setResponseData(expectedDepartments);

        //call method to be tested
        dispatcher.invoke(request, response);

        //checks
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(departmentService, times(1)).findAll();
        verifyNoMoreInteractions(departmentService);
        assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

    }

    @Test
    public void testFindAllViews() throws Exception{

        MockHttpRequest request = MockHttpRequest.get("/dep/find/views");
        MockHttpResponse response = new MockHttpResponse();

        
        DepartmentView dv1 = new DepartmentView();
        dv1.setId(1l);
        dv1.setName("test 1");
        dv1.setCount(1);
        dv1.setSalary(1000.0000);

        DepartmentView dv2 = new DepartmentView();
        dv2.setId(2l);
        dv2.setName("test 2");
        dv2.setCount(2);
        dv2.setSalary(2000.0000);

        List<DepartmentView> expected = new ArrayList<DepartmentView>();
        
        when(departmentService.findAllViews()).thenReturn(expected);
        ResponseForm<List<DepartmentView>> form = new ResponseForm<List<DepartmentView>>();
        form.setResponseData(expected);

        //call method to be tested
        dispatcher.invoke(request, response);

        //checks
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(departmentService, times(1)).findAllViews();
        verifyNoMoreInteractions(departmentService);
        assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

    }

    @Test
    public void testFind() throws Exception{
        Department d = new Department();
        d.setId(1l);
        d.setName("test 1");

        MockHttpRequest request = MockHttpRequest.get("/dep/find/" + d.getId());
        MockHttpResponse response = new MockHttpResponse();

        when(departmentService.find(d.getId())).thenReturn(d);
        ResponseForm<Department> form = new ResponseForm<Department>();
        form.setResponseData(d);

        //call method to be tested
        dispatcher.invoke(request, response);

        //checks
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(departmentService, times(1)).find(d.getId());
        verifyNoMoreInteractions(departmentService);
        assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

    }
    
    @Test
    public void testSave() throws Exception{
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);
        
        Department department = new Department();
        department.setId(1l);
        department.setName("test 1");
        ResponseForm form = new ResponseForm();
        
        MockHttpRequest request = MockHttpRequest.post("/dep/save");
        MockHttpResponse response = new MockHttpResponse();
        request.content(mapper.writeValueAsString(department).getBytes());
        request.contentType(MediaType.APPLICATION_JSON);
        
        //call method to be tested
        dispatcher.invoke(request, response);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(departmentService, times(1)).save(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(department, arg.getValue());
        assertEquals(mapper.writeValueAsString(form), response.getContentAsString());
    }
    
    @Test
    public void testDelete() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Long id = 1l;
        ResponseForm form = new ResponseForm();

        MockHttpRequest request = MockHttpRequest.delete("/dep/remove/" + id);
        MockHttpResponse response = new MockHttpResponse();
        //call method to be tested
        dispatcher.invoke(request, response);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        verify(departmentService, times(1)).remove(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(id, arg.getValue());
        assertEquals(mapper.writeValueAsString(form), response.getContentAsString());

    }
}
