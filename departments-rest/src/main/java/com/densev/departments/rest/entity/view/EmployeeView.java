package com.densev.departments.rest.entity.view;

import com.densev.departments.rest.entity.Entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * View variant of Employee entity: department (name) instead of departmentId and string dateOfBirth instead of date
 *
 * @author DENIS SEVOSTEENKO
 */
@JsonPropertyOrder({
    "id",
    "fullName",
    "dateOfBirth",
    "salary",
    "department"
})
public class EmployeeView extends Entity {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final long serialVersionUID = 8561756479132667470L;
    private String fullName;
    private String dateOfBirth;
    private Integer salary;
    private String department;

    public EmployeeView() {
    }

    public EmployeeView(long id, String fullName, Date dateOfBirth, int salary, String department) {
        super(id);
        this.fullName = fullName;
        if (dateOfBirth != null) {
            this.dateOfBirth = DATE_FORMAT.format(dateOfBirth);
        }
        this.salary = salary;
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
