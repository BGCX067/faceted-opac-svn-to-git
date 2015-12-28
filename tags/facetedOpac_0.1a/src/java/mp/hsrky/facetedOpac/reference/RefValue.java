/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.reference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class RefValue {

    private java.sql.Connection conn;

    public String getRefValue(String field, Integer value) {
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

    public Integer getRefValue(String field, String value) {
        Integer ans = -1;
        String sqlRef = "SELECT `id` FROM `ref_" + field + "` WHERE `"+field+"` = '" + value + "'";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlRef);
            if (rs.next()) {
                ans = rs.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return ans;
    }
}
