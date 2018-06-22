package cl.metlife.auth;

import cl.blueprintsit.framework.auth.*;


import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 * Created by BluePrints Developer on 22-03-2017.
 */
@Stateless
public class MetlifeAndTableAuthorizationMethod extends AuthorizationMethod {

    @EJB
    MetlifeAuthorizationMethod metlifeAuthorizationMethod;

    @EJB
    TableAuthorizationMethod tableAuthorizationMethod;

    @EJB
    UserManager userManager;


    @Override
    public User getUser(String username) throws UserNotFoundException {

        User metlifeUser;

        try {
            metlifeUser = metlifeAuthorizationMethod.getUser(username);

        }catch (UserNotFoundException e){
            metlifeUser = null;
        }catch (RuntimeException e){
            metlifeUser = null;
        }

        User tableUser;
        try {
            tableUser = tableAuthorizationMethod.getUser(username);
        }catch (UserNotFoundException e){
            tableUser = null;
        }

        if(metlifeUser == null && tableUser == null)
            throw new UserNotFoundException("usuario no existe en ninguna fuente");

        else if(tableUser == null)
            try {
                userManager.createUserInDBForTracking(metlifeUser);
            }catch (UsernameExistsException e){
                throw new UserNotFoundException("Error que no deberia pasar nunca", e);
            }

        else return tableUser;

        return metlifeUser;

    }


    @Override
    public void lockUser(String username) throws UserNotFoundException {
        tableAuthorizationMethod.lockUser(username);
    }

    @Override
    public void unlockUser(String username) throws UserNotFoundException {
        tableAuthorizationMethod.unlockUser(username);
    }

    @Override
    public void markLogin(String username) throws UserNotFoundException {
        tableAuthorizationMethod.markLogin(username);

    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException {
        tableAuthorizationMethod.setPassword(username,password);
    }



}
