package ru.job4j.servlets.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс реализующий авторизационный фильтр
 */
public class AvtFilter implements Filter {
    private final ValidateService vserv = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession ses = req.getSession();
        User user = (User) ses.getAttribute("fuser");
        if (user != null && vserv.access(user.getRole_id(), req.getRequestURI())) {
            filterChain.doFilter(request, response);
            // Set standard HTTP/1.1 no-cache headers.
            ((HttpServletResponse) response ).setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        } else {
            try {
                ((HttpServletResponse) response ).sendError(403, req.getRequestURI());
            } catch (IllegalStateException e) {

            }
        }
    }

}
