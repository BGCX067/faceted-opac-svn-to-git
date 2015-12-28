/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mp.hsrky.facetedOpac.service;

import mp.hsrky.facetedOpac.command.LoginCommand;


/**
 *
 * @author Administrator
 */
public class LoginValidator {

    public boolean authenticate(Object obj)
    {
        LoginCommand loginCommand = (LoginCommand) obj;
        if((loginCommand.getUsername().equals("username")== true)
                && (loginCommand.getPassword().equals("password") == true))
        {//user validated
            return true;
        }
        else
        {
            return false;
        }
    }
}
