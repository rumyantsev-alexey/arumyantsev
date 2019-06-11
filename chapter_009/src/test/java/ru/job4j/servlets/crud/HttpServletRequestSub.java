package ru.job4j.servlets.crud;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Класс реализует заглушку части системного функционала в сервлетах (для тестов)
 */
public class HttpServletRequestSub extends HttpServletRequestWrapper {

    private class RequestDispatcherSub implements RequestDispatcher {

        @Override
        public void forward(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        }

        @Override
        public void include(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        }
    }

    private class HttpSessionSub implements HttpSession {
        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int i) {

        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            User user = new User();
            user.setLogin("yirqpqrp");
            return user;
        }

        @Override
        public Object getValue(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void putValue(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public void removeValue(String s) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    }

    public HttpServletRequestSub(HttpServletRequest request) {
        super(request);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return new RequestDispatcherSub();
    }

    @Override
    public HttpSession getSession() {
        return new HttpSessionSub();
    }
}
