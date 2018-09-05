<%@ page import="ru.job4j.servlets.crud.ValidateService" %>
<%@ page import="ru.job4j.servlets.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Update user</title>
    </head>
    <body>
    <%User usr = ValidateService.getInstance().findById(Integer.parseInt(request.getParameter("id")));%>
        <form method='post' action='/chapter_009/edit'>
            <input type='hidden' name='id' value='<%=usr.getId()%>'/>
            Name:<input type='input' name='name' value='<%=usr.getName()%>'/>
            </br>
            Login:<input type='input' name='login' value='<%=usr.getLogin()%>'/>
            </br>
            Email:<input type='input' name='email' value='<%=usr.getEmail()%>'/>
            </br>
            <input type='hidden' name='res' value='<%=usr.getRes()%>'/>
            <input type='reset' name='but1' value='Reset'/>
            <input type='submit' name='but2' value='Save'/>
        </form>
    </body>
</html>
