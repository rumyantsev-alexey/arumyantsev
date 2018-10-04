package ru.job4j.servlets.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        req.setAttribute("usrs", vserv.findAll());
        try {
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (IllegalStateException e) {

        }
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
        String action = req.getParameter("action") == null? "--" : req.getParameter("action");
        String name = req.getParameter("name") == null? "--" : req.getParameter("name");
        int id = req.getParameter("id") == null? -1 : Integer.parseInt(req.getParameter("id"));
        try {
            if (action.equals("logout")) {
                req.getSession().invalidate();
                resp.sendRedirect("login");
            } else {
                asw.run(action, id, name);
            }
            doGet(req, resp);
        } catch (IllegalStateException e) {

        }
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
