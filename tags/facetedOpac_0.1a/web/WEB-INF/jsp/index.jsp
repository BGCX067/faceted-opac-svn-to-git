<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Faceted OPAC :: Online Public Access Catalog</title>
        <link href="style/style.css" rel="stylesheet" type="text/css" />
        <link href="style/facet.css" rel="stylesheet" type="text/css" />
        <link href="style/newbook.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="script/util.js"></script>
        <script type="text/javascript" src="script/facet.js"></script>
        <script type="text/javascript">
            window.onload = function()
            {
                var facet = new Facet();
                facet.init();
            }
        </script>
    </head>

    <body>
        <%--
         <%=request.getAttribute("message") %>
         ${message}
        --%>
        
        <%@ include file="/WEB-INF/jsp/fragment/header.jsp" %>
            <div id="search_strip">
                <div class="search_query">
                    <%@ include file="/WEB-INF/jsp/fragment/searchQuery.jsp" %>
                </div>
                <%@ include file="/WEB-INF/jsp/fragment/searchBar.jsp" %>
            </div>
            <div id="body_area">
                <div class="left">
                    <%@ include file="/WEB-INF/jsp/fragment/facetMenu.jsp" %>
                </div>
                <div class="midarea">
                    <div class="head"> New Books </div>
                    <div class="body_textarea">
                        <%@ include file="/WEB-INF/jsp/fragment/newBook.jsp" %>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/jsp/fragment/footer.jsp" %>
        

    </body>
</html>
