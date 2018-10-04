package by.dsev.departments.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Department entity, fields: id, name
 * @author DENIS SEVOSTEENKO
 */
public class Department extends Entity{

    private static final long serialVersionUID = -8581790907777298148L;
    @NotNull
    @Size(min=4, max=200)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Department other = (Department) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    
}
