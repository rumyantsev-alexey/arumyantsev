<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Users list </title>
    </head>
    <body>
        User: <c:out value='${sessionScope.fuser.login}' />
        <form method='get' action='/chapter_009/login'>
            <input type='hidden' name='action' value='logout'>
            <input type='submit' value='Logout' />
        </form>
        <table border = '1'>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Role_id</th>
                <th>Date creation</th>
            </tr>
            <c:forEach items="${requestScope['usrs']}"  var="usr">
                <tr>
                    <td>${usr.id}</td>
                    <td>${usr.name}</td>
                    <td>${usr.login}</td>
                    <td>${usr.email}</td>
                    <td>${usr.role_id}</td>
                    <td>${usr.res}</td>
                    <td>
                        <c:if test = "${sessionScope.fuser.role_id == ADMIN_ID or sessionScope.fuser.login == usr.login}">
                            <form method='get' action='/chapter_009/edit'>
                                <input type='hidden' name='action' value='update'>
                                <input type='hidden' name='id' value='${usr.id}'/>
                                <input type='submit' value='Edit' />
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:if test = "${sessionScope.fuser.role_id == ADMIN_ID}">
                            <form method='post' action='/chapter_009/list'>
                                <input type='hidden' name='action' value='delete'>
                                <input type='hidden' name='id' value='${usr.id}'/>
                                <input type='submit' value='Delete'/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </br>
            <c:if test = "${sessionScope.fuser.role_id == ADMIN_ID}">
               <form method='get' action='/chapter_009/create'>
                    <input type='submit' value='New user' />
                </form>
            </c:if>
    </body>
</html>
