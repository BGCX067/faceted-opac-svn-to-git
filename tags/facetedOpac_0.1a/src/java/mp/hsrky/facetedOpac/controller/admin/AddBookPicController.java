package mp.hsrky.facetedOpac.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import mp.hsrky.facetedOpac.command.UploadPic;
import mp.hsrky.facetedOpac.service.AddBook;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class AddBookPicController extends HttpServlet implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();


        if (session.getAttribute("username") == null) {//session not started
            response.sendRedirect("login.html");
        }
        if (request.getParameter("bookId") != null) {
            //System.out.print(request.getRealPath(request.getContextPath()));
            String pic_path = request.getRealPath("/book_cover/");
            //System.out.print(request.getRealPath("/book_cover/"));
            String filename = "";
            String sBookId = request.getParameter("bookId").trim();
            String sCallNumber = request.getParameter("callNumber").trim().replace(".", "");
            filename = sCallNumber + "00" + sBookId;

            UploadPic uploadPic = new UploadPic();
            uploadPic.init(pic_path, filename);
            uploadPic.doPost(request, response);

            AddBook ab = new AddBook();
            if (uploadPic.getUploadSuccess()) {//upload picture successfully
                ab.updateImagePath(sBookId, "book_cover/" + uploadPic.getNewFileName());
            } else {
                //default is book_cover/default.jpg
                ab.updateImagePath(sBookId, "book_cover/default.jpg");
            }
            response.sendRedirect("home.html");
            ModelAndView modelAndView = new ModelAndView("admin/home");
            modelAndView.addObject("message", "Add book success!");
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin/home");
            modelAndView.addObject("message", "Invalid action detected. Please try again.");
            return modelAndView;
        }


    }
}