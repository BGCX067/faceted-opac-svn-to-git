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
public class FacetFormat {
private Book[] books;
private String sCurrentQuery = "";
private java.sql.Connection conn;
    public FacetFormat(String sCurrentQuery){
        this.sCurrentQuery = sCurrentQuery;
    }

    public void setBooks(Book[] books)
    {
        this.books = books;
    }

    public FacetType[] getFacetFormat(){
        FacetType[] fts = new FacetType[getCount()];
        String sql = "SELECT DISTINCT(`format`) from book_complete";
        if(sCurrentQuery.compareTo("") > 0)
        {
            sql += " WHERE `id` IN (" + sCurrentQuery + ")";
        }
        try {
                        Connection connection = new Connection();
            conn = connection.getConnection();
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
                    Connection connection = new Connection();
            conn = connection.getConnection();
        String sql = "SELECT COUNT(DISTINCT(`format`)) from book_complete";
        try {
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
    private int getTotalAvailable(String sFormat)
    {
        int total = 0;
        String sql = "SELECT COUNT(DISTINCT(`id`)) FROM book_complete WHERE `format` LIKE '%"+ sFormat +"%'";
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
