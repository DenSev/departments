package com.densev.departments.rest.entity.view;

import com.densev.departments.rest.entity.Entity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * View variant of Employee entity: department (name) instead of departmentId and string dob instead of date
 *
 * @author DENIS SEVOSTEENKO
 */
@JsonPropertyOrder({
    "id",
    "fullName",
    "dob",
    "salary",
    "department"
})
public class EmployeeView extends Entity {
    private static final long serialVersionUID = 8561756479132667470L;
    private String fullName;
    private String dob;
    private Integer salary;
    private String department;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
