package resources.news;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.infrastructure.SessionHelper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by root on 10.9.16.
 */
public class NewsService {
        @Resource(name="sessionFactory")
        private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

        public static List<News> getAll() {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM News");
            List<News> news = query.list();
            session.getTransaction().commit();

            return news;
        }



        public static News getById(Integer id) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            News n = session.get(News.class, id);
            session.getTransaction().commit();

            return n;
        }

        /*public static void save(News news) {
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
*/
}
