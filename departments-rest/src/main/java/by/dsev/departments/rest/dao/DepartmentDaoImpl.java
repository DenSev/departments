package by.dsev.departments.rest.dao;

import by.dsev.departments.rest.entity.Department;
import by.dsev.departments.rest.entity.view.DepartmentView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created on: 10/04/18
 */
@Component
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentDaoImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Department> readAll() {
        try (Session session = sessionFactory.openSession()) {
            List<Department> departments = session
                .createQuery("from Department order by id", Department.class)
                .list();
            return departments;
        }
    }

    @Override
    public List<DepartmentView> readAllViews() {
        try (Session session = sessionFactory.openSession()) {
            List<DepartmentView> departmentViews = (List<DepartmentView>) session
                .createQuery("select new by.dsev.departments.rest.entity.view.DepartmentView(d.id, d.name, " +
                    "(select count(e.id) from Employee e where e.department = d) as count, " +
                    "(select avg(e.salary) from Employee e where e.department = d) as salary ) " +
                    "from Department d order by d.id", DepartmentView.class)
                .list();

            return departmentViews;
        }
    }

    @Override
    public Department create(final Department department) {
        try (Session session = sessionFactory.openSession()) {
            session.persist(department);
            return department;
        }
    }

    @Override
    public Department read(final Long id) {
        try (Session session = sessionFactory.openSession()) {
            final Department department = session.get(Department.class, id);
            return department;
        }
    }

    @Override
    public Department update(final Department department) {
        try (Session session = sessionFactory.openSession()) {
            final Department persistedDepartment = (Department) session.merge(department);
            return persistedDepartment;
        }

    }

    @Override
    public boolean delete(final Long id) {
        try (Session session = sessionFactory.openSession()) {
            final Department department = session.get(Department.class, id);
            session.delete(department);
            return true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
