<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:forEach items="${requestScope['usrs']}"  var="usr">
                <tr>
                    ${usr}
                    <td>
                        <form method='get' action='/chapter_009/edit'>
                            <input type='hidden' name='action' value='update'>
                            <input type='hidden' name='id' value='${usr.id}'/>
                            <input type='submit' value='Edit' />
                        </form>
                    </td>
                    <td>
                        <form method='post' action='/chapter_009/list'>
                            <input type='hidden' name='action' value='delete'>
                            <input type='hidden' name='id' value='${usr.id}'/>
                            <input type='submit' value='Delete'/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </br>
        <form method='get' action='/chapter_009/create'>
            <input type='submit' value='New user' />
        </form>
    </body>
</html>
