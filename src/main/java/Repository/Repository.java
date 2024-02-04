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

    public void sendRegistration(String username, String password) {
        String sendRegistration = "INSERT INTO pizza.users (username, password) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sendRegistration);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            preparedStatement.executeUpdate();

        } catch (Exception exception) {
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
}

