package ru.job4j.cars;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class LoginFilter  implements Filter {

    /**
     * Фильтр проверяет сформинрован ли пользователь в сесии
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession ses = req.getSession();
        String login = (String) req.getParameter("login");
        UsersEntity user = (UsersEntity) ses.getAttribute("loginUser");
        if (user == null && login == null) {
            req.getRequestDispatcher("/cars/login").forward(req, (HttpServletResponse) servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
