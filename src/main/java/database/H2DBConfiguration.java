package database;

import java.sql.*;

/**
 * This class is responsible for H2DB configuration.
 */
public class H2DBConfiguration implements NotifiableDatabaseConfiguration {
    private final String user = "sa";
    private final String password = "empik2021";
    protected Connection h2Connection;

    public H2DBConfiguration(String dbUrl){
        try {
            this.h2Connection = DriverManager.getConnection(dbUrl, user, password);
            createTableIfNotExists();
        } catch (SQLException ex) {
            throw new IllegalStateException("Database connection initialization failure!");
        }
    }

    // Constructor for testing purposes.
    protected H2DBConfiguration(String dbUrl, String password){
        try {
            this.h2Connection = DriverManager.getConnection(dbUrl, user, password);
            createTableIfNotExists();
        } catch (SQLException ex) {
            throw new IllegalStateException("Database connection initialization failure!");
        }
    }

    protected void createTableIfNotExists() throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS USERS" +
                "(LOGIN varchar(255), " +
                "REQUEST_COUNT int);";
        Statement statement = h2Connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void notify(String login) throws DatabaseNotifyUnsuccessfulException{
        try{
            if(checkIfLoginExists(login))
                incrementRequestCounter(login);
            else
                insertLogin(login);
        } catch(Exception ex) {
            throw new DatabaseNotifyUnsuccessfulException(ex.getMessage());
        }
    }

    protected boolean checkIfLoginExists(String login) throws SQLException{
        String sql = "SELECT LOGIN FROM USERS WHERE LOGIN = ?;";
        PreparedStatement statement = h2Connection.prepareStatement(sql);
        statement.setString(1, login);
        return statement.executeQuery().next(); // Means no rows.
    }

    protected void incrementRequestCounter(String login) throws SQLException {
        String sql = "UPDATE USERS " +
                "SET REQUEST_COUNT = REQUEST_COUNT + 1 " +
                "WHERE LOGIN = ?;";
        PreparedStatement statement = h2Connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.executeUpdate();
    }

    protected void insertLogin(String login) throws SQLException{
        String sql = "INSERT INTO USERS VALUES (?, 1);";
        PreparedStatement statement = h2Connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.executeUpdate();
    }
}
