package by.dsev.departments.rest.tests;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
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

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import by.dsev.departments.rest.service.DepartmentService;
import by.dsev.departments.rest.web.Constants;
import by.dsev.departments.rest.web.DepartmentRest;

@ContextConfiguration(locations = "classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DepartmentRestTest {

    

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;
    
    @InjectMocks
    private DepartmentRest deaprtmentRest;
    
    /**
     * set up mockito
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(deaprtmentRest).build();
    }

    /**
     * 
     * @throws Exception 
     */
    @Test
    public void testFindAllViews() throws Exception{

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

        when(departmentService.findAllViews()).thenReturn(Arrays.asList(dv1, dv2));

        mockMvc.perform(get("/departments.json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                .andExpect(jsonPath("$.responseData", hasSize(2)))
                .andExpect(jsonPath("$.responseData[0].id", is(1)))
                .andExpect(jsonPath("$.responseData[0].name", is("test 1")))
                .andExpect(jsonPath("$.responseData[0].count", is(1)))
                .andExpect(jsonPath("$.responseData[0].salary", is(1000.0000)))
                .andExpect(jsonPath("$.responseData[1].id", is(2)))
                .andExpect(jsonPath("$.responseData[1].name", is("test 2")))
                .andExpect(jsonPath("$.responseData[1].count", is(2)))
                .andExpect(jsonPath("$.responseData[1].salary", is(2000.0000)));

        verify(departmentService, times(1)).findAllViews();
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void testFindAll() throws Exception{

        Department dv1 = new Department();
        dv1.setId(1l);
        dv1.setName("test 1");

        Department dv2 = new Department();
        dv2.setId(2l);
        dv2.setName("test 2");

        when(departmentService.findAll()).thenReturn(Arrays.asList(dv1, dv2));

        mockMvc.perform(get("/departments_basic.json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                .andExpect(jsonPath("$.responseData", hasSize(2)))
                .andExpect(jsonPath("$.responseData[0].id", is(1)))
                .andExpect(jsonPath("$.responseData[0].name", is("test 1")))
                .andExpect(jsonPath("$.responseData[1].id", is(2)))
                .andExpect(jsonPath("$.responseData[1].name", is("test 2")));

        verify(departmentService, times(1)).findAll();
        verifyNoMoreInteractions(departmentService);
    }
    
    @Test
    public void testFind() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

        Department d = new Department();
        d.setId(1l);
        d.setName("test 1");

        when(departmentService.find(d.getId())).thenReturn(d);

        mockMvc.perform(get("/department.json?id=" + d.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)))
                .andExpect(jsonPath("$.responseData.id", is(1)))
                .andExpect(jsonPath("$.responseData.name", is("test 1")));

        verify(departmentService, times(1)).find(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(d.getId(), arg.getValue());

    }
    
    @Test
    public void testSave() throws Exception{
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);
        ObjectMapper mapper = new ObjectMapper();
        
        Department department = new Department();
        department.setId(1l);
        department.setName("test 1");

        mockMvc.perform(post("/department.json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(department)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)));

        verify(departmentService, times(1)).save(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(department, arg.getValue());
    }
    
    @Test
    public void testDelete() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Long id = 1l;

        mockMvc.perform(delete("/department.json?id=" + id))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.responseCode", is(Constants.RESPONSE_CODE_SUCCESS)));

        verify(departmentService, times(1)).remove(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(id, arg.getValue());
    }
}
