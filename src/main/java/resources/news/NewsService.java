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

    public static List<News> getByCompanyId(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM News WHERE companyId = " + id);
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

        public static void save(News news) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.save(news);
            session.getTransaction().commit();
        }

        public static void delete(Integer id) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            News news = (News) session.get(News.class, id);
            if (news != null) {
                session.delete(news);
            }
            session.getTransaction().commit();
        }

        public static void update(News news) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            News existingNews = (News) session.get(News.class, news.getId());

            existingNews.setTitle(news.getTitle());
            existingNews.setContent(news.getContent());
            session.save(existingNews);
            session.getTransaction().commit();
        }

}
