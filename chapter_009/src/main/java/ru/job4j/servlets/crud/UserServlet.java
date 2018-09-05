package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.BiPredicate;

/**
 * Класс реализующий сервлет обрабатывающий страницу со списоком записей
 */
public class UserServlet extends HttpServlet {
    private final AntiSwitch asw = new AntiSwitch();
    private final ValidateService vserv = ValidateService.getInstance();

    /**
     * Метод инициализирующий меню выбора и генерирующий данные для примера
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        asw.load("add", this.add());
        asw.load("update", this.update());
        asw.load("delete", this.delete());
        asw.load("--", this.non());
        vserv.generate(10);
    }

    /**
     * Метод реализует обработку метода Get для стрвницы со списком записей
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<User> ulist = new ArrayList<>();
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(  "<head>" +
                            "<title>User's list</title>" +
                        "</head>"+
                            "<table border = '1'>"+
                        "<body>");
        ulist = vserv.findAll();
        for(User u: ulist) {
            writer.append("<tr>");
            writer.append(u.toString());
            writer.append("<td>" +
                    "<form method='get' action='/chapter_009/edit'>" +
                    "<input type='hidden' name='id' value='" + u.getId() + "'/>" +
                    "<input type='submit' value='Edit' />" +
                    "</form>" +
                    "</td>");
            writer.append("<td>" +
                    "<form method='post' action='/chapter_009/list'>" +
                    "<input type='hidden' name='action' value='delete'>" +
                    "<input type='hidden' name='id' value='" + u.getId() + "'/>" +
                    "<input type='submit' name='but2' value='Delete'/>" +
                    "</form>" +
                    "</td>" +
                    "</tr>");
            writer.flush();
        }
        writer.append("</br>" +
                "<form method='get' action='/chapter_009/create'>" +
                "<input type='submit' value='New user' />" +
                "</form>" +
                "</body>");
        writer.flush();
    }

    /**
     *Метод реализует обработку метода Post для страницы со списком записей
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String action = req.getParameter("action") == null? "--" : req.getParameter("action");
        String name = req.getParameter("name") == null? "--" : req.getParameter("name");
        int id = req.getParameter("id") == null? -1 : Integer.parseInt(req.getParameter("id"));
        writer.append("Result operation " + action + ": " + asw.run(action, id, name) + "</br>");
        writer.append(  "<form method='get' action='/chapter_009/list'>" +
                            "<input type='submit' value='Back' />" +
                        "</form>");
        writer.flush();
    }

    /**
     * Методы реализующие соответствующий функционал для различных действийсвий метода Post
     * @return
     */
    private BiPredicate<Integer, String> add() {
        return (integer, s) -> vserv.add(s);
    }
    private BiPredicate<Integer, String> update() {
        return (integer, s) -> vserv.update(integer, s);
    }

    private BiPredicate<Integer, String> delete() {
        return (integer, s) -> vserv.delete(integer);
    }

    private BiPredicate<Integer, String> non() {
        return (integer, s) -> true;
    }
}
