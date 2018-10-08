package com.densev.departments.tests;
/*

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.densev.departments.entity.Employee;
import com.densev.departments.entity.SearchForm;
import com.densev.departments.entity.view.EmployeeView;
import com.densev.departments.service.EmployeeService;
import com.densev.departments.web.EmployeeController;
*/

public class EmployeeControllerTest {
/*
    private MockMvc mockMvc;
    
    @Mock
    private EmployeeService employeeService;
    
    @InjectMocks
    private EmployeeController deaprtmentController;
    
    *//**
     * set up mockito
     *//*
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        Mockito.reset(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(deaprtmentController).build();
    }

    *//**
     * test add method, which passes new Employee to model and forwards to edit page
     * @throws Exception
     *//*
    @Test
    public void testNew() throws Exception{

        Employee e = new Employee();
        mockMvc.perform(get("/employee/new.html"))
                            .andExpect(status().isOk())
                            .andExpect(forwardedUrl(".employee"))
                            .andExpect(model().attribute("employee", hasProperty("id", is(e.getId())) ))
                            .andExpect(model().attribute("employee", hasProperty("fullName", is(e.getFullName()))));

        verifyZeroInteractions(employeeService);
    }

    *//**
     * tests edit method which retrieves desired employee and passes it to model, forwarding to edit page after it's done
     * @throws Exception
     *//*
    @Test
    public void testEdit() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);

        Employee e = new Employee();
        e.setId(1l);
        e.setFullName("employee");
        
        when(employeeService.find(e.getId())).thenReturn(e);
        
        mockMvc.perform(get("/employee/edit.html?id=" + e.getId()))
                            .andExpect(status().isOk())
                            .andExpect(forwardedUrl(".employee"))
                            .andExpect(model().attribute("employee", hasProperty("id", is(1l)) ))
                            .andExpect(model().attribute("employee", hasProperty("fullName", is("employee")) ));
        
        verify(employeeService, times(1)).find(arg.capture());
        verifyNoMoreInteractions(employeeService);
        assertEquals(e.getId(), arg.getValue());
    }

    
    *//**
     * test save method which saves employee passed as ModelAttribute and redirects to main page of jsp client
     * @throws Exception
     *//*
    @Test
    public void testSave() throws Exception{
        ArgumentCaptor<Employee> arg = ArgumentCaptor.forClass(Employee.class);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse("1999-01-01");
        
        Employee e = new Employee();
        e.setId(1l);
        e.setFullName("employee");
        e.setDepartmentId(1l);
        e.setDob(date);
        e.setSalary(1000);

        mockMvc.perform( post("/employee/save.html")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", e.getId().toString())
                                .param("fullName", e.getFullName())
                                .param("salary", e.getSalary().toString())
                                .param("departmentId", e.getDepartmentId().toString())
                                .param("dob", fmt.format(e.getDob()))
                                .sessionAttr("employee", new Employee()))
                            .andExpect(status().isFound())
                            .andExpect(redirectedUrl("/rest_template.html"));

        verify(employeeService, times(1)).save(arg.capture());
        verifyNoMoreInteractions(employeeService);
        assertEquals(e, arg.getValue());
    }
    
    @Test
    public void testSearch() throws Exception{
        ArgumentCaptor<SearchForm> arg = ArgumentCaptor.forClass(SearchForm.class);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse("1999-01-01");
        Date date2 = fmt.parse("2000-01-01");
        
        SearchForm form = new SearchForm();
        form.setStartDate(date1);
        form.setEndDate(date2);
        
        EmployeeView e1 = new EmployeeView();
        e1.setId(1l);
        e1.setFullName("employee 2");
        e1.setDepartment("dep");
        e1.setDob(fmt.format(date1));
        e1.setSalary(1000);
        EmployeeView e2 = new EmployeeView();
        e2.setId(2l);
        e2.setFullName("employee 2");
        e2.setDepartment("dep");
        e2.setDob(fmt.format(date2));
        e2.setSalary(2000);
        List<EmployeeView> views = new ArrayList<EmployeeView>();
        views.add(e1);
        views.add(e2);

        when(employeeService.searchByDate(arg.capture())).thenReturn(views);
        
        mockMvc.perform( post("/employee/search.html")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("startDate", fmt.format(date1))
                .param("endDate", fmt.format(date2))
                .sessionAttr("searchForm", new SearchForm()))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl(".search-results"))
            .andExpect(model().attribute("searchResults",hasSize(2)))
            .andExpect(model().attribute("searchResults", hasItem(
                    allOf(
                            hasProperty("id", is(e1.getId())),
                            hasProperty("fullName", is(e1.getFullName())),
                            hasProperty("department", is(e1.getDepartment())),
                            hasProperty("salary", is(e1.getSalary())),
                            hasProperty("dob", is(e1.getDob()))
                    )
            )))
            .andExpect(model().attribute("searchResults", hasItem(
                    allOf(
                            hasProperty("id", is(e2.getId())),
                            hasProperty("fullName", is(e2.getFullName())),
                            hasProperty("department", is(e2.getDepartment())),
                            hasProperty("salary", is(e2.getSalary())),
                            hasProperty("dob", is(e2.getDob()))
                    )
            )));

        verify(employeeService, times(1)).searchByDate(arg.getValue());
        verifyNoMoreInteractions(employeeService);
        assertEquals(form, arg.getValue());

    }
    
    *//**
     * tests delete method, which deletes employee by id passed to it and then redirects to main page of jsp client
     * @throws Exception
     *//*
    @Test
    public void testDelete() throws Exception{
        ArgumentCaptor<Long> arg = ArgumentCaptor.forClass(Long.class);
        Long id = 1l;

        mockMvc.perform(get("/employee/delete.html?id=" + id))
                            .andExpect(status().isFound())
                            .andExpect(redirectedUrl("/rest_template.html"));

        verify(employeeService, times(1)).remove(arg.capture());
        verifyNoMoreInteractions(employeeService);
        assertEquals(id, arg.getValue());

    }*/

}
