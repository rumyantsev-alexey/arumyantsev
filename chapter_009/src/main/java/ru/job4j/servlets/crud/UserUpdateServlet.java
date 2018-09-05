package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        int id = req.getParameter("id") == null? -1 : Integer.parseInt(req.getParameter("id"));
        User usr = vserv.findById(id);
        writer.append("<form method='post' action='/chapter_009/edit'>" +
                            "<input type='hidden' name='id' value='" + usr.getId() + "'/>" +
                            "Name:<input type='input' name='name' value='" + usr.getName() + "'/></br>" +
                            "Login:<input type='input' name='login' value='" + usr.getLogin() + "'/></br>" +
                            "Email:<input type='input' name='email' value='" + usr.getEmail() + "'/></br>" +
                            "<input type='hidden' name='res' value='" + usr.getRes() + "'/>" +
                            "<input type='reset' name='but1' value='Reset'/>" +
                            "<input type='submit' name='but2' value='Save'/>" +
                        "</form>");
        writer.flush();
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        usr.setId(Integer.parseInt(req.getParameter("id")));
        usr.setName(req.getParameter("name"));
        usr.setLogin(req.getParameter("login"));
        usr.setEmail(req.getParameter("email"));
        usr.setRes(Timestamp.valueOf(req.getParameter("res")));
        writer.append("edit post</br>");
        writer.append(  "<form method='get' action='/chapter_009/list'>" +
                            "<input type='submit' value='Back' />" +
                        "</form>");
        vserv.updateByUser(usr);
        writer.flush();
    }
}
