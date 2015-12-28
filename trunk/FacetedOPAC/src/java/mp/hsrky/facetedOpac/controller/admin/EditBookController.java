/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mp.hsrky.facetedOpac.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import mp.hsrky.facetedOpac.model.Book;
import mp.hsrky.facetedOpac.model.DropDown;
import mp.hsrky.facetedOpac.service.AddBook;
import mp.hsrky.facetedOpac.service.AddBookDropDown;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EditBookController implements Controller {

    private HttpSession session;
    private ModelAndView modelAndView;

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        if (session.getAttribute("username") == null) {//session not started
            response.sendRedirect("login.html");
        }
        if (request.getParameter("title") != null) {
            //has POST data => perform update
            //simple update logic => delete, then add
            String sBookId = request.getParameter("bookId");
            Book book = new Book();
            book.setBookId(Integer.parseInt(sBookId, 10));
            AddBook addBook = new AddBook();
            if (book.retrieveData()) {
                try {
                    book.setAuthor(request.getParameterValues("author"));
                    //case description
                    book.setDescription(request.getParameter("desc"));
                    //case call number
                    book.setCall_number(request.getParameter("call_number"));
                    //case format
                    try {
                        int iFormat = Integer.parseInt(request.getParameter("format"), 10);
                        if (addBook.verifyRef("format", iFormat)) {
                            book.setIFormat(iFormat);
                        } else {
                            book.setIFormat(addBook.addRef("format", Integer.toString(iFormat)));
                        }
                    } catch (Exception e) {
                        book.setIFormat(addBook.addRef("format", request.getParameter("format")));
                    }
                    //book.setImage(request.getParamter("image"));

                    //case imprint
                    book.setImprint(request.getParameter("imprint"));
                    //case isbn
                    book.setIsbn(request.getParameterValues("ISBN"));
                    //case language
                    try {
                        int iLanguage = Integer.parseInt(request.getParameter("language"), 10);
                        if (addBook.verifyRef("language", iLanguage)) {
                            book.setILanguage(iLanguage);
                        } else {
                            int iNewLanguage = addBook.addRef("language", Integer.toString(iLanguage));
                            book.setILanguage(iNewLanguage);
                        }
                    } catch (Exception e) {
                        book.setILanguage(addBook.addRef("language", request.getParameter("language")));
                    }
                    //case location
                    try {
                        int iLocation = Integer.parseInt(request.getParameter("location"), 10);
                        if (addBook.verifyRef("location", iLocation)) {
                            book.setILocation(iLocation);
                        } else {//if user enter integer as location
                            int iNewLocation = addBook.addRef("location", Integer.toString(iLocation));
                            book.setILocation(iNewLocation);
                        }
                    } catch (Exception e) {
                        //if the location is new
                        book.setILocation(addBook.addRef("location", request.getParameter("location")));
                    }
                    //case location link (case when location is online only)
                    if (request.getParameter("location_link") != null) {
                        book.setLocation_link(request.getParameter("location_link"));
                    } else {
                        book.setLocation_link("");
                    }
                    //case publisher
                    book.setPublisher(request.getParameter("publisher"));
                    //case subject
                    book.setSubject(request.getParameterValues("subject"));
                    //case title
                    book.setTitle(request.getParameter("title"));
                    //case year
                    book.setYear(Integer.parseInt(request.getParameter("year"), 10));

                } catch (Exception e) {
                    System.out.print("create book object error: \n" + e);
                }
                book.update();
            }

            modelAndView = new ModelAndView("admin/home");

        } else {
            modelAndView = new ModelAndView("admin/editBook");

            String sBookId = request.getParameter("id");
            Book book = new Book();
            book.setBookId(Integer.parseInt(sBookId));
            if (book.retrieveData()) {
                modelAndView.addObject("book", book);
                AddBookDropDown bookDropDown = new AddBookDropDown();
                DropDown dd_format = new DropDown();
                DropDown dd_language = new DropDown();
                DropDown dd_location = new DropDown();
                dd_format = bookDropDown.getRefList("format", book.getFormat());
                dd_language = bookDropDown.getRefList("language", book.getLanguage());
                dd_location = bookDropDown.getRefList("location", book.getLocation());
                modelAndView.addObject("dd_format", dd_format.getDropDownOption());
                modelAndView.addObject("dd_language", dd_language.getDropDownOption());
                modelAndView.addObject("dd_location", dd_location.getDropDownOption());
            }
        }
        return modelAndView;
    }
}