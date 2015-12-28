/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.command;

import java.sql.*;
import mp.hsrky.facetedOpac.service.Connection;

public class LoginCommand {

    private java.sql.Connection conn;
    private Connection connection = new Connection();
    public LoginCommand() {
        
        conn = connection.getConnection();
    }
    private String username;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the userId.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param userId The userId to set.
     */
    public void setUsername(String userId) {
        this.username = userId;
    }

    public boolean authenticate() {
        boolean status = false;
        if (conn != null) {
            String sqlAuthenticate = "SELECT `name` FROM `admin` WHERE `username` = '" + username + "' " +
                    "AND `password` = '" + password + "'";
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlAuthenticate);
                if (rs.next()) {
                    setName(rs.getString(1));
                    status = true;
                }
            } catch (SQLException sqlex) {
                status = false;
                sqlex.printStackTrace();
            }
        } else {
            status = false;
        }
        return status;
    }
    
    public void finalize() throws Throwable {
        connection.closeConnection();
    }
}
