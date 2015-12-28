<div class="midarea">
    <div class="result_head"> Results <span class="bold">1 - ${totalBooks}</span> of <span class="bold">${totalBooks}</span> </div>
    <div class="body_textarea">
        <div class="book_list">
            <c:forEach var="n" items="${books}" varStatus="a" >
            <div class="book_item">
                <a href="book.html?id=${n.bookId}" class="book-list-pic-holder">
                    <img src="${n.image}" class="book-list-pic"/>
                </a>
                <div class="book_list_name"><span style="position:absolute;font-size:16px;">${a.count}. </span>
                    <a href="book.html?id=${n.bookId}" class="book-title-link">${n.title}</a>
                </div>
                <div class="book_list_desc">
                    <table>
                        <tr>
                            <td class="book-list-left">Author</td>
                            <td class="book-list-right">
                                ${n.plainAuthor}
                            </td>
                        </tr>
                        <tr>
                            <td class="book-list-left">Year</td>
                            <td class="book-list-right">${n.year}</td>
                        </tr>
                        <tr>
                            <td class="book-list-left">Format</td>
                            <td class="book-list-right">${n.format}</td>
                        </tr>
                        <tr>
                            <td class="book-list-left">Call number</td>
                            <td class="book-list-right">${n.call_number}</td>
                        </tr>
                    </table>

                </div>

            </div>
            </c:forEach>
        </div>
    </div>
</div>