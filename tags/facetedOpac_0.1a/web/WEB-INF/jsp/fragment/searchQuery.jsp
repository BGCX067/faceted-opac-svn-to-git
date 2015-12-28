<c:if test="${queryItems != null}">
<script type="text/javascript">
    var removeFacet = function(field, value)
    {
        var sUrl = window.location.toString();
        var sStartUrl = sUrl.split('?');
        var sEndUrl = sStartUrl[1];
        sStartUrl = sStartUrl[0];//f=g&k=h
        aQueries = sEndUrl.split('&'); //f=g, k=h
        var sQueryField, sQueryValue ;
        //console.log("received:"+field+' : '+value);
        for(var i = 0; i < aQueries.length; i++)
        {
            sQueryField = aQueries[i].split('=')[0];
            sQueryValue = aQueries[i].split('=')[1];
            //console.log("compared:" + sQueryField + " : " + sQueryValue);
            if((unescape(sQueryField) === unescape(field)) && (unescape(sQueryValue) === unescape(value)))
            {
                aQueries.splice(i,1);
                break;
            }
        }
        aQueries = aQueries.join('&');
        sUrl = sStartUrl + '?' + aQueries;
        var aTest = sUrl.split('?');
        if(aTest[1] === "")
        {
            sUrl = sUrl.replace(/\?/,'');
        }
        //console.log(sUrl);
        window.location = sUrl;
    };
</script>

<span style="font-size:medium;">You searched for:<br/></span>
<c:forEach var="n" items="${queryItems}" varStatus="a" >
<span style="margin-right:12px;">
    <NOBR>
        <span style="color:red">${n.field}:&nbsp;</span>
        <span style="font-weight:normal;font-size:small;color:navy;">${n.value}</span>&nbsp;
        (<a href="javascript:removeFacet('${n.field}','${n.value}')" style="font-weight:normal;">remove</a>),
    </NOBR>
</span>
</c:forEach>
</c:if>