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
import java.util.stream.Collectors;

/**
 * Класс реализует сервлет, который реализует часть controller моделе MVC
 */
public class HallServlet extends HttpServlet {
    DBcinema db = DBcinema.getInstance();

    /**
     * метод реализует первичное заполнение БД при загрузке сервлета
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        Random rnd = new Random();
        db.addZone("Green", 15,20);
        db.addZone("Blue", 12,18);
        db.addZone("Red", 5,10);
        db.addSession("Morning", "11-00");
        db.addSession("Afternoon", "15-00");
        db.addSession("Evening", "21-00");
        int rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 16; i++) {
            db.addCostp(i,rr,1,1);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 13; i++) {
            db.addCostp(i,rr,1,2);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 6; i++) {
            db.addCostp(i,rr,1,3);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 16; i++) {
            db.addCostp(i,rr,2,1);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 13; i++) {
            db.addCostp(i,rr,2,2);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 6; i++) {
            db.addCostp(i,rr,2,3);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 16; i++) {
            db.addCostp(i,rr,3,1);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 13; i++) {
            db.addCostp(i,rr,3,2);
        }
        rr = 10 + rnd.nextInt(100);
        for(int i=1; i < 6; i++) {
            db.addCostp(i,rr,3,3);
        }
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
        switch (actualObj.get("type").textValue()) {
            case ("getZone"):
                mapper.writeValue(out, db.findAllZones());
                break;
            case ("getSession"):
                mapper.writeValue(out, db.findAllSessions());
                break;
            case("getBusy"):
                mapper.writeValue(out, db.returnBusySeats(actualObj.get("calendar").textValue(), actualObj.get("zone").textValue(),
                        actualObj.get("session").textValue()));
                break;
            case("getCost"):
                mapper.writeValue(out, db.costPerRow(actualObj.get("zone").textValue(), actualObj.get("session").textValue()));
                break;
            case("saleZakaz"):
                String result = "Payment failed!!!";
                String name = actualObj.get("name").textValue();
                String phone = actualObj.get("phone").textValue();
                String calendar = actualObj.get("data").get("calendar").textValue();
                String session = actualObj.get("data").get("session").textValue();
                String zone = actualObj.get("data").get("zone").textValue();

                if (db.addClient(name, phone) && db.addBusyp(calendar, actualObj.get("data").get("seats"), db.colByName(session, "session", "id"),
                        db.colByName(zone, "zone", "id"), db.colByName(name, "clients", "id"))){
                    result= "Payment has passed";
                }
                out.print(result);
                break;
        }
    }
}
