package ru.job4j.servlets.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс ркализующий аутенфикационный фильтр
 */
public class AuthFilter  implements Filter {
    private final ValidateService vserv = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession ses = req.getSession();
        User user = (User) ses.getAttribute("fuser");
        if (user == null || vserv.checkLogin(user.getLogin(), user.getPass()) < 1 || req.getRequestURI().contains("login")) {
            req.getRequestDispatcher("login").forward(req, response);
        }
        filterChain.doFilter(request, response);
    }
}
