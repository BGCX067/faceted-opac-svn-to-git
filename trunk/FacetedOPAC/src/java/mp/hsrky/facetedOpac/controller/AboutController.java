/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AboutController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ModelAndView modelAndView = new ModelAndView("about");

		return modelAndView;
	}
}