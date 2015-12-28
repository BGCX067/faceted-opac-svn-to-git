<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires",0);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Faceted OPAC :: Online Public Access Catalog</title>
        <link href="../style/style.css" rel="stylesheet" type="text/css" />
        <link href="../style/facet.css" rel="stylesheet" type="text/css" />
        <link href="../style/newbook.css" rel="stylesheet" type="text/css" />
        <link href="admin.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
        <form id="form1" name="form1" method="post" action="">
            <%@ include file="/WEB-INF/jsp/fragment/admin_header.jsp" %>
            <div id="search_strip">
                <div class="search_query">
                    <%@ include file="/WEB-INF/jsp/fragment/searchQuery.jsp" %>
                </div>
                <%@ include file="/WEB-INF/jsp/fragment/searchBar.jsp" %>
            </div>
            <div id="body_area" style="margin-left:auto;margin-right:auto;">
                <div class="loginBox" style="background-color:transparent; border-color:transparent;color:red;font-size:medium;">
                   Error! Your previous action was not successfully. <br />
                   ${message}
                </div>
            </div>
<%@ include file="/WEB-INF/jsp/fragment/admin_footer.jsp" %>
        </form>

    </body>
</html>
