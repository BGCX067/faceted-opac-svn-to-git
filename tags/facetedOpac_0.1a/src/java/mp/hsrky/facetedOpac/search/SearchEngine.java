/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mp.hsrky.facetedOpac.service.Connection;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;

import org.apache.lucene.store.RAMDirectory;
import java.io.IOException;
import org.apache.lucene.queryParser.ParseException;
/**
 *
 * @author Administrator
 */
public class SearchEngine {

    public static void main(String[] args) throws IOException, ParseException {//constructor
        java.sql.Connection conn;
        Connection connection = new Connection();
        conn = connection.getConnection();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new RAMDirectory();

        IndexWriter writer = new IndexWriter(index, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);
            
        if (conn != null) {
            String sqlQuery = "SELECT * FROM `book_complete`";
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);
                while (rs.next()) {
                    Document doc = new Document();
                    doc.add(new Field("id", rs.getString("firstname"), Field.Store.YES, Field.Index.UN_TOKENIZED));
                    doc.add(new Field("title", rs.getString("title"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("subject", rs.getString("subject"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("author", rs.getString("author"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("isbn", rs.getString("isbn"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("call_number", rs.getString("call_number"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("imprint", rs.getString("imprint"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("year", rs.getString("year"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("publisher", rs.getString("publisher"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("description", rs.getString("description"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("format", rs.getString("format"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("language", rs.getString("language"), Field.Store.YES, Field.Index.TOKENIZED));
                    doc.add(new Field("location", rs.getString("location"), Field.Store.YES, Field.Index.TOKENIZED));
                    writer.addDocument(doc);

                }
                connection.closeConnection();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        writer.close();
    }
}
