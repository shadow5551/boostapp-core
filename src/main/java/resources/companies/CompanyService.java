package resources.companies;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.companies.Company;
import resources.infrastructure.SessionHelper;

public class CompanyService {

    @Resource(name="sessionFactory")
    private static SessionFactory sessionFactory = SessionHelper.sessionFactory;

    public static List<Company> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Company");
        List<Company> companies = query.list();
        session.getTransaction().commit();

        return companies;
    }

    public static Company getById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Company company = session.get(Company.class, id);
        session.getTransaction().commit();
        return company;
    }

    public static Company save(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(company);
        session.getTransaction().commit();

        return company;
    }

    public static void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Company company = (Company) session.get(Company.class, id);
        session.delete(company);
        session.getTransaction().commit();
    }

    public static void update(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Company existingCompany = (Company) session.get(Company.class, company.getId());

        existingCompany.setTitle(company.getTitle());
        existingCompany.setTagLine(company.getTagLine());

        session.save(existingCompany);
        session.getTransaction().commit();
    }
}