package by.dsev.departments.rest.web;

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

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import by.dsev.departments.rest.service.DepartmentService;


/**
 * Rest service for interaction with {@link Department} and {@link DepartmentView}
 * @author DENIS SEVOSTEENKO
 *
 */
@SuppressWarnings("rawtypes")
@Path("/dep")
@Controller
public class DepartmentRest {


    private static final Logger LOG = LoggerFactory.getLogger(DepartmentRest.class);
    @Autowired
    private DepartmentService departmentService;

    /**
     * retrieves all departments 
     * @return {@link ResponseForm} with a list of {@link Department} objects and response code
     */
    @GET
    @Path("/find/all")
    @Produces("application/json")
    public Response findAll() {
        ResponseForm<List<Department>> form = new ResponseForm<List<Department>>();
        try{
            form.setResponseData(departmentService.findAll());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }
    
    /**
     * retrieves all deparments formatted for view
     * @return {@link ResponseForm} with a list of {@link DepartmentView} objects and response code
     */
    @GET
    @Path("/find/views")
    @Produces("application/json")
    public Response findAllViews() {
        ResponseForm<List<DepartmentView>> form = new ResponseForm<List<DepartmentView>>();
        try{
            form.setResponseData(departmentService.findAllViews());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * saves specified department
     * @param department - department to save, either a new one or an updated one
     * @return {@link ResponseForm} without any data and only responseCode
     */
    @POST
    @Path("/save")
    @Produces("application/json")
    public Response save(Department department) {
        ResponseForm form = new ResponseForm();
        try{
            departmentService.save(department);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }
    
    /**
     * retrieves department by specified id
     * @param id - id of desired department
     * @return {@link ResponseForm} with a {@link Department} object and response code
     */
    @GET
    @Path("/find/{id}")
    @Produces("application/json")
    public Response find(@PathParam("id") Long id) {
        ResponseForm<Department> form = new ResponseForm<Department>();
        try{
            form.setResponseData(departmentService.find(id));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

    /**
     * @param id - id of department to be deleted
     * @return {@link ResponseForm} without responseData and only responseCode. 
     * Returns {@link Constants.RESPONSE_CODE_ERROR_DELETION} if the desired 
     * object cannot be deleted which can later be used to throw exception or 
     * process the result some other way
     */
    @DELETE
    @Path("/remove/{id}")
    @Produces("application/json")
    public Response remove(@PathParam("id") Long id) {
        ResponseForm<Department> form = new ResponseForm<Department>();
        try{
            departmentService.remove(id);
        } catch(DataIntegrityViolationException e ) {
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR_DELETION);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return Response.status(200).entity(form).build();
    }

}
