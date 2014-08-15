package com.tleaf.lifelog.configuration;

import com.tleaf.lifelog.dao.ApiDao;
import com.tleaf.lifelog.dao.ApiDaoImple;
import com.tleaf.lifelog.dao.CouchDbConn;
import com.tleaf.lifelog.dao.CouchDbConnImpl;
import com.tleaf.lifelog.service.ApiService;
import com.tleaf.lifelog.service.ApiServiceImple;
import com.tleaf.lifelog.service.JsonResourceCreatorimpl;
import com.tleaf.lifelog.service.ResourceCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc  //same as <mvc:annotation-driven/>
@ComponentScan(basePackages = {"com.tleaf.lifelog"})//same as <context:component-scan base-package="com.tleaf.lifelog"/>
@PropertySource("classpath:couchdb.properties")

public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //...
    }

    //Set default servlet handler, this is the same as <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //Add bean for InternalResourceViewResolver
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    //properties file
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /* DI */
    @Bean
    public ApiService apiService() {
        return new ApiServiceImple();
    }

    @Bean
    public ApiDao apiDao() {
        return new ApiDaoImple();
    }

    @Bean
    public CouchDbConn couchDbConn() {
        return new CouchDbConnImpl();
    }

    @Bean
    public ResourceCreator resourceCreator() {
        return new JsonResourceCreatorimpl();
    }
}
