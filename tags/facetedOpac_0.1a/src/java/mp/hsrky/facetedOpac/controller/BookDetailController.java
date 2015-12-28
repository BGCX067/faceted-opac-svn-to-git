/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.controller;

/**
 *
 * @author Administrator
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.hsrky.facetedOpac.model.Book;
import mp.hsrky.facetedOpac.model.CheckString;
import mp.hsrky.facetedOpac.model.FacetAuthor;
import mp.hsrky.facetedOpac.model.FacetFormat;
import mp.hsrky.facetedOpac.model.FacetLanguage;
import mp.hsrky.facetedOpac.model.FacetPublisher;
import mp.hsrky.facetedOpac.model.FacetSubject;
import mp.hsrky.facetedOpac.model.FacetType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BookDetailController implements Controller {
    private CheckString cString = new CheckString();
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("id") == null)
        {//no id specified

            response.sendRedirect("index.html");
        }
        Book book = new Book();

        try{
        book.setBookId(Integer.parseInt(request.getParameter("id")));
        }catch(Exception e)
        {//invalid id

            response.sendRedirect("index.html");
        }

        if(book.retrieveData())
        {

        }else
        {
           response.sendRedirect("index.html");
        }

        String[] non = {};
		ModelAndView modelAndView = new ModelAndView("book_details");
		modelAndView.addObject("book", book);
        FacetAuthor fa = new FacetAuthor("");
        FacetType[] fa_t = fa.getFacetSubject();

        FacetLanguage fl = new FacetLanguage("");
        FacetType[] fl_t = fl.getFacetFormat();

        FacetPublisher fp = new FacetPublisher("");
        FacetType[] fp_t = fp.getFacetSubject();

        FacetSubject fs = new FacetSubject("", non);
        FacetType[] fs_t = fs.getFacetSubject();

        FacetFormat ff = new FacetFormat("");
        FacetType[] ff_t = ff.getFacetFormat();

        modelAndView.addObject("facet_author", fa_t);
		modelAndView.addObject("facet_language", fl_t);
        modelAndView.addObject("facet_publisher", fp_t);
		modelAndView.addObject("facet_subject", fs_t);
        modelAndView.addObject("facet_format", ff_t);
		return modelAndView;
	}
}