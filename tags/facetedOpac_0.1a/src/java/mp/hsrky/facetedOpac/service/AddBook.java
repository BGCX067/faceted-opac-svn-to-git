package mp.hsrky.facetedOpac.service;

import mp.hsrky.facetedOpac.model.Book;

import java.sql.*;
import mp.hsrky.facetedOpac.model.CheckString;

/**
 *
 * @author Administrator
 */
public class AddBook {

    private java.sql.Connection conn;
    private java.sql.Connection connTransaction; //with transaction function
    private CheckString cString = new CheckString();

    public AddBook() {
        Connection connection = new Connection();
        conn = connection.getConnection();
    }

    public boolean update(Book book) {
        boolean updateSuccess = false;
        Connection connectionTransaction = new Connection();
        connTransaction = connectionTransaction.getConnection();

        try {//transaction start
            connTransaction.setAutoCommit(false);
            PreparedStatement pst;
            Statement st = connTransaction.createStatement();

            String sqlUpdateBook = "UPDATE `book` SET " +
                    "`title` = '"+cString.verify(book.getTitle())+"'," +
                    "`call_number` = '"+cString.verify(book.getCall_number())+"', " +
                    "`format` = '"+book.getIFormat()+"', " +
                    "`location` = '"+book.getILocation()+"', " +
                    "`location_link` = '"+cString.verify(book.getLocation_link())+"', " +
                    "`imprint` = '"+cString.verify(book.getImprint())+"', " +
                    "`year` = '"+book.getYear()+"', " +
                    "`publisher` = '"+cString.verify(book.getPublisher())+"', " +
                    "`language` = '"+book.getILanguage()+"', " +
                    "`description` ='"+cString.verify(book.getDescription())+"' " +
                    "WHERE `id` = " + book.getBookId();
            System.out.print("Sql update book: " + sqlUpdateBook);
            st.executeUpdate(sqlUpdateBook);
                        //insert into table "author" /multiple author
            
            int i;

            for (i = 0; i < book.getAuthor().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_author` (`book_id`, `author`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getAuthor()[i]));
                pst.setInt(1, book.getBookId());
                pst.execute();
            }
            //insert into table "subject" /multiple subject
            for (i = 0; i < book.getSubject().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_subject` (`book_id`, `subject`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getSubject()[i]));
                pst.setInt(1, book.getBookId());
                pst.execute();
            }
            //insert into table "isbn" /multiple isbn
            for (i = 0; i < book.getIsbn().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_isbn` (`book_id`, `isbn`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getIsbn()[i]));
                pst.setInt(1, book.getBookId());
                pst.execute();
            }
            connTransaction.commit();
            updateSuccess = true;
            System.out.print("Add book transaction committed");
            
        } catch (SQLException sqlEx) {
            if (connTransaction != null) {
                try {
                    connTransaction.rollback();
                    System.out.print("Add book transaction rollback.");
                    connectionTransaction.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            sqlEx.printStackTrace();
        } finally {
            try {
                if (connTransaction != null && !connTransaction.isClosed()) {
                    connTransaction.close();
                }
            } catch (SQLException e) {
            }
        }
        connectionTransaction.closeConnection();
        return updateSuccess;
    }

    public boolean register(Book book) {
        boolean registerSuccess = false;
        Connection connectionTransaction = new Connection();
        connTransaction = connectionTransaction.getConnection();

        try {//transaction start
            connTransaction.setAutoCommit(false);
            PreparedStatement pst;
            Statement st = connTransaction.createStatement();
            //insert into table "book"
            /*PreparedStatement pst = connTransaction.prepareStatement("INSERT INTO `book` " +
            "(`title`,`call_number`, `format`, `location`, `location_link`, `imprint`, " +
            "`year`, `publisher`, `language`, `description`, `image`) VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getCall_number());
            pst.setInt(3, book.getFormat());
            pst.setInt(4, book.getLocation());
            pst.setString(5, book.getLocation_link());
            pst.setString(6, book.getImprint());
            pst.setInt(7, book.getYear());
            pst.setString(8, book.getPublisher());
            pst.setInt(9, book.getLanguage());
            pst.setString(10, book.getDescription());
            pst.setString(11, "to do");
            pst.execute();*/

            String sqlInsertBook = "INSERT INTO `book` " +
                    "(`title`,`call_number`, `format`, `location`, `location_link`, `imprint`, " +
                    "`year`, `publisher`, `language`, `description`) VALUES " +
                    "('" + cString.verify(book.getTitle()) + "','" + cString.verify(book.getCall_number()) + "'," + book.getIFormat() + "," +
                    book.getILocation() + ",'" + cString.verify(book.getLocation_link()) + "','" + cString.verify(book.getImprint()) +
                    "'," + book.getYear() + ",'" + cString.verify(book.getPublisher()) + "'," + book.getILanguage() + ",'" + cString.verify(book.getDescription()) + "')";
            System.out.print("Sql insert book: " + sqlInsertBook);
            st.execute(sqlInsertBook, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = st.getGeneratedKeys();
            int id = 1; //book id - foreign keys
            while (keys.next()) {
                id = keys.getInt(1);
                book.setBookId(id);
            }
            //insert into table "author" /multiple author
            int i;

            for (i = 0; i < book.getAuthor().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_author` (`book_id`, `author`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getAuthor()[i]));
                pst.setInt(1, id);
                pst.execute();
            }
            //insert into table "subject" /multiple subject
            for (i = 0; i < book.getSubject().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_subject` (`book_id`, `subject`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getSubject()[i]));
                pst.setInt(1, id);
                pst.execute();
            }
            //insert into table "isbn" /multiple isbn
            for (i = 0; i < book.getIsbn().length; i++) {
                pst = connTransaction.prepareStatement("INSERT INTO `book_isbn` (`book_id`, `isbn`) VALUES (?,?)");
                pst.setString(2, cString.verify(book.getIsbn()[i]));
                pst.setInt(1, id);
                pst.execute();
            }
            connTransaction.commit();
            registerSuccess = true;
            System.out.print("Add book transaction committed");
            connectionTransaction.closeConnection();
        } catch (SQLException sqlEx) {
            if (connTransaction != null) {
                try {
                    connTransaction.rollback();
                    System.out.print("Add book transaction rollback.");
                    connectionTransaction.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            sqlEx.printStackTrace();
        } finally {
            try {
                if (connTransaction != null && !connTransaction.isClosed()) {
                    connTransaction.close();
                }
            } catch (SQLException e) {
            }
        }

        return registerSuccess;
    }

    public boolean verifyRef(String field, int value) {
        boolean isExist = false;
        String sqlQuery = "SELECT * from `ref_" + cString.verify(field) + "` WHERE id = " + value;
        try {
            Statement statQuery = conn.createStatement();
            isExist = statQuery.execute(sqlQuery);
            System.out.print(field + "- isExist: " + isExist);
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return isExist;
    }

    public int addRef(String field, String value) {
        int autoIncValue = -1;
        String sqlInsert = "INSERT into `ref_" + cString.verify(field) + "` set `" + cString.verify(field) + "` = '" + cString.verify(value) + "'";
        try {
            Statement statInsert = conn.createStatement();
            int result = statInsert.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            if (result == 1) {
                ResultSet rs = statInsert.getGeneratedKeys();
                if (rs.next()) {
                    autoIncValue = rs.getInt(1);
                }
                rs.close();
            }

        //autoIncValue

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.print("autoIncValue: " + autoIncValue);
        return autoIncValue;
    }
//insert image path

    public boolean updateImagePath(String bookId, String path) {
        boolean status;
        String sqlUpdate = "UPDATE `book` SET `image` = '" + cString.verify(path) + "' WHERE `id` = " + cString.verify(bookId);
        try {
            Statement statUpdate = conn.createStatement();
            int result = statUpdate.executeUpdate(sqlUpdate);
            if (result == 1) {
                status = true;
            } else {
                status = false;
            }
        } catch (SQLException sqlEx) {
            status = false;
            sqlEx.printStackTrace();
        }
        return status;
    }

    protected void finalize() throws Throwable {
        //do finalization here
        conn.close();
        connTransaction.close();
    }
}
