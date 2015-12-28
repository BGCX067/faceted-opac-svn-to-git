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
                <c:if test="${not started}">
                    <div class="midarea">
                        <div class="result-head">Please select a keyword to begin.</div>
                    </div>
                </c:if>
                <c:if test="${not result}">
                    <div class="midarea">
                        <div class="result-head">No results.</div>
                    </div>
                </c:if>
                <c:if test="${result}">
                    <%@ include file="/WEB-INF/jsp/fragment/book_result.jsp" %>
                </c:if>
            </div>
            <%@ include file="/WEB-INF/jsp/fragment/footer.jsp" %>


    </body>
</html>
