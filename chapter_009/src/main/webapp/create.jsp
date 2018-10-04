<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Create user</title>
    </head>
    <body>
    <form method='post' action='/chapter_009/create'>
        Name:<input type='input' name='name' value=''/>
        </br>
        Login:<input type='input' name='login' value=''/>
        </br>
        Password:<input type='input' name='pass' value=''/>
        </br>
        Email:<input type='input' name='email' value=''/>
        </br>
        Role<select name="role">
            <c:forEach items="${requestScope['roles']}"  var="rol">
                <option value="${rol}">${rol}</option>
            </c:forEach>
        </select>
        </br>
        <input type='reset' name='but1' value='Reset'/>
        <input type='submit' name='but2' value='Save'/>
    </form>
    </body>
</html>
