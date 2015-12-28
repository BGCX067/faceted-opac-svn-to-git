package mp.hsrky.facetedOpac.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import mp.hsrky.facetedOpac.command.LoginCommand;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("logout")!= null)
        {
            HttpSession oldSession = request.getSession();
            oldSession.removeAttribute("username");
            
            oldSession = null;
            response.sendRedirect("login.html");
        }

		ModelAndView modelAndView;
        if((request.getParameter("username") == null || "".equals(request.getParameter("username")))&&
            (request.getParameter("password") == null || "".equals(request.getParameter("password"))))
        {//not value entered
            modelAndView = new ModelAndView("admin/Login");
            modelAndView.addObject("message", null);
        }
        else if (request.getParameter("username") == null || "".equals(request.getParameter("username")))
        {//no username
            modelAndView = new ModelAndView("admin/Login");
            modelAndView.addObject("message", "*Username cannot be empty");
        }
        else if(request.getParameter("password") == null || "".equals(request.getParameter("password")))
        {//no password
            modelAndView = new ModelAndView("admin/Login");
            modelAndView.addObject("message", "*Password cannot be empty");
        }
        else
        {//validating
            LoginCommand lc = new LoginCommand();
            String username = request.getParameter("username").trim();
            username = username.replace("'", "");
            username = username.replace("\"", "");
            username = username.replace("#", "");
            String password = request.getParameter("password").trim();
            password = password.replace("'", "");
            password = password.replace("\"", "");
            password = password.replace("#", "");
            lc.setUsername(username);
            lc.setPassword(password);
            if(lc.authenticate())
            {//authenticated - start session
                HttpSession session = request.getSession(true);
                session.setAttribute("username", lc.getUsername());
                session.setAttribute("name", lc.getName());

                modelAndView = new ModelAndView("admin/home");
                modelAndView.addObject("message", lc.getName());
                response.sendRedirect("home.html");
            }
            else
            {
                modelAndView = new ModelAndView("admin/Login");
                modelAndView.addObject("message", "*Invalid username or password");
            }

        }

		return modelAndView;
	}
}