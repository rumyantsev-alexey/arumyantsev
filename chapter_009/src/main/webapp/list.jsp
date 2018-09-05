<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Users list</title>
    </head>
    <body>
        <table border = '1'>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Date creation</th>
            </tr>
            <%for(User u: ValidateService.getInstance().findAll()) {%>
            <tr>
                <%=u.toString()%>
                <td>
                    <form method='get' action='/chapter_009/edit'>
                        <input type='hidden' name='action' value='update'>
                        <input type='hidden' name='id' value='<%=u.getId()%>'/>
                        <input type='submit' value='Edit' />
                    </form>
                </td>
                <td>
                    <form method='post' action='/chapter_009/list'>
                        <input type='hidden' name='action' value='delete'>
                        <input type='hidden' name='id' value='<%=u.getId()%>'/>
                        <input type='submit' value='Delete'/>
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
        </br>
        <form method='get' action='/chapter_009/create'>
            <input type='submit' value='New user' />
        </form>
    </body>
</html>
