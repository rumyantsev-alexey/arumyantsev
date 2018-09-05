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
        Email:<input type='input' name='email' value=''/>
        </br>
        <input type='reset' name='but1' value='Reset'/>
        <input type='submit' name='but2' value='Save'/>
    </form>
    </body>
</html>
