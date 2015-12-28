/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class Facet {
    private java.sql.Connection conn;
    private Book[] books;
    public Facet(){
        
    }

    public void setBooks(Book[] books)
    {
        this.books = books;
    }

    public FacetType[] getFacetFormat()
    {
        FacetType[] fts = new FacetType[books.length];
        for(int i = 0; i < books.length; i++)
        {
            FacetType ft = new FacetType();
            ft.setCount(getExtCount("format", books[i].getBookId()));
            ft.setValue(books[i].getFormat());
            fts[i] = ft;
        }
        return fts;
    }

    public FacetType[] getFacetSubject()
    {
        FacetType[] fts = new FacetType[books.length];
        for(int i = 0; i < books.length; i++)
        {

        }
        return fts;
    }

    public FacetType[] getFacetPublisher()
    {
        FacetType[] fts = new FacetType[books.length];
        for(int i = 0; i < books.length; i++)
        {
            FacetType ft = new FacetType();
            ft.setCount(getIntCount("format", books[i].getBookId()));
            ft.setValue(books[i].getFormat());
            fts[i] = ft;
        }
        return fts;
    }

    public FacetType[] getFacetLanguage()
    {
        FacetType[] fts = new FacetType[books.length];
        for(int i = 0; i < books.length; i++)
        {
            FacetType ft = new FacetType();
            ft.setCount(getExtCount("format", books[i].getBookId()));
            ft.setValue(books[i].getFormat());
            fts[i] = ft;
        }
        return fts;
    }

    public FacetType[] getFacetAuthor()
    {
        FacetType[] fts = new FacetType[books.length];
        for(int i = 0; i < books.length; i++)
        {
            FacetType ft = new FacetType();
            ft.setCount(getExtCount("author", books[i].getBookId()));
            ft.setValue(books[i].getFormat());
            fts[i] = ft;
        }
        return fts;
    }

    public int getExtCount(String field, Integer bookId)
    {
        int ans = 0;
        String sql = "SELECT COUNT(`id`) FROM `"+field+"` WHERE `book_id` = " + Integer.toString(bookId);
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ans = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return ans;
    }

    public int getIntCount(String field, Integer bookId)
    {
        int ans = 0;
        String sql = "SELECT COUNT(`id`) FROM `book` WHERE `"+field+"` = " + Integer.toString(bookId);
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                ans = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return ans;
    }

}
