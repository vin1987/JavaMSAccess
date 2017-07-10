package com.digital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeController {
         
            public static void main(String[] args) {
         
                // variables
                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
         
                // Step 1: Loading or registering Oracle JDBC driver class
                try {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                }
                catch(ClassNotFoundException cnfex) {
                    System.out.println("Problem in loading MS Access JDBC driver");
                    cnfex.printStackTrace();
                }
         
                // Step 2: Opening database connection
                try {
         
                    String msAccessDBName = 
                                           "D:\\WORKSPACE\\TEST_WORKSPACE\\Java-JDBC\\Player.accdb";
                    String dbURL = 
                                        "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};                                      DBQ=" + msAccessDBName + ";DriverID=22;READONLY=true";
         
                    // Step 2.A: Create and get connection using DriverManager class
                    connection = DriverManager.getConnection(dbURL); 
         
                    // Step 2.B: Creating JDBC Statement 
                    statement = connection.createStatement();
         
                    // Step 2.C: Executing SQL &amp; retrieve data into ResultSet
                    resultSet = statement.executeQuery("SELECT * FROM PLAYER");
         
                    System.out.println("ID\tName\t\t\tAge\tMatches");
                    System.out.println("==\t================\t===\t=======");
         
                    // processing returned data and printing into console
                    while(resultSet.next()) {
                        System.out.println(resultSet.getInt(1) + "\t" + 
                                resultSet.getString(2) + "\t" + 
                                resultSet.getString(3) + "\t" +
                                resultSet.getString(4));
                    }
         
                }
                catch(SQLException sqlex){
                    sqlex.printStackTrace();
                }
                finally {
         
                    // Step 3: Closing database connection
                    try {
                        if(null != connection) {
         
                            // cleanup resources, once after processing
                            resultSet.close();
                            statement.close();
         
                            // and then finally close connection
                            connection.close();
                        }
                    }
                    catch (SQLException sqlex) {
                        sqlex.printStackTrace();
                    }
                }
            }
        

}
