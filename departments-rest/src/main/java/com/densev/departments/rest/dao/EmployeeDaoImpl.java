package com.densev.departments.rest.dao;

import com.densev.departments.rest.entity.Employee;
import com.densev.departments.rest.api.SearchForm;
import com.densev.departments.rest.entity.view.EmployeeView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.TemporalType;
import java.util.List;

/**
 * Created on: 10/04/18
 */
@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> readAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session
                .createQuery("from Employee order by id", Employee.class)
                .list();
            return employees;
        }
    }

    @Override
    public List<EmployeeView> readAllViews() {
        try (Session session = sessionFactory.openSession()) {
            List<EmployeeView> employeeViews = session
                .createQuery("select new com.densev.departments.rest.entity.view.EmployeeView(e.id, e.fullName, e.dateOfBirth, e.salary, d.name ) " +
                    "from Employee e " +
                    "left join Department d on e.department = d " +
                    "order by e.id", EmployeeView.class)
                .list();
            return employeeViews;
        }
    }

    @Override
    public List<EmployeeView> readAllViewsByDepartmentId(Long id) {
        try (Session session = sessionFactory.openSession()) {
            List<EmployeeView> employeeViews = session
                .createQuery("select new com.densev.departments.rest.entity.view.EmployeeView(e.id, e.fullName, e.dateOfBirth, e.salary, d.name) " +
                    "from Employee e " +
                    "left join Department d on e.department = :id " +
                    "order by e.id", EmployeeView.class)
                .setParameter("id", id)
                .list();
            return employeeViews;
        }
    }

    @Override
    public List<EmployeeView> searchByDate(SearchForm form) {
        //FIXME fix edge date
        try (Session session = sessionFactory.openSession()) {
            StringBuilder stringBuilder = new StringBuilder(
                "select new com.densev.departments.rest.entity.view.EmployeeView(" +
                    "e.id, " +
                    "e.fullName, " +
                    "e.dateOfBirth, " +
                    "e.salary, " +
                    "d.name" +
                    ") " +
                    "from Employee e " +
                    "left join Department d on e.department = d where ");
            if (form.getStartDate() != null) {
                stringBuilder.append("DATE(e.dateOfBirth) >= DATE(:startDate) ");
                if (form.getEndDate() != null) {
                    stringBuilder.append("and ");
                }
            }
            if (form.getEndDate() != null) {
                stringBuilder.append("DATE(e.dateOfBirth) <= DATE(:endDate) ");
            }
            stringBuilder.append("order by e.id");

            Query<EmployeeView> employeeViewQuery = session
                .createQuery(stringBuilder.toString(), EmployeeView.class);
            if (form.getStartDate() != null) {
                employeeViewQuery.setParameter("startDate", form.getStartDate(), TemporalType.DATE);
            }
            if (form.getEndDate() != null) {
                employeeViewQuery.setParameter("endDate", form.getEndDate(), TemporalType.DATE);
            }

            List<EmployeeView> employeeViews = employeeViewQuery.list();
            return employeeViews;
        }
        /*<select id="searchByDate" resultMap="employeeView" parameterType="searchForm">
        SELECT e.id, full_name, date_of_birth, salary, d.name as department
        FROM ${db.schema}.employees e
        LEFT JOIN ${db.schema}.departments d ON department_id = d.id
        <if test="startDate neq null and endDate eq null">
            WHERE e.date_of_birth = #{startDate}
        </if>
        <if test="startDate eq null and endDate neq null">
            WHERE e.date_of_birth = #{endDate}
        </if>
        <if test="startDate neq null and endDate neq null">
            WHERE e.date_of_birth >= #{startDate}
            AND e.date_of_birth  &lt;= #{endDate}
        </if>
        ORDER BY e.id
    </select>*/
    }

    @Override
    public Employee create(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(employee);
            return employee;
        }
    }

    @Override
    public Employee read(Long id) {
        try (Session session = sessionFactory.openSession()) {
            final Employee employee = session.get(Employee.class, id);
            return employee;
        }
    }

    @Override
    public Employee update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            final Employee persistedEmployee = (Employee) session.merge(employee);
            return persistedEmployee;
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            final Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
