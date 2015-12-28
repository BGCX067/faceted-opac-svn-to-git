/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mp.hsrky.facetedOpac.model.SimpleSearch;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class UpdateBookController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ModelAndView modelAndView = new ModelAndView("admin/updateBook");
        if(!request.getParameterMap().isEmpty())
        {//has request
            String sTitle = request.getParameter("title");
            if(!("".compareTo(sTitle) > 0))
            {
                SimpleSearch ss = new SimpleSearch(sTitle);
                modelAndView.addObject("books", ss.exec());
                modelAndView.addObject("total", ss.execCount());
            }
        }
        else
        {
            modelAndView.addObject("total", 0);
        }

		return modelAndView;
	}
}