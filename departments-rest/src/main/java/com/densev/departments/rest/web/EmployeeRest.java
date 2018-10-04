package com.densev.departments.rest.web;

import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import com.densev.departments.rest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Rest service for interaction with {@link Employee} and {@link EmployeeView}
 *
 * @author DENIS SEVOSTEENKO
 */
@Path("/api/departments")
@Controller
public class EmployeeRest {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeRest.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GET
    @Path("/employees/views")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<EmployeeView> employeeViews = employeeService.findAllViews();
        return Response.ok().entity(employeeViews).build();
    }

    @GET
    @Path("/{id}/employees/views")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByDepartmentId(@PathParam("id") Long id) {
        List<EmployeeView> employeeViews = employeeService.findAllViewsByDepartmentId(id);
        return Response.ok().entity(employeeViews).build();
    }

    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        Employee employee = employeeService.find(id);
        return Response.ok().entity(employee).build();
    }

    @POST
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        employee = employeeService.save(employee);
        return Response.ok().entity(employee).build();
    }

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) {
        try {
            SearchForm searchForm = new SearchForm();

            //strip local timezone from dates just in case
            if (startDate != null) {
                Date sDate = DATE_FORMAT.parse(startDate);
                searchForm.setStartDate(sDate);
            }
            if (endDate != null) {
                Date eDate = DATE_FORMAT.parse(endDate);
                searchForm.setEndDate(eDate);
            }

            List<EmployeeView> employeeViews = employeeService.searchByDate(searchForm);
            return Response.status(200).entity(employeeViews).build();
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @DELETE
    @Path("/employees/{id}")
    @Produces("application/json")
    public Response remove(@PathParam("id") Long id) {
        final boolean removed = employeeService.remove(id);
        if (!removed) {
            return Response.notModified().entity("Error removing").build();
        }
        return Response.ok().entity("Success").build();
    }

}
