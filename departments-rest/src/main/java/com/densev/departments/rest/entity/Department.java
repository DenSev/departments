package com.densev.departments.rest.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Table;

/**
 * Department entity, fields: id, name
 *
 * @author DENIS SEVOSTEENKO
 */
@javax.persistence.Entity
@Table(name = "departments")
public class Department extends Entity {

    private static final long serialVersionUID = -8581790907777298148L;
    private String name;

    public Department() {
    }

    public Department(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
