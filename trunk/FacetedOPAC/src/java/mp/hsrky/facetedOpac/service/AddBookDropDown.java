/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.model.DropDown;

/**
 *
 * @author Administrator
 */
public class AddBookDropDown {

    private java.sql.Connection conn;
    private Connection connection = new Connection();
    public AddBookDropDown() {
        
        conn = connection.getConnection();
    }

    public DropDown getRefList(String field) {
        int total = 0;
        String sqlQuery = "SELECT `id`, `" + field + "` from `ref_" + field + "`";
        String sqlCount = "SELECT COUNT(`id`) from `ref_" + field + "`";
        DropDown dropDown = new DropDown();
        try {
            Statement statCount = conn.createStatement();
            ResultSet rsTotal = statCount.executeQuery(sqlCount);
            if (rsTotal.next()) {
                total = rsTotal.getInt(1);
            }

            String[] aListValue = new String[total];
            String[] aListLabel = new String[total];
            Statement statRef = conn.createStatement();
            ResultSet rs = statRef.executeQuery(sqlQuery);
            int counter = 0;
            while (rs.next()) {
                aListValue[counter] = rs.getString(1);
                aListLabel[counter] = rs.getString(2);
                counter++;
            }

            dropDown.setLabel(aListLabel);
            dropDown.setValue(aListValue);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return dropDown;
    }
    public DropDown getRefList(String field, String selectedValue) {
        int total = 0;
        String sqlQuery = "SELECT `id`, `" + field + "` from `ref_" + field + "`";
        String sqlCount = "SELECT COUNT(`id`) from `ref_" + field + "`";
        DropDown dropDown = new DropDown();
        try {
            Statement statCount = conn.createStatement();
            ResultSet rsTotal = statCount.executeQuery(sqlCount);
            if (rsTotal.next()) {
                total = rsTotal.getInt(1);
            }

            String[] aListValue = new String[total];
            String[] aListLabel = new String[total];
            Statement statRef = conn.createStatement();
            ResultSet rs = statRef.executeQuery(sqlQuery);
            int counter = 0;
            while (rs.next()) {
                aListValue[counter] = rs.getString(1);
                aListLabel[counter] = rs.getString(2);
                counter++;
            }
            dropDown.setSelectedValue(selectedValue);
            dropDown.setLabel(aListLabel);
            dropDown.setValue(aListValue);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return dropDown;
    }
    public void finalize() throws Throwable {
        connection.closeConnection();
    }
}
