package resources.projects;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.infrastructure.SessionHelper;

public class ProjectService {

    @Resource(name="sessionFactory")
    private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

    public static List<Project> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Project");
        List<Project> projects = query.list();
        session.getTransaction().commit();

        return projects;
    }

    public static List<Project> getByCompanyId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Project WHERE companyId = " + id);
        List<Project> projects = query.list();
        session.getTransaction().commit();

        return projects;
    }

    public static Project getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Project p = session.get(Project.class, id);
        session.getTransaction().commit();

        return p;
    }

    public static void save(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        project.setPaymentAmount(0);
        session.save(project);
        session.getTransaction().commit();
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Project project = (Project) session.get(Project.class, id);
        if (project != null) {
            session.delete(project);
        }
        session.getTransaction().commit();
    }

    public static void update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Project existingProject = (Project) session.get(Project.class, project.getId());

        existingProject.setTitle(project.getTitle());
        existingProject.setDescription(project.getDescription());
        existingProject.setAmount(project.getAmount());
        existingProject.setPaymentAmount(project.getPaymentAmount());

        session.save(existingProject);
        session.getTransaction().commit();
    }
}