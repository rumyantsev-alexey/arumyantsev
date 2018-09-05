package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        int id = req.getParameter("id") == null? -1 : Integer.parseInt(req.getParameter("id"));
        User usr = vserv.findById(id);
        writer.append("<form method='post' action='/chapter_009/create'>" +
                "Name:<input type='input' name='name' value=''/></br>" +
                "Login:<input type='input' name='login' value=''/></br>" +
                "Email:<input type='input' name='email' value=''/></br>" +
                "<input type='reset' name='but1' value='Reset'/>" +
                "<input type='submit' name='but2' value='Save'/>" +
                "</form>");
        writer.flush();
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("create post</br>");
        writer.append(  "<form method='get' action='/chapter_009/list'>" +
                "<input type='submit' value='Back' />" +
                "</form>");
        vserv.addFull(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        writer.flush();
    }
}
