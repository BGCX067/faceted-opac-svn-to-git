<table>
    
    
    
    <tr valign="middle" align="center">
    <c:forEach var="book" items="${books}" varStatus="a" begin="0" end="2" >
        <td class="newBookItem">
            <div style="width:inherit;height:inherit;">
                <a href="book.html?id=${book.bookId}" title="${book.title}">
                    <img src="${book.image}" class="newBookImage" style="border:none !important;" />
                </a>
                <div class="newBookNameContainer">
                    <a href="book.html?id=${book.bookId}" class="newBookName"
                       alt="${book.title}"
                       title="${book.title}">
                        <c:out value="${book.shortTitle}" />
                    </a>
                </div>
            </div>
        </td>
    </c:forEach>
    </tr>
    <tr valign="middle" align="center">
    <c:forEach var="book" items="${books}" varStatus="a" begin="3" >
        <td class="newBookItem">
            <div style="width:inherit;height:inherit;">
                <a href="book.html?id=${book.bookId}" title="${book.title}">
                    <img src="${book.image}" class="newBookImage" style="border:none !important;" />
                </a>
                <div class="newBookNameContainer">
                    <a href="book.html?id=${book.bookId}" class="newBookName"
                       alt="${book.title}"
                       title="${book.title}">
                        <c:out value="${book.shortTitle}" />
                    </a>
                </div>
            </div>
        </td>
    </c:forEach>
    </tr>
    
    
</table>