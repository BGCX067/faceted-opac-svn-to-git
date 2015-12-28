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
        <script type="text/javascript">
            window.onload = function()
            {
                document.getElementById('upload-pic').onclick = function()
                {//upload-pic, image-field
                    var oImage = document.getElementById('image-field');
                    
                    if(!oImage.value)
                    {
                        alert("Please select an image or click the 'Skip' link to proceed.")
                        return false;
                    }
                }
            }
        </script>
    </head>

    <body>
        <%@ include file="/WEB-INF/jsp/fragment/admin_header.jsp" %>
        <div id="search_strip">
            <div class="search_query">
                
            </div>
            <%@ include file="/WEB-INF/jsp/fragment/searchBar.jsp" %>
        </div>
        <div id="body_area">
        <%@ include file="/WEB-INF/jsp/fragment/adminTask.jsp" %>
            <div class="midarea">
                <div class="head">Add book cover for <br />${bookTitle}</div>
                <div class="admin-action">
                    Choose a picture:
                    <c:if test="${empty message}">*${message}</c:if>
                    <form method="POST" action="addbookPic.html?bookId=${bookId}&callNumber=${callNumber}" enctype="multipart/form-data">
                        <table style="margin-top:16px;">
                            <tr valid="top">
                                <td class="insert-new-book-left">Cover picture:</td>
                                <td class="insert-new-book-right"><input id="image-field" type="file" class="input-reg-book" size="25" name="image" />
                                    <input type="hidden" name="MAX_FILE_SIZE" value="500" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="left" style="padding-top:12px;">
                                    <input id="upload-pic" type="submit" value="Upload" style="width:120px;"/>
                                    <a href="home.html">Skip</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jsp/fragment/admin_footer.jsp" %>


    </body>
</html>
