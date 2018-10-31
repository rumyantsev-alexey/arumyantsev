package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс реализующий сервлет для создания записи
 */
public class UserCreateServlet extends HttpServlet {
    private final ValidateService vserv = ValidateService.getInstance();

    /**
     * Метод реализует обработку метода Get для страницы создания записи
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", vserv.findAllRoles());
        req.setAttribute("countres", vserv.findAllCountry());
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    /**
     * Метод реализует обработку метода Post для страницы создания записи
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vserv.addFull(req.getParameter("name"), req.getParameter("login"), req.getParameter("pass"),
            req.getParameter("email"), vserv.roleidByRole(req.getParameter("role")),
                vserv.cityidByCity(req.getParameter("cname")));
        req.getRequestDispatcher("list").forward(req, resp);
    }
}
