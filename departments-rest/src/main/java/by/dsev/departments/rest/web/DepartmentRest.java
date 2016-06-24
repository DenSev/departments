package by.dsev.departments.rest.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import by.dsev.departments.rest.service.DepartmentService;


/**
 * Rest service for interaction with {@link Department} and {@link DepartmentView}
 * @author DENIS SEVOSTEENKO
 *
 */
@SuppressWarnings("rawtypes")
@Controller
public class DepartmentRest {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentRest.class);
    @Autowired
    private DepartmentService departmentService;

    /**
     * retrieves all deparments formatted for view
     * @return {@link ResponseForm} with a list of {@link DepartmentView} objects and response code
     */
    @ResponseBody
    @RequestMapping(value = "/departments.json", method = RequestMethod.GET, produces="application/json")
    public ResponseForm<List<DepartmentView>> findAllViews() {
        ResponseForm<List<DepartmentView>> form = new ResponseForm<List<DepartmentView>>();
        try{
            form.setResponseData(departmentService.findAllViews());
        } catch(Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return form;
    }

    /**
     * retrieves all departments 
     * @return {@link ResponseForm} with a list of {@link Department} objects and response code
     */
    @ResponseBody
    @RequestMapping(value = "/departments_basic.json", method = RequestMethod.GET, produces="application/json")
    public ResponseForm<List<Department>> findAll() {
        ResponseForm<List<Department>> form = new ResponseForm<List<Department>>();
        try{
            form.setResponseData(departmentService.findAll());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return form;
    }

    /**
     * retrieves department by specified id
     * @param id - id of desired department
     * @return {@link ResponseForm} with a {@link Department} object and response code
     */
    @ResponseBody
    @RequestMapping(value = "/department.json", method = RequestMethod.GET, produces="application/json")
    public ResponseForm<Department> find(@RequestParam Long id) {
        ResponseForm<Department> form = new ResponseForm<Department>();
        try{
            form.setResponseData(departmentService.find(id));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return form;
    }

    /**
     * saves specified department
     * @param department - department to save, either a new one or an updated one
     * @return {@link ResponseForm} without any data and only responseCode
     */
    @ResponseBody
    @RequestMapping(value = "/department.json", method = RequestMethod.POST, produces="application/json")
    public ResponseForm save(@RequestBody Department department) {
        ResponseForm form = new ResponseForm();
        try{
            departmentService.save(department);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return form;
    }

    /**
     * @param id - id of department to be deleted
     * @return {@link ResponseForm} without responseData and only responseCode. 
     * Returns {@link Constants.RESPONSE_CODE_ERROR_DELETION} if the desired 
     * object cannot be deleted which can later be used to throw exception or 
     * process the result some other way
     */
    @ResponseBody
    @RequestMapping(value = "/department.json", method = RequestMethod.DELETE, produces="application/json")
    public ResponseForm remove(@RequestParam Long id) {
        ResponseForm form = new ResponseForm();
        try{
            departmentService.remove(id);
        } catch(DataIntegrityViolationException e ) {
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR_DELETION);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
            form.setResponseCode(Constants.RESPONSE_CODE_ERROR);
        }
        return form;
    }

}
