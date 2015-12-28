/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.controller.admin;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.hsrky.facetedOpac.model.Book;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DeleteBookController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        String sId = request.getParameter("id");
        Book book = new Book();
        book.setBookId(Integer.parseInt(sId));
        if(book.retrieveData())
        {
            book.remove();
        }
		ModelAndView modelAndView = new ModelAndView("admin/home");

		return modelAndView;
	}
}