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
        Query query = session.createQuery("FROM  projects");
        List<Project> projects = query.list();
        session.getTransaction().commit();

        return projects;
    }

    public static Project getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Project.class, id);
    }

    public static void save(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(project);
        session.getTransaction().commit();
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Project project = (Project) session.get(Project.class, id);
        session.delete(project);
        session.getTransaction().commit();
    }

    public static void update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Project existingProject = (Project) session.get(Project.class, project.getId());

        // work with project

        session.save(existingProject);
        session.getTransaction().commit();
    }
}