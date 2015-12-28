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
                    <!-- navigation menu -->
                    <div class="comments_area">
                        <div class="Services_head">
                            <div align="right">Resource Links</div>
                            <div class="comments_text">
                                <div class="more-resource">
                                    Search using Google<br />
                                    <a class="comments_link" target="_blank" href="http://www.google.com.my/search?hl=en&q=${book.title}">http://www.google.com.my/search?hl=en&q=${book.title}</a>
                                </div>

                                <div class="more-resource">
                                    Find preview in Google Book <br />
                                    <a class="comments_link" target="_blank" href="http://books.google.com/books?q=${book.title}">http://books.google.com/books?q=${book.title}</a>
                                </div>

                                <div class="more-resource">
                                    Find books in Amazon <br />
                                    <a class="comments_link" target="_blank" href="http://www.amazon.com/s/ref=nb_ss_b?url=search-alias%3Dstripbooks&field-keywords=${book.title}">http://www.amazon.com/s/ref=nb_ss_b?url=search-alias%3Dstripbooks&field-keywords=${book.title}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end navigation menu -->
                </div>
                <div class="midarea">
                    <div class="head"> ${book.title} </div>
                    <div class="body_textarea">
                        <div class="book_info_left" style="float:left;margin-right:16px;">
                            <div class="book_left_upper" style="width:240px;height:240px;">
                                <img src="${book.image}" class="BookImage" width="240" height="240"/>
                            </div>
                            <div class="book_left_lower">
                                <div id="call_number_container">
                                    <span id="call_number_text" class="below_book_left">Call Number:</span>
                                    <span id="call_number" class="below_book_right">${book.call_number}</span>
                                </div>
                                <div id="location">
                                    <span id="location_text" class="below_book_left">Location:</span>
                                    <span id="location" class="below_book_right">
                                        <c:choose>
                                            <c:when test="${not empty book.location_link}">
                                                <a href="${book.location_link}" class="online-resource"
                                                   title="${book.location_link}">
                                                    ${book.location}
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                            ${book.location}
                                            </c:otherwise>
                                        </c:choose>

                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="book_info_right">
                            <table class="book-details">
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Author:</td>
                                    <td class="bookInfo">
                                        <c:forEach var="n" items="${book.author}" varStatus="a" >
                                            <a href="search.html?author=${n}">
                                                <c:out value="${n}"/>
                                            </a>
                                            <c:if test="${!a.last}">,&nbsp;</c:if>
                                            <c:if test="${a.last}">.</c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Format:</td>
                                    <td class="bookInfo">
                                        <a href="search.html?format=${book.format}">${book.format}</a>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Imprint:</td>
                                    <td class="bookInfo">${book.imprint}</td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Publisher:</td>
                                    <td class="bookInfo">
                                        <a href="search.html?publisher=${book.publisher}">${book.publisher}</a>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Year:</td>
                                    <td class="bookInfo">
                                        <a href="search.html?year=${book.year}">${book.year}</a>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Language:</td>
                                    <td class="bookInfo">
                                        <a href="search.html?language=${book.language}">${book.language}</a>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Description:</td>
                                    <td class="bookInfo">${book.description}</td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">ISBN(s):</td>
                                    <td class="bookInfo">
                                        <c:forEach var="n"  items="${book.isbn}" varStatus="a" >
                                            <c:out  value="${n}"/>
                                            <c:if test="${!a.last}">,</c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr class="bookrow">
                                    <td class="bookInfoDesc">Subject(s):</td>
                                    <td class="bookInfo">
                                        <c:forEach var="n" items="${book.subject}" varStatus="a" >
                                            <a href="search.html?subject=${n}"><c:out value="${n}"/></a>
                                            <c:if test="${!a.last}">, </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="/WEB-INF/jsp/fragment/footer.jsp" %>

    </body>
</html>
