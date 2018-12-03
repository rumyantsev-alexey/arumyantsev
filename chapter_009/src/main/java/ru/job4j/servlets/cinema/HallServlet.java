package ru.job4j.servlets.cinema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Класс реализует сервлет, который реализует часть controller моделе MVC
 */
public class HallServlet extends HttpServlet {
    private static final DBcinema db = DBcinema.getInstance();
    private final AntiSwitch asw = new AntiSwitch();

    /**
     * Метод инициализирующий меню выбора
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        asw.load("getZone", this.getZone());
        asw.load("getSession", this.getSession());
        asw.load("getBusy", this.getBusy());
        asw.load("getCost", this.getCost());
        asw.load("saleZakaz", this.saleZakaz());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonData = req.getReader().lines().collect(Collectors.joining());
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonData);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding( "UTF-8" );
        asw.run(actualObj.get("type").textValue(), out, actualObj);
    }

    private void writeObjectToWriter(PrintWriter out, Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(out, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BiConsumer<PrintWriter, JsonNode> getZone() {
        return (out, json) -> { writeObjectToWriter(out, db.findAllZones()); };
    }

    private BiConsumer<PrintWriter, JsonNode> getSession() {
        return (out, json) -> { writeObjectToWriter(out, db.findAllSessions()); };
    }

    private BiConsumer<PrintWriter, JsonNode> getBusy() {
        return (out, json) -> { writeObjectToWriter(out, db.returnBusySeats(json.get("calendar").textValue(), json.get("zone").textValue(),
                json.get("session").textValue())); };
    }

    private BiConsumer<PrintWriter, JsonNode> getCost() {
        return (out, json) -> { writeObjectToWriter(out, db.costPerRow(json.get("zone").textValue(), json.get("session").textValue())); };
    }

    private BiConsumer<PrintWriter, JsonNode> saleZakaz() {
        return (out, json) -> {
            String result = "Payment failed!!!";
            if (db.addClient(json.get("name").textValue(), json.get("phone").textValue()) && db.addBusyp(json.get("data").get("calendar").textValue(),
                    json.get("data").get("seats"),
                    db.colByName(json.get("data").get("session").textValue(), "session", "id"),
                    db.colByName(json.get("data").get("zone").textValue(), "zone", "id"),
                    db.colByName(json.get("name").textValue(), "clients", "id"))){
                result= "Payment has passed";
            }
            out.print(result);
        };
    }

}
