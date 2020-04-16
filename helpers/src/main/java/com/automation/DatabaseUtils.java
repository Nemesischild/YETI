package com.automation;

import com.runner.runner.EnhancedLogging;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {

    public static Connection setMySQLDBConnect(String sHost, String sPort, String sDBName, String sUsername, String sPassword) throws Exception{

        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Setup the connection with the DB
        String sConnectionString = "jdbc:mysql://"+sHost+":"+sPort+"/"+ sDBName+ "?user="+sUsername+"&password="+sPassword;
        Connection mySqlConnection;
        try{
            mySqlConnection = DriverManager
                    .getConnection(sConnectionString);
            return mySqlConnection;
        }catch(Exception e){
            EnhancedLogging.debug("DB Connection Issue: " + e.getMessage());
            return null;
        }
    }


    public static ResultSet queryMySQLDB(Connection mySqlConnection, String sQuery) throws Exception {
        Statement statement = null;
        ResultSet resultSet = null;
        //Connection myConnection =  mySqlConnection;

        try {

            // Statements allow to issue SQL queries to the database
            statement = mySqlConnection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery(sQuery);
            return resultSet;
        } catch (Exception e) {
            EnhancedLogging.debug("Query failed: \n" + e.getMessage());
            return null;
        }
    }


    public static int updateMySQLDB(Connection mySqlConnection, String sQuery) throws Exception {
        Statement statement = null;
        int resultSet;
        //Connection myConnection =  mySqlConnection;

        try {

            // Statements allow to issue SQL queries to the database
            statement = mySqlConnection.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeUpdate(sQuery);
            return resultSet;
        } catch (Exception e) {
            EnhancedLogging.debug("Query failed: \n" + e.getMessage());
            return 0;
        }

    }

    // You need to close the DB Connection
    private static void closeDBConnection (Connection connect) {
        try {
             if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
