<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizza Order</title>
    <style>
        body {
            margin: 0;
            overflow: hidden;
            display: flex;
            align-items: center; /* Центрирование по вертикали */
            justify-content: center; /* Центрирование по горизонтали */
            height: 100vh; /* 100% высоты окна браузера */
        }
    </style>
</head>
<body>
<img src="pizza.jpg" alt="Pizza Image">

<form action="login" method="post">
    <h4>Please Sign up</h4>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>