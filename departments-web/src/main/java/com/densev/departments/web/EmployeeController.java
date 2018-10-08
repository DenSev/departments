package com.densev.departments.web;


import com.densev.departments.entity.SearchForm;
import com.densev.departments.entity.view.EmployeeView;
import com.densev.departments.service.DepartmentService;
import com.densev.departments.service.EmployeeService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * Controller working with jsp pages and {@link Employee} and {@link EmployeeView} objects
 *
 * @author DENIS SEVOSTEENKO
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    /**
     * Opens edit employee page with a new {@link Employee} object as department model attribute
     * and a list of {@link Department}s for select input
     *
     * @param model
     * @return forwards to employee edit page
     */
    @RequestMapping(value = "/new.html", method = RequestMethod.GET)
    public String add(Model model) {
        try {
            model.addAttribute("employee", new Employee());
            model.addAttribute("departments", departmentService.findAllViews());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }
        return ".employee";
    }

    /**
     * Opens edit employee page with a {@link Employee} object that was
     * looked up by id specified as department model attribute
     * and a list of {@link Department}s for select input
     *
     * @param id    - id of employee to be edited
     * @param model
     * @return forwards to employee edit page
     */
    @RequestMapping(value = "/edit.html", method = RequestMethod.GET)
    public String find(@RequestParam Long id, Model model) {
        try {
            model.addAttribute("employee", employeeService.find(id));
            model.addAttribute("departments", departmentService.findAll());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }
        return ".employee";
    }

    /**
     * Saves an employee, returns to edit page if there are any validation errors
     *
     * @param employee - employee to be saved
     * @param result
     * @param model
     * @return redirects to main page of jsp version of client application and
     * back to edit page if there are validation errors
     */
    @RequestMapping(value = "/save.html", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                LOG.error(result.getAllErrors().toString());
                model.addAttribute("employee", employee);
                model.addAttribute("departments", departmentService.findAll());
                return ".employee";
            }
            employeeService.save(employee);
        } catch (DataIntegrityViolationException e) {
            LOG.error(e.getMessage(), e.getCause());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }
        return "redirect:/rest_template.html";
    }

    /**
     * Looks up employees falling within specified parameters: date of birth must be either equal
     * to one of the dates if the other is null or fall between two if neither are
     *
     * @param searchForm - {@link SearchForm} query, fields may be null, if one of the fields is null
     *                   and other isn't searches by equality comparison rather than interval
     * @param result
     * @param model
     * @return page with search results displayed
     */
    @RequestMapping(value = "/search.html", method = RequestMethod.POST)
    public String search(@Valid @ModelAttribute SearchForm searchForm, BindingResult result, Model model) {
        try {
            List<EmployeeView> views = employeeService.searchByDate(searchForm);
            model.addAttribute("searchResults", views);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }
        return ".search-results";
    }

    /**
     * Deletes an employee by specified id and reloads page to refresh results
     *
     * @param id
     * @return redirects or 'reloads' main page of jsp version of client application
     */
    @RequestMapping(value = "/delete.html", method = RequestMethod.GET)
    public String remove(@RequestParam Long id) {
        try {
            employeeService.remove(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }
        return "redirect:/rest_template.html";
    }

}