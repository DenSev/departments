package com.densev.departments.tests;

/*import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.densev.departments.entity.view.DepartmentView;
import com.densev.departments.entity.view.EmployeeView;
import com.densev.departments.service.DepartmentService;
import com.densev.departments.service.EmployeeService;
import com.densev.departments.web.IndexController;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@WebAppConfiguration*/
public class IndexControllerTest {
/*
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private IndexController controller;

    *//**
    * set up mockito
    *//*
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(departmentService);
        Mockito.reset(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testDepartmentsPage() throws Exception{

        DepartmentView d = new DepartmentView();
        d.setId(1l);
        d.setName("department");
        EmployeeView e = new EmployeeView();
        e.setId(1l);
        e.setFullName("employee");

        List<DepartmentView> deps = new ArrayList<DepartmentView>();
        deps.add(d);
        List<EmployeeView> emps = new ArrayList<EmployeeView>();
        emps.add(e);

        when(departmentService.findAllViews()).thenReturn(deps);
        when(employeeService.findAllViews()).thenReturn(emps);

        mockMvc.perform(get("/rest_template.html"))
                            .andExpect(status().isOk())
                            .andExpect(forwardedUrl(".rest_template"))
                            .andExpect(model().attribute("departments",hasSize(1)))
                            .andExpect(model().attribute("departments", hasItem(
                                    allOf(
                                            hasProperty("id", is(d.getId())),
                                            hasProperty("name", is(d.getName()))
                                    )
                            )))
                            .andExpect(model().attribute("employees", hasSize(1)))
                            .andExpect(model().attribute("employees", hasItem(
                                    allOf(
                                            hasProperty("id", is(e.getId())),
                                            hasProperty("fullName", is(e.getFullName()))
                                    )
                            )));

        verify(departmentService, times(1)).findAllViews();
        verifyNoMoreInteractions(departmentService);
        verify(employeeService, times(1)).findAllViews();
        verifyNoMoreInteractions(employeeService);

    }*/
}
