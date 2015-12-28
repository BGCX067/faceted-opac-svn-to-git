<script type="text/javascript">
    var $ = function(sId)
    {
        return document.getElementById(sId);
    }
    function search_keyword()
    {
        if($('keyword-field').value === "")
        {
            alert("Keyword should not be empty.")
            $('keyword-field').focus();
            return void(0);
        }
        if($('search-field').value === "")
        {
            alert("Please select a search field.");
            $('search-field').focus();
            return void(0);
        }
        var sLocation = window.location;
        sLocation = sLocation.toString();
        sLocation = sLocation.replace("/admin/", "/");
        if(/search.html/.test(sLocation))
        {//in search page
            if(sLocation.split("?").length > 1)
            {
                if($('search-field').value == 'subject')
                {//allow multiple subject
                    window.location = sLocation + "&" + $('search-field').value + "=" + $('keyword-field').value;
                }
                else if(gup($('search-field').value))
                {//search field already stated
                    var aA = sLocation.split($('search-field').value);
                    
                    var aB = aA[aA.length-1].split("&");
                    
                    aB[0] = "="+$('keyword-field').value;
                    
                    aB = aB.join("&");
                    aA[aA.length-1] = aB;
                    
                    aA = aA.join($('search-field').value);
                    window.location = aA;
                }
                else
                {
                    window.location = sLocation + "&" + $('search-field').value + "=" + $('keyword-field').value;
                }
            }else
            {
                window.location = sLocation + "?" + $('search-field').value + "=" + $('keyword-field').value;
            }
        }
        else
        {
            var aPart = sLocation.split('/');
            aPart[aPart.length-1] = "search.html";
            sLocation = aPart.join('/');
            window.location = sLocation + "?" + $('search-field').value + "=" + $('keyword-field').value;
        }
    }
</script>

<div class="search_area">
    <div class="search_box">

        <label>
            <input name="keyword" type="text" class="searchtextbox" id="keyword-field"/>
        </label>
        <span style="width:8px;height:inherit;"></span>
        <label>
            <select name="field" type="text" class="searchtextbox searchfield" id="search-field">
                <option disabled=""></option>
                <option value="author">Author</option>
                <option value="subject">Subject</option>
                <option value="publisher">Publisher</option>
            </select>
        </label>

    </div>
    <div class="search_go">
        <div align="center"><a href="javascript:search_keyword();" id="keyword-search"
                               class="go">GO</a></div>
    </div>
</div>