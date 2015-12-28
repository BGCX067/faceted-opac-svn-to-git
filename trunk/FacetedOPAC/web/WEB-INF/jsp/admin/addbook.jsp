<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires",0);
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
        <script type="text/javascript" src="script/addBook.js"></script>
        <script type="text/javascript">
            window.onload = function()
            {
                Book.init();
            };
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
                <div class="head">Add a new book</div>
                <div class="admin-action">
                    Please enter the details in following form (* indicates required field):${message}
                   <c:if test="${empty message}"></c:if>
                    <form method="POST" action="addBook.html">
                        <table style="margin-top:16px;">
                            <tr valign="top">
                                <td  class="insert-new-book-left">Book title*:</td>
                                <td class="insert-new-book-right">
                                    <input name="title" class="input-reg-book title-list" type="text" />
                                    <span style="display:none;" id="title-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Call number*:</td>
                                <td class="insert-new-book-right">
                                    <input name="call_number" class="input-reg-book call-number-list" type="text" />
                                    <span style="display:none;" id="call-number-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>

                            <tr valign="top">
                                <td  class="insert-new-book-left">Author*:</td>
                                <td class="insert-new-book-right">
                                    <span id="book-authors-list"><input name="author" class="input-reg-book-shorter author-list" type="text"/></span>
                                    <input id="add-another-author" class="reg-book-add" type="button" value="Add another"/>
                                    <span style="display:none;" id="author-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>

                            <tr valign="top">
                                <td  class="insert-new-book-left">Format*:</td>
                                <td class="insert-new-book-right">
                                    <select name="format" id="book-format-list" class="input-reg-book-shorter format-list">
                                        <option disabled></option>
                                        <c:forEach var="n" items="${dd_format}" varStatus="a">
                                            ${n}
                                        </c:forEach>
                                    </select>
                                    <input id="add-option-format" class="reg-book-add" type="button" value="Add new"/>
                                    <span style="display:none;" id="format-list-invalid" class="reg-book-invalid">*select one option</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Location*:</td>
                                <td class="insert-new-book-righ">
                                    <select name="location" id="book-location-list" class="input-reg-book-shorter location-list">
                                        <option disabled></option>
                                        <c:forEach var="n" items="${dd_location}" varStatus="a">
                                            ${n}
                                        </c:forEach>
                                    </select>
                                    <input id="add-option-location" class="reg-book-add" type="button" value="Add new"/>
                                    <span style="display:none;" id="location-list-invalid" class="reg-book-invalid">*select one option</span>
                                    <span style="display:none;" id="link-list-container"><br />Link: <input class="input-reg-book link-list" type="text" name="location_link" />
                                        <span style="display:none;" id="link-list-invalid" class="reg-book-invalid">*required</span>
                                    </span>
                                 </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Imprint*:</td>
                                <td class="insert-new-book-right">
                                    <input name="imprint" class="input-reg-book imprint-list" type="text" />
                                    <span style="display:none;" id="imprint-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Year*:</td>
                                <td class="insert-new-book-right">
                                    <input name="year" class="input-reg-book-shorter year-list" id="year-4-digit" type="text" />
                                    <span style="display:none;" id="year-list-invalid" class="reg-book-invalid">*invalid format (<i>example: 1999</i>)</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Publisher*:</td>
                                <td class="insert-new-book-right">
                                    <input name="publisher" class="input-reg-book publisher-list" type="text" />
                                    <span style="display:none;" id="publisher-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Language*:</td>
                                <td class="insert-new-book-right">
                                    <select name="language" id="book-language-list" class="input-reg-book-shorter language-list">
                                        <option disabled></option>
                                        <c:forEach var="n" items="${dd_language}" varStatus="a">
                                            ${n}
                                        </c:forEach>
                                    </select>
                                <input id="add-option-language" class="reg-book-add" type="button" value="Add new"/>
                                <span style="display:none;" id="language-list-invalid" class="reg-book-invalid">*select one option</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">ISBN*:</td>
                                <td class="insert-new-book-right">
                                    <span id="book-isbn-list"><input name="ISBN" class="input-reg-book-shorter isbn-list" type="text" /></span>
                                    <input id="add-another-isbn" class="reg-book-add" type="button" value="Add another"/>
                                    <span style="display:none;" id="isbn-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Description:</td>
                                <td class="insert-new-book-right">
                                    <textarea name="desc" class="input-reg-book"></textarea>
                                </td>
                            </tr>
                            <tr valign="top">
                                <td  class="insert-new-book-left">Subject*:</td>
                                <td class="insert-new-book-right">
                                    <span id="book-subject-list"><input name="subject" class="input-reg-book-shorter subject-list" type="text" /></span>
                                    <input id="add-another-subject" class="reg-book-add" type="button" value="Add another"/>
                                    <span style="display:none;" id="subject-list-invalid" class="reg-book-invalid">*required</span>
                                </td>
                            </tr>
                            <tr valid="top" style="display:none;">
                                <td class="insert-new-book-left">Cover picture:</td>
                                <td class="insert-new-book-right"><input type="file" class="input-reg-book" size="25" name="image" />
                                <input type="hidden" name="MAX_FILE_SIZE" value="500" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="left" style="padding-top:12px;">
                                    <input id="register-book" type="button" value="Register" style="width:120px;"/>
                                    <input type="reset" id="reset-book" style="width:120px" />
                                    <input type="submit" id="submit-form" style="display:none;" />
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
