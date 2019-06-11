package ru.job4j.servlets.crud;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * Сервлет реализует обработку пост запроса и его сохранение в базе
 */
public class UServlet extends HttpServlet {

    static class Answ {
        public String type;
        public String data;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * Метод реализует обработку пост и реализует необходимый функционал в зависимости от ajax
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        Answ ans = mapper.readValue(jsonData, Answ.class);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        switch (ans.type) {
            case ("city"):
                out.print(ValidateService.getInstance().cityByCityId(Integer.parseInt(ans.data)));
                break;
            case ("role"):
                out.print(ValidateService.getInstance().roleByRoleId(Integer.parseInt(ans.data)));
                break;
            case ("selectcity"):
                out.print("<label class=\"control-label col-sm-2\" for=\"country\">Cites</label>");
                out.print("<div class=\"col-sm-10\">");
                out.print("<select id=\"country\" class=\"form-control\" onchange=\"printCites();\">");
                for (String cit: ValidateService.getInstance().findAllCityByCountry(ans.data)) {
                    out.print("<option value=\"" + cit + "\">" + cit + "</option>");
                }
                out.print("</select>");
                out.print("</div>");
                break;
            default: break;
        }
    }
}
