package ua.epam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.epam.dao.entities.event.EventDao;
import ua.epam.dao.entities.event.IEventDao;
import ua.epam.dao.entities.report.IReportDao;
import ua.epam.dao.entities.report.ReportDao;
import ua.epam.dao.entities.subscribes.ISubscribesDao;
import ua.epam.dao.entities.subscribes.SubscribesDao;
import ua.epam.dao.entities.user.IUserDao;
import ua.epam.dao.entities.user.UserDao;
import ua.epam.dao.sevices.EventService;
import ua.epam.dao.sevices.ReportService;
import ua.epam.dao.sevices.SubscriptionsService;
import ua.epam.dao.sevices.UserService;
import ua.epam.services.IStreamEventPool;
import ua.epam.services.implementations.StreamEventPool;
import ua.epam.services.implementations.SuggestReportPool;

import java.util.Properties;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public IUserDao iUserDao() {
        return new UserDao();
    }

    @Bean
    public ReportService reportService() {
        return new ReportService();
    }

    @Bean
    public IReportDao iReportDao() {
        return new ReportDao();
    }

    @Bean
    public EventService eventService() {
        return new EventService();
    }

    @Bean
    public IEventDao iEventDao() {
        return new EventDao();
    }

    @Bean
    public SubscriptionsService subscriptionsService() {
        return new SubscriptionsService();
    }

    @Bean
    public ISubscribesDao subscribesDao() {
        return new SubscribesDao();
    }

    @Bean
    public IStreamEventPool eventPool() {
        return new StreamEventPool();
    }

    @Bean
    public SuggestReportPool reportPool() {
        return SuggestReportPool.getEntity();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("vanntno@gmail.com");
        sender.setPassword("vsvwdkrekostmtfm");
        Properties pop = sender.getJavaMailProperties();
        pop.put("mail.transport.protocol", "smtp");
        pop.put("mail.smtp.auth", "true");
        pop.put("mail.smtp.starttls.enable", "true");
        pop.put("mail.debug", "true");
        return sender;
    }

}
