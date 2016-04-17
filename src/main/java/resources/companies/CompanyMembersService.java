package resources.companies;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.companies.Company;
import resources.infrastructure.SessionHelper;

public class CompanyMembersService {

    @Resource(name="sessionFactory")
    private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

    public static CompanyMember getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CompanyMember.class, id);
    }

    public static void save(CompanyMember companyMember) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(companyMember);
        session.getTransaction().commit();
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompanyMember companyMember = (CompanyMember) session.get(CompanyMember.class, id);
        session.delete(companyMember);
        session.getTransaction().commit();
    }

    public static void update(CompanyMember companyMember) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CompanyMember existingCompanyMember = (CompanyMember) session.get(CompanyMember.class, companyMember.getId());

        existingCompanyMember.setCompanyId(companyMember.getCompanyId());
        existingCompanyMember.setUserId(companyMember.getUserId());

        session.save(existingCompanyMember);
        session.getTransaction().commit();
    }
}