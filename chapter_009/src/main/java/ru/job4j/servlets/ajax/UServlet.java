package ru.job4j.servlets.ajax;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Сервлет реализует обработку пост запроса и его сохранение в базе
 */
public class UServlet extends HttpServlet {
    ConcurrentHashMap <Integer, User> data = new ConcurrentHashMap<>();
    volatile int key = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonUser;
        User usr = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        jsonUser = req.getReader().lines().collect(Collectors.joining());
        usr = objectMapper.readValue(jsonUser, User.class);
        data.put(key++, usr);
    }
}
