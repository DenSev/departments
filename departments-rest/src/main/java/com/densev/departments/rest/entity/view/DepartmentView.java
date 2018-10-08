package com.densev.departments.rest.entity.view;

import com.densev.departments.rest.entity.Department;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * View wrapper over department, includes two additional fields: count and salary
 *
 * @author DENIS SEVOSTEENKO
 */
public class DepartmentView extends Department {

    private static final long serialVersionUID = -874063191438611202L;
    private Long count;
    private Double salary;

    public DepartmentView() {
    }

    public DepartmentView(long id, String name, long count, double salary) {
        super(id, name);
        this.count = count;
        this.salary = salary;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
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
