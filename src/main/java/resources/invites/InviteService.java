package resources.invites;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.invites.Invite;
import resources.companies.Company;
import resources.infrastructure.SessionHelper;

public class InviteService {
    @Resource(name="sessionFactory")
    private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

    public static Invite getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Invite.class, id);
    }

    public static List<Invite> getAllByCompanyId(Integer companyId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Invite WHERE companyId = :companyId AND status = :status");
        query.setParameter("companyId", companyId);
        query.setParameter("status", "pending");
        List<Invite> invites = query.list();
        session.getTransaction().commit();

        return invites;
    }

    public static List<Invite> getAllByUserId(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Invite WHERE userId = :userId AND status = :status");
        query.setParameter("userId", userId);
        query.setParameter("status", "pending");

        List<Invite> invites = query.list();
        session.getTransaction().commit();

        return invites;
    }

    public static Invite getByUserIdAndCompanyId(Integer userId, Integer companyId) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Invite WHERE userId = :userId AND companyId = :companyId AND status = :status");
        query.setParameter("userId", userId);
        query.setParameter("companyId", companyId);
        query.setParameter("status", "pending");

        List<Invite> invites = query.list();
        session.getTransaction().commit();

        return invites.isEmpty() ? null : invites.get(0);
    }

    public static Invite save(Invite invite) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(invite);
        session.getTransaction().commit();

        return invite;
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Invite invite = (Invite) session.get(Invite.class, id);
        session.delete(invite);
        session.getTransaction().commit();
    }

    public static Invite update(Invite invite) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Invite existingInvite = (Invite) session.get(Invite.class, invite.getId());

        existingInvite.setStatus(invite.getStatus());

        session.save(existingInvite);
        session.getTransaction().commit();

        return existingInvite;
    }
}