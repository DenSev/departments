package by.dsev.departments.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import by.dsev.departments.entity.SearchForm;
import by.dsev.departments.service.DepartmentService;
import by.dsev.departments.service.EmployeeService;


/**
 * Controller for main pages
 * @author DENIS SEVOSTEENKO
 */
@Controller
public class IndexController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    
    /**
     * welcom page
     * @return view name of main page
     */
    @RequestMapping("/main.html")
    public String index(Model model) {
        return ".index";
    }

    /**
     * main page jQuery version of client app
     * @param model
     * @return
     */
    @RequestMapping("/list.html")
    public String list(Model model) {
        return ".list";
    }

    /**
     * main page of jsp version of client app
     * @param model
     * @return
     */
    @RequestMapping("/rest_template.html")
    public String departments(Model model) {

        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("employees", employeeService.findAllViews());
        model.addAttribute("departments", departmentService.findAllViews());

        return ".rest_template";
    }

}
