package by.dsev.departments.entity.view;

import by.dsev.departments.entity.Department;

/**
 * View wrapper over department, includes two additional fields: count and salary
 * @author DENIS SEVOSTEENKO
 */
public class DepartmentView extends Department{

    private static final long serialVersionUID = -874063191438611202L;
    private Integer count;
    private Double salary;
    
    
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((count == null) ? 0 : count.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DepartmentView other = (DepartmentView) obj;
        if (count == null) {
            if (other.count != null)
                return false;
        } else if (!count.equals(other.count))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }
}
