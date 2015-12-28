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
public class FacetSubject {

    private Book[] books;
    private java.sql.Connection conn;
    private String sCurrentQuery = "";
    private String[] queries;
    public FacetSubject(String sCurrentQuery, String[] queries) {
        this.sCurrentQuery = sCurrentQuery;
        this.queries = queries;
    }
    public void setBooks(Book[] books) {
        this.books = books;
    }

    public FacetType[] getFacetSubject() {
        FacetType[] fts = new FacetType[getCount()];
        String sql = "SELECT DISTINCT(`subject`) FROM `book_complete` WHERE `subject` != ''";
        if(sCurrentQuery.compareTo("") > 0)
        {
            sql += " AND `id` IN (" + sCurrentQuery + ")";
        }
        if(queries.length > 0)
        {
            sql += " AND `subject` NOT IN (";
            for(int i = 0; i < queries.length; i++)
            {
                sql += "'" + queries[i] + "'";
                if(i != queries.length - 1)
                {
                    sql += ",";
                }
            }
            sql += ")";
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

    public int getCount() {
        int ans = 0;
        Connection connection = new Connection();
        conn = connection.getConnection();
        String sql = "SELECT COUNT(DISTINCT(`subject`)) FROM `book_complete` WHERE `subject` != ''";
        if(sCurrentQuery.compareTo("") > 0)
        {
            sql += " AND `id` IN (" + sCurrentQuery + ")";
        }
        System.out.print(queries.length);
        if(queries.length > 0)
        {
            sql += " AND `subject` NOT IN (";
            for(int i = 0; i < queries.length; i++)
            {
                sql += "'" + queries[i] + "'";
                if(i != queries.length - 1)
                {
                    sql += ",";
                }
            }
            sql += ")";
        }
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
    private int getTotalAvailable(String sSubject)
    {
        int total = 0;
        String sql = "SELECT COUNT(DISTINCT(`id`)) FROM book_complete WHERE `subject` LIKE '%"+ sSubject +"%'";
        if(sCurrentQuery.compareTo("") > 0)
        {
            sql += " AND `id` IN (" + sCurrentQuery + ")";
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
