/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.model.Book;

/**
 *
 * @author Administrator
 */
public class NewBookItem {

    private java.sql.Connection conn;
    private int MAX_ROW = 6;
    private Book[] book;
    public NewBookItem() {
        Connection connection = new Connection();
        conn = connection.getConnection();
        String sqlQuery = "SELECT `id` FROM `book` order by id desc";
        try
        {
            Statement statQuery = conn.createStatement();
            ResultSet rsResult = statQuery.executeQuery(sqlQuery);
            int counter = 0;
            book = new Book[MAX_ROW];
            while(rsResult.next() && counter < MAX_ROW)
            {
                book[counter] = new Book();
                book[counter].setBookId(rsResult.getInt(1));
                book[counter].retrieveData();
                counter++;
            }
            connection.closeConnection();
        }
        catch(SQLException sqlEx)
        {
            sqlEx.printStackTrace();
        }
    }

    public Book[] getBook(){
        return book;
    }
}
