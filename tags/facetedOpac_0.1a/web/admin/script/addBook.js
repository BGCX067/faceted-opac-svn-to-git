var Book = {

    init: function(){
        this.click_expand_function('add-another-author', 'book-authors-list', HTML_CODE.Author);
        this.click_expand_function('add-another-isbn', 'book-isbn-list', HTML_CODE.ISBN);
        this.click_expand_function('add-another-subject', 'book-subject-list', HTML_CODE.Subject);
        
        this.click_add_option('add-option-format', 'book-format-list', 'format');
        this.click_add_option('add-option-location', 'book-location-list', 'location');
        this.click_add_option('add-option-language', 'book-language-list', 'language');
        $('book-location-list').onchange = function()
        {
            if($('book-location-list')[$('book-location-list').selectedIndex].text.toLowerCase() === 'online')
            {
                $('link-list-container').style.display = 'block';
            }
            else
            {
                $('link-list-container').style.display = 'none';
            }
        }
        $('reset-book').onclick = function()
        {
            resetCheckStatus();
        }
        $('register-book').onclick = function()
        {
            if(!verifyData())
            {
                return false
            }
            else
            {
                document.getElementById('submit-form').click();
            }
        };
    },

    click_expand_function: function(sButtonId, sExpandAreaId, obj, oDoc){
        oDoc = oDoc || document;
        $(sButtonId, oDoc).onclick = function()
        {
            var oEle = document.createElement('input');
            oEle.className = obj.className;
            oEle.name = obj.name;
            oEle.type = obj.type;
            $(sExpandAreaId, oDoc).appendChild(oEle);
        };
    },

    click_add_option: function(sButtonId, sSelectId, sType, oDoc)
    {
        oDoc = oDoc || document;
        $(sButtonId, oDoc).onclick = function()
        {
            var newOption = prompt('Enter a new value for the '+sType+':', '');
            if(!newOption){
                return;
            }
            var opt = oDoc.getElementById(sSelectId);
            opt.options[opt.length] = new Option(newOption, newOption);
            opt.options[opt.length-1].selected = true;
            if(sType.toLowerCase() === 'location')
            {
                $('link-list-container').style.display = 'none';
            }
        }
    }
};

var HTML_CODE = {
    Author: {
        className:'input-reg-book-shorter author-list',
        type:'text',
        name:'author'
    },//'<input class="input-reg-book-shorter" type="text" name="author"/>',
    ISBN: {
        className:'input-reg-book-shorter isbn-list',
        type:'text',
        name:'ISBN'
    } ,//'<input class="input-reg-book-shorter" type="text" name="ISBN"/>',
    Subject: {
        className:'input-reg-book-shorter subject-list',
        type:'text',
        name:'subject'
    }//'<input class="input-reg-book-shorter" type="text" name="subject" />'
};

var verifyData = function(oDoc)
{
    oDoc = oDoc || document;
    var bContinue = true;
    var bSomeFieldFilled = false;
    var verifyList = ['subject','isbn','language','publisher','year','imprint', 'location','format','call-number','author','title']; //x-list, x-list-invalid
    resetCheckStatus();
    //check empty
    for(var i = 0; i < verifyList.length; i ++)
    {
        var classList = getElementsByClassName(verifyList[i]+'-list');
        
        for(var j = 0; j < classList.length; j++)
        {
            bSomeFieldFilled = false;
            if(classList[j].value !== '')
            {
                bSomeFieldFilled = true;
                break;
            }
        }
        if(!bSomeFieldFilled)
        {//at least one has the value
            $(verifyList[i]+'-list-invalid', oDoc).style.display = 'inline';
            bContinue = false;
        }
    }
    //other cases
    if($('link-list-container',oDoc).style.display !== 'none')
    {
        if(getElementsByClassName('link-list')[0].value === '')
        {
            bContinue = false;
            //$('link-list-container').style.display  = 'block';
            $('link-list-invalid', oDoc).style.display = 'inline';
        }
    }
    //check value
    if(!/^(\d{4})$/.test($('year-4-digit').value))
    {
        $('year-list-invalid').style.display = 'inline';
        bContinue = false;
    }
    return bContinue;
};

var resetCheckStatus = function()
{
    var aInvalidTag = getElementsByClassName('reg-book-invalid');
    for(var i = 0; i < aInvalidTag.length; i++)
    {
        aInvalidTag[i].style.display = 'none';
    }
}
