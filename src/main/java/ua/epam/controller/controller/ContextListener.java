package ua.epam.controller.controller;

import ua.epam.AppContext;
import ua.epam.dao.UserRepo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.atomic.AtomicReference;
//import java.util.logging.*;


public class ContextListener implements ServletContextListener {
    private AtomicReference<UserRepo> dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        dao = AppContext.USER_REPO;

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dao);
        AppContext.LOGGER.info("Context listener start working");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dao = null;
    }

}
