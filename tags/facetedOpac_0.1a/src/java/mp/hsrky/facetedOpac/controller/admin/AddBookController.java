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

public class AddBookController implements Controller {

    private Book book = new Book();
    private ModelAndView modelAndView;
    private HttpSession session;
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();
        System.out.print("add book session: " + session.getAttribute("username"));

        if(session.getAttribute("username") == null)
        {//session not started
            response.sendRedirect("login.html");
        }
        if (request.getParameter("title") != null) {
            AddBook addBook = new AddBook();
            try {
                book.setAuthor(request.getParameterValues("author"));
                //case description
                book.setDescription(request.getParameter("desc"));
                //case call number
                book.setCall_number(request.getParameter("call_number"));
                //case format
                try
                {
                    int iFormat = Integer.parseInt(request.getParameter("format"), 10);
                    if(addBook.verifyRef("format", iFormat))
                    {
                        book.setIFormat(iFormat);
                    }
                    else
                    {
                        book.setIFormat(addBook.addRef("format", Integer.toString(iFormat)));
                    }
                }catch(Exception e)
                {
                    book.setIFormat(addBook.addRef("format", request.getParameter("format")));
                }
                //book.setImage(request.getParamter("image"));

                //case imprint
                book.setImprint(request.getParameter("imprint"));
                //case isbn
                book.setIsbn(request.getParameterValues("ISBN"));
                //case language
                try
                {
                    int iLanguage = Integer.parseInt(request.getParameter("language"), 10);
                    if(addBook.verifyRef("language", iLanguage))
                    {
                        book.setILanguage(iLanguage);
                    }
                    else
                    {
                        int iNewLanguage = addBook.addRef("language", Integer.toString(iLanguage));
                        book.setILanguage(iNewLanguage);
                    }
                } catch (Exception e) {
                    book.setILanguage(addBook.addRef("language", request.getParameter("language")));
                }
                //case location
                try
                {
                    int iLocation = Integer.parseInt(request.getParameter("location"), 10);
                    if(addBook.verifyRef("location", iLocation))
                    {
                        book.setILocation(iLocation);
                    }
                    else
                    {//if user enter integer as location
                        int iNewLocation = addBook.addRef("location", Integer.toString(iLocation));
                        book.setILocation(iNewLocation);
                    }
                }catch (Exception e){
                    //if the location is new
                    book.setILocation(addBook.addRef("location", request.getParameter("location")));
                }
                //case location link (case when location is online only)
                if (request.getParameter("location_link") != null) {
                    book.setLocation_link(request.getParameter("location_link"));
                }
                else
                {
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

            
            /* insert record */
            if(addBook.register(book))
            {//add success
                modelAndView = new ModelAndView("admin/addbookPic");
                modelAndView.addObject("bookId", book.getBookId());
                modelAndView.addObject("callNumber", book.getCall_number());
                modelAndView.addObject("bookTitle", book.getTitle());
            }
            else
            {//fail to insert into db
                modelAndView = new ModelAndView("admin/fail");
                modelAndView.addObject("message", "<br /> - Add new book record failed. <a href='addBook.html'>Please try again.</a>");
            }
        }
        else
        {//no input - create form - drop down
            System.out.print("Add book controller: no input detected");
            AddBookDropDown bookDropDown = new AddBookDropDown();
            DropDown dd_format = new DropDown();
            DropDown dd_language = new DropDown();
            DropDown dd_location = new DropDown();
            dd_format = bookDropDown.getRefList("format");
            dd_language = bookDropDown.getRefList("language");
            dd_location = bookDropDown.getRefList("location");

            modelAndView = new ModelAndView("admin/addbook");
            modelAndView.addObject("dd_format", dd_format.getDropDownOption());
            modelAndView.addObject("dd_language", dd_language.getDropDownOption());
            modelAndView.addObject("dd_location", dd_location.getDropDownOption());
        }
        return modelAndView;
    }
}