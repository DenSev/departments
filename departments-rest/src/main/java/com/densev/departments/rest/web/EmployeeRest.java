package com.densev.departments.rest.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import com.densev.departments.rest.service.EmployeeService;

/**
 * Rest service for interaction with {@link Employee} and {@link EmployeeView}
 * @author DENIS SEVOSTEENKO
 */
@SuppressWarnings("rawtypes")
@Path("/emp")
@Controller
public class EmployeeRest {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRest.class);
    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves all employees
     * @return {@link ResponseForm} with a list of {@link EmployeeView} objects and {@link Constants} responseCode
     */
    @GET
    @Path("/find/views")
    @Produces("application/json")
    public Response findAll() {
        ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
        try{
            form.setResponseData(employeeService.findAllViews());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * Looks up employees by provided id of a department they should belong to
     * @param id - id of department, employees of which should be retrieved
     * @return {@link ResponseForm} with a list of {@link EmployeeView} objects and {@link Constants} responseCode
     */
    @GET
    @Path("/find/dep/{id}")
    @Produces("application/json")
    public Response findAllByDepartmentId(@PathParam("id") Long id) {
        ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
        try{
            form.setResponseData(employeeService.findAllViewsByDepartmentId(id));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * Retrieves a desired employee by id provided
     * @param id - id of employee to be looked up in db
     * @return {@link ResponseForm} with {@link EmloyeeView} object and {@link Constants} responseCode
     */
    @GET
    @Path("/find/{id}")
    @Produces("application/json")
    public Response find(@PathParam("id") Long id) {
        ResponseForm<Employee> form = new ResponseForm<Employee>();
        try{
            form.setResponseData(employeeService.find(id));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * Saves an employee
     * @param employee - employee to be save, may be either a new one or old one to be updated
     * @return {@link ResponseForm} without date and with only responseCode
     */
    @POST
    @Path("/save")
    @Produces("application/json")
    public Response save(Employee employee) {
        ResponseForm form = new ResponseForm();
        try{
            employeeService.save(employee);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * Searches db for employees falling whithin specified parameters: date of birth must be either equal
     * to one of the dates if the other is null or fall between two if neither are
     * @param searchForm - searchForm query, fields may be null, if one of the fields is null 
     * and other isn't searches by equality comparison rather than interval
     * @return {@link ResponseForm} with list of {@link EmployeeView} results of search query
     */
    @POST
    @Path("/search")
    @Produces("application/json")
    public Response search(SearchForm searchForm) {
        ResponseForm<List<EmployeeView>> form = new ResponseForm<List<EmployeeView>>();
        try{
            //strip local timezone from dates just in case
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            if(searchForm.getStartDate() != null){
                Date startDate = format.parse(format.format(searchForm.getStartDate()));
                searchForm.setStartDate(startDate);
            }
            if(searchForm.getEndDate() != null){
                Date endDate = format.parse(format.format(searchForm.getEndDate()));
                searchForm.setEndDate(endDate);
            }
            form.setResponseData(employeeService.searchByDate(searchForm));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * @param id - id of employee to be deleted
     * @return {@link ResponseForm} without responseData and only responseCode. 
     * Returns {@link Constants.RESPONSE_CODE_ERROR_DELETION} if the desired 
     * object cannot be deleted which can later be used to throw exception or 
     * process the result some other way
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces("application/json")
    public Response remove(@PathParam("id") Long id) {
        ResponseForm form = new ResponseForm();
        try{
            employeeService.remove(id);
        } catch(DataIntegrityViolationException e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR_DELETION);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

}
