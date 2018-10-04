package by.dsev.departments.tests;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import by.dsev.departments.entity.Department;
import by.dsev.departments.service.DepartmentService;
import by.dsev.departments.web.DepartmentController;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@WebAppConfiguration
public class DepartmentControllerTest {
    
    private MockMvc mockMvc;
    
    @Mock
    private DepartmentService departmentService;
    
    @InjectMocks
    private DepartmentController deaprtmentController;
    
    /**
     * set up mockito
     */
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(departmentService);
        mockMvc = MockMvcBuilders.standaloneSetup(deaprtmentController).build();
    }

    /**
     * test add method, which passes new Department to model and forwards to edit page
     * @throws Exception
     */
    @Test
    public void testNew() throws Exception{

        Department d = new Department();
        mockMvc.perform(get("/department/new.html"))
                            .andExpect(status().isOk())
                            .andExpect(forwardedUrl(".department"))
                            .andExpect(model().attribute("department", hasProperty("id", is(d.getId())) ))
                            .andExpect(model().attribute("department", hasProperty("name", is(d.getName()))));

        verifyZeroInteractions(departmentService);
    }

    /**
     * tests edit method which retrieves desired department and passes it to model, forwarding to edit page after it's done
     * @throws Exception
     */
    @Test
    public void testEdit() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

        Department d = new Department();
        d.setId(1l);
        d.setName("department");
        
        when(departmentService.find(d.getId())).thenReturn(d);
        
        mockMvc.perform(get("/department/edit.html?id=" + d.getId()))
                            .andExpect(status().isOk())
                            .andExpect(forwardedUrl(".department"))
                            .andExpect(model().attribute("department", hasProperty("id", is(1l)) ))
                            .andExpect(model().attribute("department", hasProperty("name", is("department")) ));
        
        verify(departmentService, times(1)).find(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(d.getId(), arg.getValue());
    }

    
    /**
     * test save method which saves department passed as ModelAttribute and redirects to main page of jsp client
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception{
        ArgumentCaptor<Department> arg = ArgumentCaptor.forClass(Department.class);

        Department d = new Department();
        d.setId(1l);
        d.setName("department");

        mockMvc.perform( post("/department/save.html")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", d.getId().toString())
                                .param("name", d.getName())
                                .sessionAttr("department", d))
                            .andExpect(status().isFound())
                            .andExpect(redirectedUrl("/rest_template.html"));

        verify(departmentService, times(1)).save(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(d, arg.getValue());
    }
    
    /**
     * tests delete method, which deletes department by id passed to it and then redirects to main page of jsp client
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Long id = 1l;

        mockMvc.perform(get("/department/delete.html?id=" + id))
                            .andExpect(status().isFound())
                            .andExpect(redirectedUrl("/rest_template.html"));

        verify(departmentService, times(1)).remove(arg.capture());
        verifyNoMoreInteractions(departmentService);
        assertEquals(id, arg.getValue());

    }

}
