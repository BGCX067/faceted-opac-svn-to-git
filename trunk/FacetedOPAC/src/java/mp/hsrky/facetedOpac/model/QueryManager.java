/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.model;

/**
 *
 * @author Administrator
 */
public class QueryManager {

    private QueryPair[] querypair = {};
    private QueryPair[] queriespair = {};

    public QueryManager() {
    }

    public QueryPair[] getQueryPair(){
        QueryPair[] qp = new QueryPair[querypair.length + queriespair.length];
        int i;
        for(i = 0; i < querypair.length; i++)
        {
            qp[i] = querypair[i];
        }
        for(int j = 0; j < queriespair.length; j++)
        {
            qp[i] = queriespair[j];
            i++;
        }
        return qp;
    }

    public void addQuery(String field, String value) {
        QueryPair qp = new QueryPair();
        qp.setField(field);
        qp.setValue(value);
        QueryPair[] qpCopy = new QueryPair[querypair.length + 1];
        for (int i = 0; i < querypair.length; i++) {
            qpCopy[i] = querypair[i];
        }
        qpCopy[querypair.length] = qp;
        querypair = qpCopy;
    }

    public void addQueries(String field, String value) {
        QueryPair qp = new QueryPair();
        qp.setField(field);
        qp.setValue(value);
        QueryPair[] qpCopy = new QueryPair[queriespair.length + 1];
        for (int i = 0; i < queriespair.length; i++) {
            qpCopy[i] = queriespair[i];
        }
        qpCopy[queriespair.length] = qp;
        queriespair = qpCopy;
    }

    public String getQuery() {
        String sql = "SELECT DISTINCT(`id`) FROM `book_complete` WHERE `" + querypair[0].getField() + "` = '" + querypair[0].getValue() + "'";
        for (int i = 1; i < querypair.length; i++) {
            sql += " AND `" + querypair[i].getField() + "` = '" + querypair[i].getValue() + "'";
        }
        return sql;
    }

    public String getCountQuery() {
        String sql = "SELECT COUNT(DISTINCT(`id`)) FROM `book_complete` WHERE `" + querypair[0].getField() + "` = '" + querypair[0].getValue() + "'";
        for (int i = 1; i < querypair.length; i++) {
            sql += " AND `" + querypair[i].getField() + "` = '" + querypair[i].getValue() + "'";
        }
        return sql;
    }

    public String getSimilarQuery() {
        String sql = "";
        if (querypair.length > 0)
        {
            sql = "SELECT DISTINCT(`id`) FROM `book_complete` WHERE `" + querypair[0].getField() + "` LIKE '%" + querypair[0].getValue() + "%'";
            for (int i = 1; i < querypair.length; i++)
            {
                sql += " AND `" + querypair[i].getField() + "` LIKE '%" + querypair[i].getValue() + "%'";
            }

            for (int j = 0; j < queriespair.length; j++)
            {
                sql += " AND `id` IN (SELECT `id` FROM `book_complete` WHERE `subject` LIKE '%" + queriespair[j].getValue() + "%')";
            }
        }
        else if(queriespair.length > 0)
        {
            sql = "SELECT DISTINCT(`id`) FROM `book_complete` WHERE `" + queriespair[0].getField() + "` LIKE '%" + queriespair[0].getValue() + "%'";
            for (int j = 0; j < queriespair.length; j++)
            {
                sql += " AND `id` IN (SELECT `id` FROM `book_complete` WHERE `subject` LIKE '%" + queriespair[j].getValue() + "%')";
            }
        }
        return sql;
    }

    public String getCountSimilarQuery() {
        String sql = "";
        if (querypair.length > 0)
        {
            sql = "SELECT COUNT(DISTINCT(`id`)) FROM `book_complete` WHERE `" + querypair[0].getField() + "` LIKE '%" + querypair[0].getValue() + "%'";
            for (int i = 1; i < querypair.length; i++) {
                sql += " AND `" + querypair[i].getField() + "` LIKE '%" + querypair[i].getValue() + "%'";
            }
            for (int j = 0; j < queriespair.length; j++) {
                sql += " AND `id` IN (SELECT `id` FROM `book_complete` WHERE `subject` LIKE '%" + queriespair[j].getValue() + "%')";
            }
            System.out.println("generated query" + " " + sql);
        }
        else if(queriespair.length > 0)
        {
            sql = "SELECT COUNT(DISTINCT(`id`)) FROM `book_complete` WHERE `" + queriespair[0].getField() + "` LIKE '%" + queriespair[0].getValue() + "%'";
            for(int j = 1; j < queriespair.length; j++)
            {
                sql += " AND `id` IN (SELECT `id` FROM `book_complete` WHERE `subject` LIKE '%" + queriespair[j].getValue() + "%')";
            }
        }
        return sql;
    }
}
