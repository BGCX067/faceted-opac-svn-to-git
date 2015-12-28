/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.model;

import mp.hsrky.facetedOpac.search.Engine;

/**
 *
 * @author 3P 2009
 */
public class SimpleSearch {

    private Engine en = new Engine();

    public SimpleSearch(String title) {
        String sql = "SELECT DISTINCT(`id`) FROM `book` WHERE `title` LIKE '%" + title + "%'";
        String sqlCount = "SELECT COUNT(DISTINCT(`id`)) FROM `book` WHERE `title` LIKE '%" + title + "%'";
        en.setQuery(sql);
        en.setCountQuery(sqlCount);
        en.closeConnection();
    }

    public Book[] exec()
    {
        return en.exec();
    }
    public int execCount()
    {
        return en.execCount();
    }
}
