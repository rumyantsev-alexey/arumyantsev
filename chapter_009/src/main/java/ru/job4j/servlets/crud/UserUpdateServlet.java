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
        if (req.getParameter("id") != null) {
            req.setAttribute("find", vserv.findById(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("roles", vserv.findAllRoles());
            req.setAttribute("lor", vserv.roleByRoleId(vserv.findById(Integer.parseInt(req.getParameter("id"))).getRoleid()));
            req.setAttribute("city", vserv.cityByCityId(vserv.findById(Integer.parseInt(req.getParameter("id"))).getCityid()));
            req.setAttribute("country", vserv.countryByCityid(vserv.findById(Integer.parseInt(req.getParameter("id"))).getCityid()));
            req.setAttribute("countres", vserv.findAllCountry());
            req.setAttribute("cites", vserv.findAllCityByCountry((String) req.getAttribute("country")));
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("list");
        }
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
        usr.setPass(req.getParameter("pass"));
        usr.setEmail(req.getParameter("email"));
        usr.setRoleid(vserv.roleidByRole(req.getParameter("role")));
        usr.setRes(Timestamp.valueOf(req.getParameter("res")));
        usr.setCityid(vserv.cityidByCity(req.getParameter("cname")));
        vserv.updateByUser(usr);
        if (((User) req.getSession().getAttribute("fuser")).getLogin().equals(usr.getLogin())) {
            req.getSession().setAttribute("fuser", usr);
        }
        req.getRequestDispatcher("list").forward(req, resp);
    }
}
