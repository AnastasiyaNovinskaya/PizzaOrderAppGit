<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.PizzaType" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
</head>
<body>
<h2>Select Pizza</h2>
<form action="placeOrder" method="post">
<%
    ArrayList<PizzaType> pizzas = (ArrayList<PizzaType>) session.getAttribute("pizzas");
    if (pizzas != null && !pizzas.isEmpty()) {
        for (PizzaType pizza : pizzas) {
%>
<label>
    <input type="radio" name="selectedPizza" value="<%= pizza.getId() %>">
    <%= pizza.getName() %> - <%= pizza.getPrice() + " $"%>
</label>
<br><br>
<%
    }
} else {
%>
<p>No pizzas available.</p>
<%
    }
%>

<input type="submit" value="Place Order">
</form>
</body>
</html>