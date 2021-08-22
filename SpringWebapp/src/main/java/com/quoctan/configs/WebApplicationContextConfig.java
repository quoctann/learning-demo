package com.quoctan.configs;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.quoctan.formatter.CategoryFormatter;
import com.quoctan.validator.ProductNameValidator;
import com.quoctan.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.quoctan.controllers",
    "com.quoctan.repository",
    "com.quoctan.service",
    "com.quoctan.validator"
})
public class WebApplicationContextConfig implements WebMvcConfigurer {
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    // Khai báo tài nguyên tĩnh cho hệ thống, đi vào từ thư mục webapp
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Thứ tự là đường dẫn lập trình ở ngoài - đường dẫn trong hệ thống
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/resources/images/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new CategoryFormatter());
    }
    
    @Override
    public Validator getValidator() {
        return validator();
    }
    
    // Cấu hình validator của lớp product
    @Bean
    public WebAppValidator productValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new ProductNameValidator());
        WebAppValidator v = new WebAppValidator();
        v.setSpringValidator(springValidators);
        return v;
    }
    
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean v = new LocalValidatorFactoryBean();
        v.setValidationMessageSource(messageSource());
        return v;
    }
    
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resource = new InternalResourceViewResolver();
        resource.setViewClass(JstlView.class);
        resource.setPrefix("/WEB-INF/jsp/");
        resource.setSuffix(".jsp");
        return resource;
    }
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
    
    // Setup bean để xử lý upload ảnh file các thứ encode multipart
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    
    // Tạo bean tương tác với cloudinary (mở dashboard nó ra)
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "quoctan",
            "api_key", "146256471874449",
            "api_secret", "DngyGbiJtXwvStyV7r_pbfa8St8",
            "secure", true    
        ));
        return c;
    }
}
