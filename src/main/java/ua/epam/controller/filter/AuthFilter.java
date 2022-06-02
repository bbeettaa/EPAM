package ua.epam.controller.filter;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.dao.UserRepo;
import ua.epam.models.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.isNull;

public class AuthFilter implements Filter {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LANGUAGE = "language";
    public static final String ACTION = "action";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Save login and password to session if user is logs in
     * <p>
     * Add action name to request.
     * If action is null set login command to action
     */
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = ((HttpServletRequest) request).getSession();

        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String language = req.getParameter(LANGUAGE);

        if (isNull(login) || isNull(password)) {
            login = String.valueOf(session.getAttribute(LOGIN));
            password = String.valueOf(session.getAttribute(PASSWORD));
            language = String.valueOf(session.getAttribute(LANGUAGE));
        }

        final AtomicReference<UserRepo> dao = AppContext.USER_REPO;
        try {
            //log in user
            if (dao.get().userIsExist(login, password)) {
                final Role role = dao.get().get(login, password).getRole();

                req.getSession().setAttribute(PASSWORD, password);
                req.getSession().setAttribute(LOGIN, login);
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute(LANGUAGE, language);
                req.setAttribute(ACTION, (isNull(req.getParameter(ACTION))) ? LOGIN : req.getParameter(ACTION));
            } else {
                req.removeAttribute(ACTION);
                req.setAttribute(ACTION, LOGIN);
            }

            chain.doFilter(req, res);

        } catch (Exception e) {
            AppContext.LOGGER.error(e.getMessage());
            request.getRequestDispatcher(ViewPath.ERROR_PAGE).forward(request, response);
        }

    }

    @Override
    public void destroy() {
    }

}
