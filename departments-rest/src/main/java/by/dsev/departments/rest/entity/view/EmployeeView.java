package by.dsev.departments.rest.entity.view;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import by.dsev.departments.rest.entity.Entity;

/**
 * View variant of Employee entity: department (name) instead of departmentId and string dob instead of date
 * @author DENIS SEVOSTEENKO
 */
@JsonPropertyOrder({
     "id",
     "fullName",
     "dob",
     "salary",
     "department"
 })
public class EmployeeView extends Entity{
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
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
        EmployeeView other = (EmployeeView) obj;
        if (department == null) {
            if (other.department != null)
                return false;
        } else if (!department.equals(other.department))
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }
}
