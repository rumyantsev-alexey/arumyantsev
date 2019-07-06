package ru.job4j.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Test
    public void whenEnterCommandThenSendThisCommandToServerTest() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(System.lineSeparator().getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ByteArrayInputStream input = new ByteArrayInputStream("выход".getBytes());
        Client cln = new Client(socket, input);
        cln.run();
        assertEquals(("выход" + System.lineSeparator()), out.toString());

    }

}