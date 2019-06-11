package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс реализует сервлет отвечающий за аутентификацию
 */
public class Login extends HttpServlet {
    private final ValidateService vserv = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (IllegalStateException e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (vserv.checkLogin(req.getParameter("login"), req.getParameter("pass")) > 0) {
            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPass(req.getParameter("pass"));
            user.setRoleid(vserv.checkLogin(req.getParameter("login"), req.getParameter("pass")));
            req.getSession().setAttribute("fuser", user);
            try {
                resp.sendRedirect("list");
            } catch (IllegalStateException e) {

            }
        } else {
            doGet(req, resp);
        }
    }
}
