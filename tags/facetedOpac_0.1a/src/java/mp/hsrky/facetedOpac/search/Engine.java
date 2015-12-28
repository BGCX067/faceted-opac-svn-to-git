/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.model.Book;
import mp.hsrky.facetedOpac.service.Connection;

/**
 *
 * @author Administrator
 */
public class Engine {

    private String query;
    private String countQuery;
    private java.sql.Connection conn;
    private Connection connection = new Connection();

    public Engine() {

        conn = connection.getConnection();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(String countQuery) {
        this.countQuery = countQuery;
    }

    public Book[] exec() {

        Book[] books = new Book[execCount()];
        try {

            Statement statQuery = conn.createStatement();

            ResultSet rsResult = statQuery.executeQuery(query);
            int i = 0;

            while (rsResult.next()) {

                Book book = new Book();
                book.setBookId(rsResult.getInt(1));
                book.retrieveData();
                books[i] = book;
                //book[i].setBookId(rsResult.getInt(1));
                i++;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return books;
    }

    public int execCount() {
        int count = 0;
        try {
            Connection connection2 = new Connection();
            conn = connection.getConnection();
            Statement statQuery = conn.createStatement();

            ResultSet rsResult = statQuery.executeQuery(countQuery);
            if (rsResult.next()) {

                count = rsResult.getInt(1);
            }
            connection2.closeConnection();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return count;
    }

    public void closeConnection() {
        try {
            if(connection != null)
            {
                connection.closeConnection();
            }
            if(conn != null)
            {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void finalize() throws Throwable {
        connection.closeConnection();
        conn.close();
    }
}
