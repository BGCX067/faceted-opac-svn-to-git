/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.service;

import java.sql.*;

public class Connection {

    private java.sql.Connection conn;

    public java.sql.Connection getConnection() {

        conn = null;

        String url = "jdbc:mysql://localhost:3306/hsr4ky_facetedopac";
        String username = "hsr4ky_opac";
        String password = "opac";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception sqlex) {
            System.out.print(sqlex);
        }
        /*
        String userName = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/faceted_opac";
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, userName, password);
        System.out.println("Database connection established");
        } catch (Exception e) {
        System.err.println("Cannot connect to database server" + e);
        } finally {
        if (conn != null) {
        try {
        conn.close();
        System.out.println("Database connection terminated");
        } catch (Exception e) {
        }
        }
        }
         * */
        return conn;

    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Connection connection = new Connection();
        if (connection.getConnection() != null) {
            System.out.println("Connected");
        }
    }
    public void finalize() throws Throwable {
        closeConnection();
    }
}