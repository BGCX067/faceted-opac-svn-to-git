function Facet()
{
    this.init = function()
    {
        var aFacet = getElementsByClassName('f_header');
        for(var i = 0; i < aFacet.length; i++)
        {
            aFacet[i].onclick = facetController(aFacet[i].getAttribute('name'));

        }
        if(gup('subject'))
        {
            //$('f_subject').style.display = 'none';
        }
        if(gup('publisher'))
        {
            $('f_publisher').style.display = 'none';
        }
        if(gup('author'))
        {
            $('f_author').style.display = 'none';
        }
        if(gup('language'))
        {
            $('f_language').style.display = 'none';
        }
        if(gup('format'))
        {
            $('f_format').style.display = 'none';
        }
    };

    /**
     *
     */
    var facetController = function(sName)
    {
        return function()
        {
            hideShowFacet(sName);
        }
    };
    /*
     * 
     */

    var hideShowFacet = function(sShowName, sClassName)
    {
    sClassName = sClassName || 'f_content';
    var aSubFacet = getElementsByClassName(sClassName);
    //console.log(aSubFacet);
    for(var i = 0; i < aSubFacet.length; i++)
    {
    if(aSubFacet[i].getAttribute('name') === sShowName)
    {
    if(aSubFacet[i].style.display == 'block')
    {
    document.getElementById('f_facet_'+sShowName+'_toggle').className = "headerToggleMinus"
    aSubFacet[i].style.display = 'none';
    }
    else
    {
    document.getElementById('f_facet_'+sShowName+'_toggle').className = "headerTogglePlus"
    aSubFacet[i].style.display = 'block';
    }
    }
    else
    {
    document.getElementById('f_facet_'+aSubFacet[i].getAttribute('name')+'_toggle').className = 'headerToggleMinus';
    aSubFacet[i].style.display = 'none';
    }
    }
    };


}

