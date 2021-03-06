/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.service.Connection;

/**
 *
 * @author Administrator
 */
public class FacetLanguage {
private Book[] books;
private java.sql.Connection conn;
private String sCurrentQuery = "";
    public FacetLanguage(String sqlCurrentQuery){
        sCurrentQuery = sqlCurrentQuery;
    }

    public void setBooks(Book[] books)
    {
        this.books = books;
    }

    public FacetType[] getFacetFormat(){
        FacetType[] fts = new FacetType[getCount()];
                    Connection connection = new Connection();
            conn = connection.getConnection();
        String sql = "SELECT DISTINCT(`language`) from book_complete";
        if(sCurrentQuery.compareTo("") > 0)
        {
            sql += " WHERE `id` IN (" + sCurrentQuery + ")";
        }
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                FacetType ft = new FacetType();
                ft.setValue(rs.getString(1));
                ft.setCount(getTotalAvailable(rs.getString(1)));
                fts[i] = ft;
                i++;
            }
            connection.closeConnection();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return fts;
    }
    public int getCount()
    {
        int ans = 0;
        String sql = "SELECT COUNT(DISTINCT(`language`)) from book_complete";
        try {
                        Connection connection = new Connection();
            conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ans = rs.getInt(1);
            }
            connection.closeConnection();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return ans;
    }
    private int getTotalAvailable(String sLanguage)
    {
        int total = 0;
        String sql = "SELECT COUNT(DISTINCT(`id`)) FROM book_complete WHERE `language` LIKE '%"+ sLanguage +"%'";
        if(sCurrentQuery.compareTo("") > 0)
        {
           sql += " and `id` IN (" + sCurrentQuery + ")";
        }
        try {
            Connection connection = new Connection();
            conn = connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                total = rs.getInt(1);
            }
            connection.closeConnection();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return total;
    }
}
