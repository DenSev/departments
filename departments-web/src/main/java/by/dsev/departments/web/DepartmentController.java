package com.densev.departments.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.densev.departments.entity.Department;
import com.densev.departments.entity.view.DepartmentView;
import com.densev.departments.service.DepartmentService;

/**
 * Controller working with jsp pages and {@link Department} and {@link DepartmentView} objects
 * @author DENIS SEVOSTEENKO
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;

    /**
     * Opens edit department page with a new {@link Department} object as department model attribute
     * @param model 
     * @return forwards to department edit page
     */
    @RequestMapping(value = "/new.html", method = RequestMethod.GET)
    public String add( Model model) {
        try{
            model.addAttribute("department", new Department());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
        return ".department";
    }

    /**
     * Opens edit department page with a {@link Department} object 
     * that was looked up by specified id as department model attribute
     * @param id - id of object to be edited
     * @param model
     * @return forwards to department edit page
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String find(@RequestParam Long id, Model model) {
        try{
            model.addAttribute("department", departmentService.find(id));
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
        return ".department";
    }

    /**
     * Saves a department, returns to edit page if there are any validation errors
     * @param department - department to be saved
     * @param result
     * @param model
     * @return redirects to main page of jsp version of client application and 
     * back to edit page if there are validation errors
     */
    @RequestMapping(value = "/save.html", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Department department, BindingResult result, Model model) {
        try{
            if(result.hasErrors()){
                model.addAttribute("department", department);
                return ".department";
            }
            departmentService.save(department);
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
        return "redirect:/rest_template.html";
    }

    /**
     * Deletes a department by specified id and reloads page to refresh results
     * @param id - id of department to be deleted
     * @return redirects or 'reloads' main page of jsp version of client application
     */
    @RequestMapping(value = "/delete.html", method = RequestMethod.GET)
    public String remove(@RequestParam Long id) {

        try{
            departmentService.remove(id);
        }catch(DataIntegrityViolationException e){
            LOG.error(e.getMessage(), e.getCause());
        } catch(Exception e){
            LOG.error(e.getMessage(), e.getCause());
        }
        return "redirect:/rest_template.html";
    }

}
