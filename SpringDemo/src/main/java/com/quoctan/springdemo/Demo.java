package com.quoctan.springdemo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Demo {
    public static void main(String[] agrs) {
        // Spring IoC cho phép tương tác với các lớp thông qua tập tin Beans.xml
        // và không cần phụ thuộc/tạo đối tượng truyền thống, khi cần sử dụng
        // class nào chỉ cần tạo 
        
        // Tạo file theo đường dẫn mặc định src/main/resources/Beans.xml
        // Sau đó build, kiểm tra xem Beans.xml trong src/target/classes được
        // tạo chưa vì đây mới là file thực sự chạy sau khi build
        
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld h = (HelloWorld) context.getBean("helloWorld");
        System.out.println(h.getMessage());
    }
    
}
