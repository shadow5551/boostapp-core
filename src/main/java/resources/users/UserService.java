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
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.getTransaction().commit();

        return users;
    }

    public static User getByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE email = :email");
        query.setParameter("email", email);

        List<User> users = query.list();
        session.getTransaction().commit();

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public static User getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();

        return user;
    }

    public static void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        user.setIsArchived(false);
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

        existingPerson.setIsArchived(user.getIsArchived());

        session.save(existingPerson);
        session.getTransaction().commit();
    }

}