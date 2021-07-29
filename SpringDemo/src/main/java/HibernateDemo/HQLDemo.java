package HibernateDemo;


import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;


public class HQLDemo {
    public static void main(String[] agrs) {
        Session session = HibernateUtils.getFACTORY().openSession();
        Query q = session.createQuery("SELECT P.id, P.name, P.price FROM Product P");
        List<Object[]> products = q.getResultList();
        products.forEach(p -> System.out.printf("%d - %s\n", p[0], p[1], p[2]));
        
        session.close();
    }
}
