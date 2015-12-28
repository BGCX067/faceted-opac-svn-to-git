<%
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Faceted OPAC :: Online Public Access Catalog</title>
        <link href="../style/style.css" rel="stylesheet" type="text/css" />
        <link href="../style/facet.css" rel="stylesheet" type="text/css" />
        <link href="../style/newbook.css" rel="stylesheet" type="text/css" />
        <link href="admin.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../script/util.js"></script>
        <style>
            .title {
                width:450px;
                text-align:left;
                font-size:14px;
                margin-right:8px;
                color:black;
            }
            .action {
                width:25px;
                text-align:right;
                font-size:14px;
                padding-right:8px;
            }
            .number{
                color:black;
                width:8px;
                text-align:left;
            }
            .bottom-border{
                border-bottom:1px dashed grayText;
            }
        </style>
        <script type="text/javascript">
            window.onload = function(){loadfnc()};
            function cfm(url)
            {
                if(confirm("Are you sure?"))
                {
                    window.location = url;
                }
            }
            function loadfnc()
            {
                if(gup('title')!="")
                {
                      try
                      {
                          $('input-title').value = gup('title');
                      }catch(e)
                      {
                          
                      }
                }
            }
        </script>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/fragment/admin_header.jsp" %>
        <div id="search_strip">
            <div class="search_query">
                <%@ include file="/WEB-INF/jsp/fragment/searchQuery.jsp" %>
            </div>
            <%@ include file="/WEB-INF/jsp/fragment/searchBar.jsp" %>
        </div>
        <div id="body_area">
        <%@ include file="/WEB-INF/jsp/fragment/adminTask.jsp" %>
            <div class="midarea">
                <div class="head">
                    Please enter title of the book you wish to update:
                </div>
                <form methon="GET" name="book-title" action="updateBook.html">
                    <label for="title" style="font-size:medium;">Title: </label><input id="input-title" name="title" style="width:350px;"/>
                    <input type="submit" value="Search" />
                </form>
                <div class="admin-action">
                    <c:if test="${books != null}">
                    <table>
                        <tr>
                            <th class="title" colspan="4">Book title</th>
                        </tr>
                        <c:forEach var="n" items="${books}" varStatus="a" >
                        <tr>
                            <td class="number bottom-border">${a.count}.</td>
                            <td class="title bottom-border">${n.title}</td>
                            <td class="action bottom-border">
                                <a href="editBook.html?id=${n.bookId}">Edit</a>
                            </td>
                            <td class="action bottom-border">
                                <a href="javascript:cfm('deleteBook.html?id=${n.bookId}')">Delete</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    </c:if>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/fragment/admin_footer.jsp" %>


    </body>
</html>
