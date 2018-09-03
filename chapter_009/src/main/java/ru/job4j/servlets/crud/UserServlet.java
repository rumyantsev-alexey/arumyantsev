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
 * Класс реализующий слой Presentation
 */
public class UserServlet extends HttpServlet {
    private AntiSwitch asw = new AntiSwitch();
    ValidateService vserv = ValidateService.getInstance();

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
     * Метод реализует обработку метода Get
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
        writer.print("User's list:<br>");
        ulist = vserv.findAll();
        for(User u: ulist){
            writer.println(u+"<br>");
        }
        writer.flush();
    }

    /**
     *Метод реализует обработку метода Post
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action;
        String name;
        int id;
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        action = req.getParameter("action") == null? "--" : req.getParameter("action");
        name = req.getParameter("name") == null? "--" : req.getParameter("name");
        id = req.getParameter("id") == null? 0 : Integer.parseInt(req.getParameter("id"));
        writer.print("Result operation " + action + ": " + asw.run(action, id, name));
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
