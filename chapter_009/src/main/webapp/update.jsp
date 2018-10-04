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
            Password:<input type='input' name='pass' value='${usr.pass}'/>
            </br>
            Email:<input type='input' name='email' value='${usr.email}'/>
            </br>
            <c:choose>
                <c:when test="${sessionScope.fuser.role_id == 9999}">
                    Role<select name="role">
                        <c:forEach items="${requestScope['roles']}"  var="rol">
                            <option value="${rol}" <c:if test = "${lor eq rol}"> selected="selected" </c:if> >${rol}</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    Role: <c:out value='${requestScope.lor}' />
                    <input type='hidden' name='role' value='${requestScope.lor}'/>
                </c:otherwise>
            </c:choose>
            </br>
            <input type='hidden' name='res' value='${usr.res}'/>
            <input type='reset' name='but1' value='Reset'/>
            <input type='submit' name='but2' value='Save'/>
        </form>
    </body>
</html>
