package com.densev.departments.rest.web;

import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.view.DepartmentView;
import com.densev.departments.rest.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Rest service for interaction with {@link Department} and {@link DepartmentView}
 *
 * @author DENIS SEVOSTEENKO
 */
@Path("/departments")
@Component
public class DepartmentRest {


    private static final Logger LOG = LoggerFactory.getLogger(DepartmentRest.class);

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentRest(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    /**
     * retrieves all departments
     *
     * @return {@link ResponseForm} with a list of {@link Department} objects and response code
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Department> departments = departmentService.findAll();
        return Response.ok().entity(departments).build();
    }

    /**
     * retrieves all deparments formatted for view
     *
     * @return {@link ResponseForm} with a list of {@link DepartmentView} objects and response code
     */
    @GET
    @Path("/views")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllViews() {
        List<DepartmentView> views = departmentService.findAllViews();
        return Response.ok().entity(views).build();
    }

    /**
     * saves specified department
     *
     * @param department - department to save, either a new one or an updated one
     * @return {@link ResponseForm} without any data and only responseCode
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Department department) {
        department = departmentService.save(department);
        return Response.ok().entity(department).build();
    }

    /**
     * retrieves department by specified id
     *
     * @param id - id of desired department
     * @return {@link ResponseForm} with a {@link Department} object and response code
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        Department department = departmentService.find(id);
        return Response.status(200).entity(department).build();
    }

    /**
     * @param id - id of department to be deleted
     * @return {@link ResponseForm} without responseData and only responseCode.
     * Returns {@link Constants} if the desired
     * object cannot be deleted which can later be used to throw exception or
     * process the result some other way
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Long id) {
        final boolean removed = departmentService.remove(id);
        if (!removed) {
            return Response.notModified().entity("Error removing department").build();
        }
        return Response.ok().entity("removed").build();
    }

}