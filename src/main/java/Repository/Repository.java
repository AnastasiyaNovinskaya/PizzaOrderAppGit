package Repository;

import Models.IngredientType;
import Models.PizzaType;
import Util.DBConfiguration;

import java.sql.*;
import java.util.ArrayList;


public class Repository {
    Connection connection;

    public Repository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBConfiguration.url, DBConfiguration.user, DBConfiguration.password);

        } catch (SQLException | ClassNotFoundException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
    }
    public boolean validateUser(String username, String password) {

        String getUsers = "SELECT * FROM pizza.Users WHERE username=? AND password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getUsers);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (Exception exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return false;
    }

    public ArrayList<PizzaType> getPizza() {

        String getPizza = "SELECT * FROM pizza.pizzatype";
        ArrayList<PizzaType> pizzaArray = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getPizza);
            while (resultSet.next()) {

                PizzaType pizzaType = new PizzaType();
                pizzaType.setId(resultSet.getInt("ID"));
                pizzaType.setName(resultSet.getString("PizzaType"));
                pizzaType.setPrice(resultSet.getDouble("Price"));
                pizzaArray.add(pizzaType);


                System.out.println(resultSet.getInt("ID") + ": " +
                        resultSet.getString("PizzaType") +
                        ", price: " + resultSet.getDouble("Price") + "$");
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return pizzaArray;
    }

    public ArrayList<IngredientType> getIngredients() {

        String getIngredients = "SELECT * FROM pizza.ingredients";
        ArrayList<IngredientType> ingredientArray = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getIngredients);
            while (resultSet.next()) {

                IngredientType ingredientType = new IngredientType();
                ingredientType.setId(resultSet.getInt("ID"));
                ingredientType.setName(resultSet.getString("Ingredient"));
                ingredientType.setPrice(resultSet.getDouble("Price"));
                ingredientArray.add(ingredientType);

                System.out.println(resultSet.getInt("ID") +
                        ": " + resultSet.getString("Ingredient") +
                        ", price: " + resultSet.getDouble("Price") + "$");
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return ingredientArray;
    }
}
