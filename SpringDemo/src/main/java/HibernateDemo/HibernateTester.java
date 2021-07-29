package HibernateDemo;

import HibernateDemo.pojo.Category;
import HibernateDemo.pojo.Manufacturer;
import HibernateDemo.pojo.Product;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;


public class HibernateTester {
    public static void main(String[] args) {
        Session session = HibernateUtils.getFACTORY().openSession();

//        // Chèn mới một dòng
//        Category cate = new Category();
//        // Trạng thái đối tượng này là transient do chưa liên kết đến đối tượng
//        // nào dưới csdl cả, khi save() thì sẽ tạo dòng mới
//        cate.setName("Accesories");
//        cate.setDescription("Test hibernate");
//        session.save(cate);
        
//        // Lấy lên một dòng để cập nhật, get nạp vào lớp pojo và mã khóa chính
//        // trạng thái này là persistent
//        Category cate = session.get(Category.class, 8);
//        cate.setDescription("Dữ liệu cập nhật bởi hibernate");
//        // Do update/delete có khả năng tranh chấp dữ liệu trên môi trường phân
//        // tán nên phải bật giao tác (transaction) lên
//        session.getTransaction().begin();
//        //session.save(cate);
//        session.delete(cate);
//        session.getTransaction().commit();

//        // Demo HQL Hibernate Query Language> truy vấn trên LỚP ĐỐI TƯỢNG ko 
//        // qtâm đến csdl (vì đã config lớp mapping đến bảng rồi)
//        Query q = session.createQuery("FROM Category WHERE id=1");
//        List<Category> cates = q.getResultList();
//        cates.forEach(c -> System.out.printf("%d - %s\n", c.getId(), c.getName()));
    
//        // Tương tác với bảng có khóa ngoại
//        Product p = new Product();
//        p.setName("Ipad Pro 2021");
//        p.setPrice(new BigDecimal(22000000));
//        Category c = session.get(Category.class, 2);
//        p.setCategory(c);
//        session.save(p);

//        // Tương tác với bảng quan hệ n-n
//        Product p = new Product();
//        p.setName("New Tablet 2021");
//        p.setPrice(new BigDecimal(29000000));
//        Category c = session.get(Category.class, 2);
//        p.setCategory(c);
//        Set<Manufacturer> mans = new HashSet<>();
//        mans.add(session.get(Manufacturer.class, 1));
//        mans.add(session.get(Manufacturer.class, 2));
//        p.setManufacturers(mans);
//        // Do hành động trên tác động đến 2 bảng, do công việc này muốn thành
//        // công thì cả 2 tiến trình đều phải thành công nên phải bọc trong giao
//        // tác
//        session.getTransaction().begin();
//        session.save(p);
//        session.getTransaction().commit();
                
//        // Truy xuất ngược lại từ quan hệ n-n
//        Manufacturer m = session.get(Manufacturer.class, 1);
//        m.getProducts().forEach(p -> System.out.printf("%d - %s\n", p.getId(), p.getName()));
        
        // Sử dụng Ctiteria Query API
        // Xây dựng đối tượng truy vấn
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//        // Có mấy bảng tạo mấy root
//        Root root = query.from(Product.class);
//        query = query.select(root);
//        // Tạo vị từ để truy xuất theo điều kiện
        // Có tên chứa galaxy hoặc iphone
//        Predicate p1 = builder.like(root.get("name").as(String.class), "%Galaxy%");
//        Predicate p2 = builder.like(root.get("name").as(String.class), "%iPhone%");
        // Có giá trên 20tr và dưới 30tr
//        Predicate p1 = builder.greaterThanOrEqualTo(root.get("price").as(BigDecimal.class), new BigDecimal(20000000));
//        Predicate p2 = builder.lessThanOrEqualTo(root.get("price").as(BigDecimal.class), new BigDecimal(30000000));
//        query = query.where(builder.and(p1, p2));
//        // Hoặc ngắn gọn hơn 2 dòng trên ta có:
//        Predicate pp = builder.between(root.get("price").as(BigDecimal.class), new BigDecimal(20000000), new BigDecimal(30000000));
//        query = query.where(pp);
//        
//        // Tiến hành truy vấn
//        Query q = session.createQuery(query);
//        List<Product> products = q.getResultList();
//        products.forEach(p -> System.out.printf("%d - %s: %.2f\n", p.getId(), p.getName(), p.getPrice()));
        
        // Thống kê cơ bản
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root root = query.from(Product.class);
//        query = query.multiselect(builder.count(root.get("id").as(Integer.class)),
//                builder.max(root.get("price").as(BigDecimal.class)));
//        Query q = session.createQuery(query);
//        Object[] kq = (Object[]) q.getSingleResult();
//        System.out.println("Count: " + kq[0]);
//        System.out.println("Max: " + kq[1]);
        
        // Thống kê nhiều dòng
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Product> pRoot = query.from(Product.class);
        Root<Category> cRoot = query.from(Category.class);
        query.where(builder.equal(pRoot.get("category"), cRoot.get("id")));
        query = query.multiselect(cRoot.get("name").as(String.class),
                builder.count(pRoot.get("id").as(Integer.class)),
                builder.max(pRoot.get("price").as(BigDecimal.class)));
        query = query.groupBy(cRoot.get("name").as(String.class));
        query = query.orderBy(builder.asc(cRoot.get("name").as(String.class)));
        Query q = session.createQuery(query);
        List<Object[]> kq = q.getResultList();
        kq.forEach(k -> {
            System.out.printf("%s - count: %d - Max: %.2f\n", k[0], k[1], k[2]);
        });
        
        session.close();
    }
}
