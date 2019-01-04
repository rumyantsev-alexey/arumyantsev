package ru.job4j.todo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ToDoServlet extends HttpServlet{
    private static final Store<ToDo> store = new DBStore();
    private static final AntiSwitch asw = new AntiSwitch();

    /**
     * Метод инициализирующий меню выбора
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        asw.load("loadList", this.loadList());
        asw.load("newDo", this.newDo());
        asw.load("updateList", this.updateList());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/todo/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonData);
        asw.run(actualObj.get("type").textValue(), out, actualObj);
    }

    @Override
    public void destroy() {
        HibernateSessionFactory.shutdown();
        super.destroy();
    }

    private BiConsumer<PrintWriter, JsonNode> loadList() {
        return (out, json) ->   {
                                    ObjectMapper mapper = new ObjectMapper();
                                    try {
                                        mapper.writeValue(out, store.findAll());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                };
    }

    private BiConsumer<PrintWriter, JsonNode> newDo() {
        return (out, json) ->   {   store.add(new ToDo(json.get("text").textValue()));
                                    out.print("Новая задача записана");
                                };
    }

    private BiConsumer<PrintWriter, JsonNode> updateList() {
        return (out, json) ->   {
                                    ToDo curr;
                                    for(JsonNode chg : json.get("chgId")) {
                                        curr = store.findById(chg.asInt());
                                        curr.setDone(!curr.getDone());
                                        store.update(curr);
                                        out.print("Изменения записаны");
                                    }
                                };
    }
}
