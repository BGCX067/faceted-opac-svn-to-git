<script type="text/javascript">
    var facetfunction = function(sField, sValue)
    {
        var sLocation = window.location;
        sLocation = sLocation.toString();
        sLocation = sLocation.replace("/admin/", "/");
        if(/search.html/.test(sLocation))
        {//in search page
            if(sLocation.split("?").length > 1)
            {
                if(sField == 'subject')
                {//allow multiple subject
                    window.location = sLocation + "&" + sField + "=" + sValue;
                }
                else if(gup(sField))
                {//search field already stated
                    var aA = sLocation.split(sField);

                    var aB = aA[aA.length-1].split("&");

                    aB[0] = "="+sValue;

                    aB = aB.join("&");
                    aA[aA.length-1] = aB;

                    aA = aA.join(sField);
                    window.location = aA;
                }
                else
                {
                    window.location = sLocation + "&" + sField + "=" + sValue;
                }
            }else
            {
                window.location = sLocation + "?" + sField + "=" + sValue;
            }
        }
        else
        {
            var aPart = sLocation.split('/');
            aPart[aPart.length-1] = "search.html";
            sLocation = aPart.join('/');
            window.location = sLocation + "?" + sField + "=" + sValue;
        }
    }
</script>

<div id="facet-menu">
    <!-- facet start -->
    <div id="browseDesc">
        Browse by...
    </div>
    <div class="f_container" name="format" id="f_format">
        <div class="f_header" name="format">
            <span id="f_facet_format_toggle" class="headerToggleMinus"></span>
            Format
        </div>
        <div class="f_content" id="f_facet_format" name="format">
            <ul class="f_contentWithBorder">
                <c:forEach var="n" items="${facet_format}" varStatus="a" >
                    <c:if test="${n.count > 0}">
                    <li class="f_subElement">

                        <a href="javascript:facetfunction('format', '${n.value}')">
                            <span class="f_resultNumber">${n.count}</span>
                            <span class="f_facetName">${n.value}</span>
                        </a>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- facet end -->
                     <!-- facet start -->
    <div class="f_container" name="year" id="f_year" style="display:none;">
        <div class="f_header" name="year">
            <span id="f_facet_year_toggle" class="headerToggleMinus"></span>
            Year
        </div>
        <div class="f_content" id="f_facet_year" name="year">
            <ul class="f_contentWithBorder">
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">1</span>
                        <span class="f_facetName">2005-2009</span>
                    </a>
                </li>
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">4</span>
                        <span class="f_facetName">2000-2004</span>
                    </a>
                </li>
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">1</span>
                        <span class="f_facetName">1990-1999</span>
                    </a>
                </li>
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">4</span>
                        <span class="f_facetName">1960-1989</span>
                    </a>
                </li>
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">1</span>
                        <span class="f_facetName">1900-1959</span>
                    </a>
                </li>
                <li class="f_subElement">
                    <a href="#">
                        <span class="f_resultNumber">4</span>
                        <span class="f_facetName">Earlier than 1900</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- facet end -->
                    <!-- facet start -->
    <div class="f_container" name="subject" id="f_subject">
        <div class="f_header" name="subject">
            <span id="f_facet_subject_toggle" class="headerToggleMinus"></span>
            Subject
        </div>
        <div class="f_content" id="f_facet_subject" name="subject">
            <ul class="f_contentWithBorder">
                <c:forEach var="n" items="${facet_subject}" varStatus="a" >
                    <c:if test="${n.count > 0}">
                    <li class="f_subElement">
                        <a href="javascript:facetfunction('subject', '${n.value}')">
                            <span class="f_resultNumber">${n.count}</span>
                            <span class="f_facetName">${n.value}</span>
                        </a>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- facet end -->
                    <!-- facet start -->
    <div class="f_container" name="publisher" id="f_publisher">
        <div class="f_header" name="publisher">
            <span id="f_facet_publisher_toggle" class="headerToggleMinus"></span>
            Publisher
        </div>
        <div class="f_content" id="f_facet_publisher" name="publisher">
            <ul class="f_contentWithBorder">
                <c:forEach var="n" items="${facet_publisher}" varStatus="a" >
                    <c:if test="${n.count > 0}">
                    <li class="f_subElement">
                        <a href="javascript:facetfunction('publisher', '${n.value}')">
                            <span class="f_resultNumber">${n.count}</span>
                            <span class="f_facetName">${n.value}</span>
                        </a>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- facet end -->
                                        <!-- facet start -->
    <div class="f_container" name="language" id="f_language">
        <div class="f_header" name="language">
            <span id="f_facet_language_toggle" class="headerToggleMinus"></span>
            Language
        </div>
        <div class="f_content" id="f_facet_language" name="language">
            <ul class="f_contentWithBorder">
                <c:forEach var="n" items="${facet_language}" varStatus="a" >
                    <c:if test="${n.count > 0}">
                    <li class="f_subElement">
                        <a href="javascript:facetfunction('language', '${n.value}')">
                            <span class="f_resultNumber">${n.count}</span>
                            <span class="f_facetName">${n.value}</span>
                        </a>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- facet end -->
                                                            <!-- facet start -->
    <div class="f_container" name="author" id="f_author">
        <div class="f_header" name="author">
            <span id="f_facet_author_toggle" class="headerToggleMinus"></span>
            Author
        </div>
        <div class="f_content" id="f_facet_author" name="author">
            <ul class="f_contentWithBorder">
                <c:forEach var="n" items="${facet_author}" varStatus="a" >
                    <c:if test="${n.count > 0}">
                    <li class="f_subElement">
                        <a href="javascript:facetfunction('author', '${n.value}')">
                            <span class="f_resultNumber">${n.count}</span>
                            <span class="f_facetName">${n.value}</span>
                        </a>
                    </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- facet end -->

</div>