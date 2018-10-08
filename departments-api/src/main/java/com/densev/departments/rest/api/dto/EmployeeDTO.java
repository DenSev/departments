package com.densev.departments.rest.api.dto;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created on: 10/05/18
 */
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String fullName;
    private DateTime dateOfBirth;
    private long salary;
    private String department;
    private String departmentId;

    public EmployeeDTO() {
    }

    private EmployeeDTO(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.dateOfBirth = builder.dateOfBirth;
        this.salary = builder.salary;
        this.department = builder.department;
        this.departmentId = builder.departmentId;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public long getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private String fullName;
        private DateTime dateOfBirth;
        private long salary;
        private String department;
        private String departmentId;

        private Builder() {
        }

        public static Builder anEmployeeDTO() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder dateOfBirth(DateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder salary(long salary) {
            this.salary = salary;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder departmentId(String departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public EmployeeDTO build() {
            return new EmployeeDTO(this);
        }
    }
}
