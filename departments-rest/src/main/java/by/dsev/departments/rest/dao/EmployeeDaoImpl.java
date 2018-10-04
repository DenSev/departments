package by.dsev.departments.rest.dao;

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.Employee;
import by.dsev.departments.rest.entity.SearchForm;
import by.dsev.departments.rest.entity.view.EmployeeView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                .createQuery("select e.id, e.fullName, e.dateOfBirth, e.salary, d.name as department " +
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
                .createQuery("select e.id, e.fullName, e.dateOfBirth, e.salary, d.name as department " +
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
        try (Session session = sessionFactory.openSession()) {
            List<EmployeeView> employeeViews = session
                .createQuery("select e.id, e.fullName, e.dateOfBirth, e.salary, d.name as department " +
                    "from Employee e " +
                    "left join Department d on e.department = d " +
                    "where e.dateOfBirth >= :startDate " +
                    "and e.dateOfBirth <= :endDate " +
                    "order by e.id", EmployeeView.class)
                .setParameter("startDate", form.getStartDate())
                .setParameter("endDate", form.getEndDate())
                .list();
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
