/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.hsrky.facetedOpac.model.Book;
import mp.hsrky.facetedOpac.model.FacetAuthor;
import mp.hsrky.facetedOpac.model.FacetFormat;
import mp.hsrky.facetedOpac.model.FacetLanguage;
import mp.hsrky.facetedOpac.model.FacetPublisher;
import mp.hsrky.facetedOpac.model.FacetSubject;
import mp.hsrky.facetedOpac.model.FacetType;
import mp.hsrky.facetedOpac.model.QueryManager;
import mp.hsrky.facetedOpac.reference.RefValue;
import mp.hsrky.facetedOpac.search.Engine;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Administrator
 */
public class SearchController implements Controller{
    private RefValue refValue = new RefValue();
    private String cQuery = "";
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView("browse");

        System.out.print(request.getRequestURI());
        if(!request.getParameterMap().isEmpty())
        {//has request
            QueryManager qm = new QueryManager();
            modelAndView.addObject("started", true);
            
            if(request.getParameter("format") != null)
            {
                qm.addQuery("format", request.getParameter("format"));
            }
            if(request.getParameter("publisher") != null)
            {
                qm.addQuery("publisher", request.getParameter("publisher"));
            }
            if(request.getParameter("language") != null)
            {
                qm.addQuery("language", request.getParameter("language"));
            }
            if(request.getParameter("author") != null)
            {
                qm.addQuery("author", request.getParameter("author"));
            }
            if(request.getParameter("subject")!= null)
            {
                for(int i = 0; i < request.getParameterValues("subject").length; i++)
                {
                    qm.addQueries("subject", request.getParameterValues("subject")[i]);
                }
            }
            System.out.print("generated query: "+qm.getSimilarQuery());
            cQuery = qm.getSimilarQuery();
            Engine engine = new Engine();
            engine.setCountQuery(qm.getCountSimilarQuery());
            engine.setQuery(qm.getSimilarQuery());
            Book[] books = new Book[engine.execCount()];
            books = engine.exec();

            if(books.length > 0)
            {
                modelAndView.addObject("result", true);
                modelAndView.addObject("books", books);
                modelAndView.addObject("totalBooks", books.length);
            }
            else
            {//no result
                modelAndView.addObject("result", false);
            }
            modelAndView.addObject("queryItems", qm.getQueryPair());
        }
        else
        {
            cQuery = "";
            modelAndView.addObject("started", false);
            
        }
        String[] queriesSubject = request.getParameterValues("subject");
        if(queriesSubject == null)
        {
            String[] tempS = {};
            queriesSubject = tempS;
        }
        FacetAuthor fa = new FacetAuthor(cQuery);
        FacetType[] fa_t = fa.getFacetSubject();

        FacetLanguage fl = new FacetLanguage(cQuery);
        FacetType[] fl_t = fl.getFacetFormat();

        FacetPublisher fp = new FacetPublisher(cQuery);
        FacetType[] fp_t = fp.getFacetSubject();

        FacetSubject fs = new FacetSubject(cQuery, queriesSubject);
        FacetType[] fs_t = fs.getFacetSubject();

        FacetFormat ff = new FacetFormat(cQuery);
        FacetType[] ff_t = ff.getFacetFormat();

        modelAndView.addObject("facet_author", fa_t);
		modelAndView.addObject("facet_language", fl_t);
        modelAndView.addObject("facet_publisher", fp_t);
		modelAndView.addObject("facet_subject", fs_t);
        modelAndView.addObject("facet_format", ff_t);
        return modelAndView;
	}
}
