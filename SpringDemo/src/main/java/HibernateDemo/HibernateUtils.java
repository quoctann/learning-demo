package HibernateDemo;

import HibernateDemo.pojo.Category;
import HibernateDemo.pojo.Manufacturer;
import HibernateDemo.pojo.Product;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


// File cấu hình này chỉ dùng một lần thôi, bean singluton, config tại file này
public class HibernateUtils {
    // Mong muốn nó được tạo 1 lần thôi, do cấu hình chỉ có 1 và ko bị thay đổi
    // trong quá trình chạy chương trình
    private static final SessionFactory FACTORY;
    
    // Cấu hình này cũng có thể cấu hình bằng xml
    // Khối tĩnh được gọi khi lớp lần đầu lớp được nạp, và chỉ chạy 1 lần
    static {
        Configuration conf = new Configuration();
        
        // Để cấu hình bằng tập tin xml thay vì code java
//        conf.configure("hibernate.conf.xml");
        
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/saledb");
        props.put(Environment.USER, "dev");
        props.put(Environment.PASS, "P@ssw0rd");
        props.put(Environment.SHOW_SQL, "true");
        
        conf.setProperties(props);
        
        // Khai báo lớp pojo cần sử dụng
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(Manufacturer.class);
        
        // Cấu hình này hầu như ko thay đổi nên cứ làm như sau là được, câu lệnh
        // này để tạo một cấu hình để tạo một factory 
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        
        FACTORY = conf.buildSessionFactory(registry);
        
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
}
