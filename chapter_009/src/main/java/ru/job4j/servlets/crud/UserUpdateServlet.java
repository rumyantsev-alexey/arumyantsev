package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 *
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService vserv = ValidateService.getInstance();

    /**
     * Метод реализует обработку метода Get для страницы редактирования записи
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    /**
     * Метод реализует обработку метода Post для страницы редактирования записи
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User usr = new User();
        usr.setId(Integer.parseInt(req.getParameter("id")));
        usr.setName(req.getParameter("name"));
        usr.setLogin(req.getParameter("login"));
        usr.setEmail(req.getParameter("email"));
        usr.setRes(Timestamp.valueOf(req.getParameter("res")));
        vserv.updateByUser(usr);
        resp.sendRedirect("/chapter_009/list");
    }
}
