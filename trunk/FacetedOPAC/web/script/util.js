var $ = function(sId, oDoc)
{
    oDoc = oDoc || document;
    return oDoc.getElementById(sId);
};

function getElementsByClassName(strClass, strTag, objContElm) {
    strTag = strTag || "*";
    objContElm = objContElm || document;
    var objColl = objContElm.getElementsByTagName(strTag);
    if (!objColl.length &&  strTag == "*" &&  objContElm.all) objColl = objContElm.all;
    var arr = new Array();
    var delim = strClass.indexOf('|') != -1  ? '|' : ' ';
    var arrClass = strClass.split(delim);
    for (var i = 0, j = objColl.length; i < j; i++) {
        var arrObjClass = objColl[i].className.split(' ');
        if (delim == ' ' && arrClass.length > arrObjClass.length) continue;
        var c = 0;
            comparisonLoop:
            for (var k = 0, l = arrObjClass.length; k < l; k++) {
                for (var m = 0, n = arrClass.length; m < n; m++) {
                    if (arrClass[m] == arrObjClass[k]) c++;
                    if (( delim == '|' && c == 1) || (delim == ' ' && c == arrClass.length)) {
                        arr.push(objColl[i]);
                        break comparisonLoop;
                    }
                }
            }
    }
    return arr;
}

var gup = function( name ) {

    var results = (new RegExp("[\\?&]"+name+"=([^&#]*)")).exec(window.location.href);

    if ( results == null ) {
        return ""
        }

    else {
        return results[1]
        }

};

function random_number()
{
    var date = new Date();
    return date.getTime() + '_' + Math.floor(Math.random()*1001);
}