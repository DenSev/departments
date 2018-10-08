package com.densev.departments.rest.api.dto;

import java.io.Serializable;

/**
 * Created on: 10/05/18
 */
public class DepartmentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private Double salary;
    private Long count;

    private DepartmentDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.salary = builder.salary;
        this.count = builder.count;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Long getCount() {
        return count;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private String name;
        private Double salary;
        private Long count;

        private Builder() {
        }

        public static Builder aDepartmentDTO() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder count(Long count) {
            this.count = count;
            return this;
        }

        public DepartmentDTO build() {
            return new DepartmentDTO(this);
        }
    }
}
