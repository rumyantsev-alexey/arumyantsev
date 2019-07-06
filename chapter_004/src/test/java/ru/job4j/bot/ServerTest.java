package ru.job4j.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {

    @Test
    public void whenIncomeExitThenOracleSayGoodByeTest() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(("выход" + System.lineSeparator()).getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.run();
        StringBuilder bb = new StringBuilder().append("Великий Оракул прощается с тобой").append(System.lineSeparator()).append(System.lineSeparator());
        assertEquals(bb.toString(), out.toString());
    }

}