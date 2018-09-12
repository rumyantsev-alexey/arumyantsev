<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Update user</title>
    </head>
    <body>
    <c:set var="usr" value="${requestScope['find']}"/>
        <form method='post' action='/chapter_009/edit'>
            <input type='hidden' name='id' value='${usr.id}'/>
            Name:<input type='input' name='name' value='${usr.name}'/>
            </br>
            Login:<input type='input' name='login' value='${usr.login}'/>
            </br>
            Email:<input type='input' name='email' value='${usr.email}'/>
            </br>
            <input type='hidden' name='res' value='${usr.res}'/>
            <input type='reset' name='but1' value='Reset'/>
            <input type='submit' name='but2' value='Save'/>
        </form>
    </body>
</html>
