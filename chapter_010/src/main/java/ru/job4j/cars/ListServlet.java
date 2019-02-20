package ru.job4j.cars;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Сервлет работате со списком объявдений
 */
public class ListServlet extends HttpServlet {

    /**
     * Метод получает список объявлений и отображает их
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbStore<CarEntity> cdb = new DbStore<>(CarEntity.class);
        ArrayList<CarEntity> carlist = cdb.findAll();
        req.setAttribute("carlist", carlist);
        req.getRequestDispatcher("/cars/list.jsp").forward(req, resp);
    }

    /**
     * Метод получает список моделей по марке автомобиля (на странице добавления объявления)
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
        JsonNode actualObj = mapper.readTree(jsonData);
        String mark = actualObj.get("mark").textValue();
        DbStore<ModelEntity> db = new DbStore<>(ModelEntity.class);
        ArrayList<ModelEntity> mlist = db.findAll();
        ArrayList<String> result = new ArrayList<>();
        for (ModelEntity mdl: mlist) {
            if (mark.equals(mdl.getMark().getName()))  {
                result.add(mdl.getName());
            }
        }
        mapper.writeValue(out, result);
    }
}
