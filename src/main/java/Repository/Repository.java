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
                pizzaType.setDescription(resultSet.getString("PizzaDescription"));
                pizzaType.setPrice(resultSet.getDouble("Price"));
                pizzaType.setImageUrl(resultSet.getString("ImageUrl"));
                pizzaArray.add(pizzaType);


                //System.out.println(resultSet.getInt("ID") + ": " +
                  //      resultSet.getString("PizzaType") +
                    //    ", price: " + resultSet.getDouble("Price") + "$");
            }
        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
        return pizzaArray;
    }


    public void addOrder(String pizzaName, int quantity) {
        String sendOrder = "INSERT INTO pizza.allOrders (pizzaName, quantity) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sendOrder);
            preparedStatement.setString(1, pizzaName);
            preparedStatement.setInt(2, quantity);

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            System.err.println("An error occurred: " + exception.getMessage());
        }
    }

        public PizzaType getPizzaById(int pizzaId){
            String getPizzaById = "SELECT * FROM pizza.pizzatype WHERE ID=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(getPizzaById);
                preparedStatement.setInt(1, pizzaId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        PizzaType pizzaType = new PizzaType();
                        pizzaType.setId(resultSet.getInt("ID"));
                        pizzaType.setName(resultSet.getString("PizzaType"));
                        pizzaType.setDescription(resultSet.getString("PizzaDescription"));
                        pizzaType.setPrice(resultSet.getDouble("Price"));
                        pizzaType.setImageUrl(resultSet.getString("ImageUrl"));
                        return pizzaType;
                    }
                }
            } catch (SQLException exception) {
                System.err.println("An error occurred: " + exception.getMessage());
            }
            return null;
        }
    }


