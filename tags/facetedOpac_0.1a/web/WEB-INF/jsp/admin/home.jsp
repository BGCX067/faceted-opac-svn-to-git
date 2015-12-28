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
                <div class="head">Welcome <%= session.getAttribute( "name" ) %> </div>
                <div class="admin-action">
                    Please select a maintenance task from the menu at left hand side.
                </div>
            </div>
        </div>
    <%@ include file="/WEB-INF/jsp/fragment/admin_footer.jsp" %>


    </body>
</html>
