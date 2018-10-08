package com.densev.departments.rest.service.converter;

import com.densev.departments.rest.api.dto.DepartmentDTO;
import com.densev.departments.rest.api.dto.EmployeeDTO;
import com.densev.departments.rest.entity.Department;
import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.entity.view.DepartmentView;
import com.densev.departments.rest.entity.view.EmployeeView;
import org.springframework.core.convert.TypeDescriptor;

import java.util.List;

/**
 * Created on: 10/08/18
 */
public class ConversionConstants {

    private ConversionConstants(){
    }

    public static final TypeDescriptor DEPARTMENT_VIEW = TypeDescriptor.valueOf(DepartmentView .class);
    public static final TypeDescriptor DEPARTMENT_DTO = TypeDescriptor.valueOf(DepartmentDTO .class);
    public static final TypeDescriptor DEPARTMENT =TypeDescriptor.valueOf(Department.class);
    public static final TypeDescriptor DEPARTMENT_VIEW_LIST = TypeDescriptor.collection(List .class, DEPARTMENT_VIEW);
    public static final TypeDescriptor DEPARTMENT_DTO_LIST = TypeDescriptor.collection(List.class, DEPARTMENT_DTO);
    public static final TypeDescriptor DEPARTMENT_LIST = TypeDescriptor.collection(List.class, DEPARTMENT);
    public static final TypeDescriptor EMPLOYEE = TypeDescriptor.valueOf(Employee.class);
    public static final TypeDescriptor EMPLOYEE_VIEW = TypeDescriptor.valueOf(EmployeeView.class);
    public static final TypeDescriptor EMPLOYEE_DTO = TypeDescriptor.valueOf(EmployeeDTO.class);
    public static final TypeDescriptor EMPLOYEE_LIST = TypeDescriptor.collection(List.class, EMPLOYEE);
    public static final TypeDescriptor EMPLOYEE_VIEW_LIST = TypeDescriptor.collection(List.class, EMPLOYEE_VIEW);
    public static final TypeDescriptor EMPLOYEE_DTO_LIST = TypeDescriptor.collection(List.class, EMPLOYEE_DTO);

}
