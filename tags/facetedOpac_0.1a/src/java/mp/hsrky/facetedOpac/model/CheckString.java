/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.model;

/**
 *
 * @author Administrator
 */
public class CheckString {

    public CheckString() {
    }

    public String verify(String string)
    {//simple check
        if(!("".compareTo(string) > 0)){
        string = string.replace("#", "");
        string = string.replace("'", "");
        string = string.replace("\"", "");
        }

        return string;
    }

}
