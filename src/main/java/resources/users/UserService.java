package resources.users;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.users.User;
import resources.infrastructure.SessionHelper;

public class UserService {

    @Resource(name="sessionFactory")
    private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

    public static List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM  User");
        List<User> users = query.list();
        session.getTransaction().commit();

        return users;
    }

    public static User getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

    public static void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    public static void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User existingPerson = (User) session.get(User.class, user.getId());

        //work with user

        session.save(existingPerson);
        session.getTransaction().commit();
    }
}