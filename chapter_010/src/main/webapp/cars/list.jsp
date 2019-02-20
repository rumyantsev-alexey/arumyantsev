<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Main page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Cache-Control" content="no-cache">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>

    <script>

        function goAdd() {
            window.location.href = '/cars/add?new';
        }

        jQuery(document).ready(function($) {
            $(".clickable-row").click(function() {
                window.location = $(this).data("href");
            });
        });

    </script>

    Current user:${sessionScope.loginUser.name}
    <form method='post' action='/cars/login'>
        <input type="hidden" name='login' value=''/>
        <input type="hidden" name='pass' value=''/>
        <input type='submit' class="btn btn-default" name='but3' value='Logout'/>
    </form>
    <c:if test="${sessionScope.loginUser.name != 'anonymus'}">
        <form method='get' action='/cars/add'>
           <input type='button' class="btn btn-default" name='but2' value='Add new ad' onclick="goAdd();" />
        </form>
    </c:if>

    <table class="table table-hover" id="List">
        <thead>
        <tr>
            <th>Id</th>
            <th>Model</th>
            <th>Issue</th>
            <th>Price</th>
            <th>Created</th>
            <th>Author</th>
            <th>Actual</th>
            <th>Foto</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.carlist}"  var="clist">
                <tr class='clickable-row' data-href='/cars/ad?id=${clist.id}'>
                    <td>
                        ${clist.id}
                    </td>
                    <td>
                            ${clist.model.name}
                    </td>
                    <td>
                            ${clist.issue}
                    </td>
                    <td>
                            ${clist.price}
                    </td>
                    <td>
                            ${clist.created}
                    </td>
                    <td>
                            ${clist.user.name}
                    </td>
                    <td>
                            ${!clist.old}
                    </td>
                    <td>
                        Count : ${fn:length(clist.fotos)}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    </body>
</html>
