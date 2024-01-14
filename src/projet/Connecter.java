package projet;

import java.sql.*;

public class Connecter {
    private Connection connection;

    public Connecter() {
        // Initialize the database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executerRequete(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void executerUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
