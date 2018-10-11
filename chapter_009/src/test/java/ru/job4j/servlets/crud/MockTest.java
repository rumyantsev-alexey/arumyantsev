package ru.job4j.servlets.crud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
/**
 * Класс реализует тесты сервлетов с помощью mocks
 */
public class MockTest {
    ValidateService validate = new ValidateStub();
    HttpServletRequestSub req = new HttpServletRequestSub(mock(HttpServletRequest.class));
    HttpServletResponse resp = mock(HttpServletResponse.class);

    @Before
    public void initServletTest() {
        validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
    }

    /**
     * Тестируется сервлет создания пользователя
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        when(req.getParameter("name")).thenReturn("Petr Ivanov");
        when(req.getParameter("login")).thenReturn("Petr");
        when(req.getParameter("pass")).thenReturn("1");
        when(req.getParameter("email")).thenReturn("Petr@bnb.ru");
        when(req.getParameter("role")).thenReturn("Admin");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Petr Ivanov"));
    }

    /**
     * Тестируется сервлет редактирования пользователя
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenEditUserThenStoreIt() throws ServletException, IOException {
        when(req.getParameter("name")).thenReturn("Petr Ivanov");
        when(req.getParameter("login")).thenReturn("Petr");
        when(req.getParameter("pass")).thenReturn("1");
        when(req.getParameter("email")).thenReturn("Petr@bnb.ru");
        when(req.getParameter("role")).thenReturn("Admin");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getEmail(), is("Petr@bnb.ru"));
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("res")).thenReturn(new Timestamp(System.currentTimeMillis()).toString());
        when(req.getParameter("email")).thenReturn("Petr@www.ru");
        new UserUpdateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getEmail(), is("Petr@www.ru"));
    }

    /**
     * Тестируется сервлет вывода списка пользователей а именно функция удвления записи
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenDeleteUserThenDeleteIt() throws ServletException, IOException {
        when(req.getParameter("name")).thenReturn("Petr Ivanov");
        when(req.getParameter("login")).thenReturn("Petr");
        when(req.getParameter("pass")).thenReturn("1");
        when(req.getParameter("email")).thenReturn("Petr@bnb.ru");
        when(req.getParameter("role")).thenReturn("Admin");
        new UserCreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Petr Ivanov"));
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("1");
        UserServlet us = new UserServlet();
        us.init();
        us.doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }

}
