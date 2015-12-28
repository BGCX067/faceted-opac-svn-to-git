/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.service.AddBook;
import mp.hsrky.facetedOpac.service.Connection;

/**
 *
 * @author Administrator
 */
public class Book {

    private java.sql.Connection conn;
    private int bookId;
    private int year;
    private int iFormat;
    private int iLocation;
    private int iLanguage;
    private int shortTitleLength = 25;
    private String shortTitle;
    private String title;
    private String call_number;
    private String format;
    private String location;
    private String location_link;
    private String imprint;
    private String publisher;
    private String language;
    private String description;
    private String image;
    private String[] subject;
    private String[] isbn;
    private String[] author;
    private String plainAuthor;
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }
    public String getShortTitle()
    {
        if(title.length() > shortTitleLength + 3)
        {
            shortTitle = title.substring(0, shortTitleLength) + "...";
        }
        else
        {
            shortTitle = title;
        }
        return shortTitle;
    }
    private String getRefValue(String field, Integer value) {
        String ans = "";
        String sqlRef = "SELECT `" + field + "` FROM `ref_" + field + "` WHERE `id` = " + value;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlRef);
            if (rs.next()) {
                ans = rs.getString(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return ans;
    }

    public String[] getArrayValue(String field, Integer bookId) {

        Integer totalRow = 0;
        String sqlQuery = "SELECT `" + field + "` FROM `book_" + field + "` WHERE `book_id` = " + bookId;
        String sqlTotal = "SELECT COUNT(`" + field + "`) FROM `book_" + field + "` WHERE `book_id` = " + bookId;

        try {//get total rows
            Statement stat = conn.createStatement();
            ResultSet rsTotal = stat.executeQuery(sqlTotal);
            if (rsTotal.next()) {
                totalRow = rsTotal.getInt(1);
                if (totalRow <= 0) {//no value
                    String[] string = {};
                    return string;
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        String[] ans = new String[totalRow];

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlQuery);
            int i = 0;
            while (rs.next()) {
                ans[i] = rs.getString(1);
                i++;

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return ans;
    }
    public boolean update()
    {
        boolean success = false;
        Connection connection = new Connection();
        conn = connection.getConnection();

        if(conn != null)
        {
            String sql[] = {
                "DELETE FROM `book_author` WHERE `book_id` = " + bookId,
                "DELETE FROM `book_isbn` WHERE `book_id` = " + bookId,
                "DELETE FROM `book_subject` WHERE `book_id` = " + bookId
            };
            try{
                conn.setAutoCommit(false);
                AddBook addBook = new AddBook();
                Statement st = conn.createStatement();
                for(int i = 0; i < sql.length; i++)
                {//remove other table ref - ez
                    st.executeUpdate(sql[i]);
                }
                conn.commit();
                connection.closeConnection();
                addBook.update(this);
                success = true;

            }catch(SQLException sqlEx)
            {
                sqlEx.printStackTrace();
            }
        }
        return success;
    }
    public boolean remove()
    {
        boolean success = false;
        Connection connection = new Connection();
        conn = connection.getConnection();
        
        if(conn != null)
        {
            String[] sql  = {
                "DELETE FROM `book` WHERE `id` = " + bookId,
                "DELETE FROM `book_author` WHERE `book_id` = " + bookId,
                "DELETE FROM `book_isbn` WHERE `book_id` = " + bookId,
                "DELETE FROM `book_subject` WHERE `book_id` = " + bookId
            };

            try{
                conn.setAutoCommit(false);
                Statement st = conn.createStatement();
                for(int i = 0; i < sql.length; i++)
                {
                    st.executeUpdate(sql[i]);
                }
                conn.commit();
                
                success = true;
            }catch(SQLException e){
                e.printStackTrace();
            }

            
            success = true;
        }
        connection.closeConnection();
        return success;
    }
    public boolean retrieveData() {
        boolean valid = false;
        Connection connection = new Connection();
        conn = connection.getConnection();
        if (conn != null) {
            String sqlBook = "SELECT * FROM `book` WHERE `id` = " + bookId;
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlBook);
                if (rs.next()) {
                    setYear(rs.getInt("year"));
                    setIFormat(rs.getInt("format"));
                    setFormat(getRefValue("format", getIFormat()));

                    setILocation(rs.getInt("location"));
                    setLocation(getRefValue("location", getILocation()));

                    setILanguage(rs.getInt("language"));
                    setLanguage(getRefValue("language", getILanguage()));

                    setTitle(rs.getString("title"));
                    setLocation_link(rs.getString("location_link"));
                    setCall_number(rs.getString("call_number"));
                    setImprint(rs.getString("imprint"));
                    setPublisher(rs.getString("publisher"));
                    setDescription(rs.getString("description"));
                    setImage(rs.getString("image"));

                    setAuthor(getArrayValue("author", bookId));
                    setIsbn(getArrayValue("isbn", bookId));
                    setSubject(getArrayValue("subject", bookId));
                    valid = true;
                } else {
                    valid = false;
                }
            } catch (SQLException sqlEx) {
                valid = false;
                sqlEx.printStackTrace();
            }
            connection.closeConnection();
        }
        return valid;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getCall_number() {
        return call_number;
    }

    public void setCall_number(String call_number) {
        this.call_number = call_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getIFormat() {
        return iFormat;
    }

    public void setIFormat(int iFormat) {
        this.iFormat = iFormat;
    }

    public int getILanguage() {
        return iLanguage;
    }

    public void setILanguage(int iLanguage) {
        this.iLanguage = iLanguage;
    }

    public int getILocation() {
        return iLocation;
    }

    public void setILocation(int iLocation) {
        this.iLocation = iLocation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String[] getIsbn() {
        return isbn;
    }

    public void setIsbn(String[] isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_link() {
        return location_link;
    }

    public void setLocation_link(String location_link) {
        this.location_link = location_link;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String getPlainAuthor()
    {
        String authors = "";
        for(int i = 0; i < author.length; i++)
        {
            authors += author[i] + ". ";
        }
        plainAuthor = authors;
        return plainAuthor;
    }
}
