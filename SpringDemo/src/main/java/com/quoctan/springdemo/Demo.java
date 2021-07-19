package com.quoctan.springdemo;


import java.util.List;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class Demo {
    public static void main(String[] agrs) {
        // Spring IoC cho phép tương tác với các lớp thông qua tập tin Beans.xml
        // và không cần phụ thuộc/tạo đối tượng truyền thống, khi cần sử dụng
        // class nào chỉ cần tạo thông qua ApplicationContext và sử dụng ở bên
        // Beans.xml
        
        // Tạo file theo đường dẫn mặc định src/main/resources/Beans.xml
        // Sau đó build, kiểm tra xem Beans.xml trong src/target/classes được
        // tạo chưa vì đây mới là file thực sự chạy sau khi build.
        
        // ClassPath nó sẽ tìm vào thư mục chứa class đã được build ra để sử
        // dụng (target ở trên)
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        // context.getBean(trường id bên file Beans.xml), cũng tại thẻ <bean>
        // có các trường class dẫn từ src vào lớp mình muốn sử dụng, xuống dưới
        // thẻ <property> có các trường name để chỉ định thuộc tính muốn sử dụng
        // trong lớp, value để sử dụng phương thức setter set thuộc tính cho nó.
        // Xong ở phía code này chúng ta có thể tương tác như với lớp OOP truyền
        // thống, khi cần thay đổi giá trị đầu vào ta có thể sửa ở file xml thôi
        // không cần thay đổi mã nguồn nhiều mà chỉ tương tác với IoC container.

//        // Mặc định những đối tượng tạo như này là singleton - thực tế chỉ có
//        // một đối tượng được tạo (dù khai báo khai báo nhiều lần khác nhau),
//        // thích hợp trong trường hợp như kết nối db. Khi muốn mỗi lần getBean
//        // mà nó tạo mới đối tượng thì thêm trong <bean scope="prototype">
//        HelloWorld h = (HelloWorld) context.getBean("helloWorld");
//        System.out.println(h.getMessage());
        
//        //  ### Các trường này hoàn toàn có thể được thay thế bằng bean bên dưới
//        // Nạp driver tương tác csdl khi sử dụng bằng IoC (file bean xml)
//        DataSource dataSource = (DataSource) context.getBean("dataSource");
//        // Mọi thứ tương tác với jdbc đều phải truy cập thông qua lớp
//        // JdbcTemplate
//        
//        // Query (truy vấn) dữ liệu dùng interface RowMapper và biểu thức lambda
//        JdbcTemplate t = new JdbcTemplate(dataSource);
//        // ResultSet trả về kiểu gì thì query nó sẽ trả về List kiểu đó
        
        // ### Đã thay thế bằng bean
        JdbcTemplate t = (JdbcTemplate) context.getBean("jdbcTemplate");
        
        List<String> result = t.query("SELECT * FROM product;", (rs, i) -> {
            return rs.getString("name");
        });
        result.forEach(p -> System.out.println(p));
        
        // Query có điều kiện (các trường có từ "tính")
        List<String> result2 = t.query("SELECT * FROM category WHERE name LIKE CONCAT('%', ?, '%');", (rs, i) -> {
            return rs.getString("name");
        }, "tính");
        result2.forEach(p -> System.out.println(p));
        
        // Cập nhật dữ liệu
        t.update("UPDATE category SET name=? WHERE id=?", "Laptop", 5);
        
    }
    
}
