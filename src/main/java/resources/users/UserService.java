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

    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  users");

        return query.list();
    }

    public User getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(User.class, id);
    }

    public static void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);

        session.delete(user);
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        User existingPerson = (User) session.get(User.class, user.getId());

//        existingPerson.setFirstName(user.getFirstName());
//        existingPerson.setLastName(user.getLastName());
//        existingPerson.setMoney(user.getMoney());

        session.save(existingPerson);
    }
}