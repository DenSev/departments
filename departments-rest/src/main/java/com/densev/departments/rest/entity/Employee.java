package com.densev.departments.rest.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Employee entity, fields: id, fullName, dob (date of birth), salary, departmentId
 *
 * @author DENIS SEVOSTEENKO
 */
@JsonPropertyOrder({
    "id",
    "fullName",
    "dob",
    "salary",
    "departmentId"
})
@javax.persistence.Entity
@Table(name = "employees")
public class Employee extends Entity {

    private static final long serialVersionUID = -641963119356963519L;
    private String fullName;
    private Date dateOfBirth;
    private Integer salary;
    @ManyToOne
    private Department department;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dob) {
        this.dateOfBirth = dob;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
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
