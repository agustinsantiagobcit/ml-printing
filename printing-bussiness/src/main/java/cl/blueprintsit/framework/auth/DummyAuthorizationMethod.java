package cl.blueprintsit.framework.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
@Stateless
public class DummyAuthorizationMethod extends AuthorizationMethod {

    private static final Logger logger = LoggerFactory.getLogger(DummyAuthorizationMethod.class);

    public User getUser(String username) throws UserNotFoundException {

        if("bpadmin".equals(username) )
            return makeDummyAdminUser();

        if("ejecutorml".equals(username) )
            return makeDummyEjecutorMlUser();

        throw new UserNotFoundException("Usuario no existe. pruebe con bpadmin");
    }

    @Override
    public void lockUser(String username) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unlockUser(String username) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void markLogin(String username) throws UserNotFoundException {
        logger.info("User {} logged in",username);
    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException {
        throw new UnsupportedOperationException();
    }

    private User makeDummyAdminUser() {
        User user = new User();

        user.setUsername("bpadmin");
        user.setEmail("admin@blueprintsit.cl");
        user.setName("Usuario Dummy Admin");

        Group group = new Group();
        group.setName("Admins");
        group.setDescription("Grupo Dummy Admins");


        user.getGroups().add(group);

        return user;
    }


    private User makeDummyEjecutorMlUser() {
        User user = new User();

        user.setUsername("ejecutorml");
        user.setEmail("ejecutor@blueprintsit.cl");
        user.setName("Ejecutor MetLife");
        Group group = new Group();

        group.setName("Ejecutores");
        group.setDescription("Grupo Dummy Ejecutores");


        user.getGroups().add(group);

        return user;
    }



}
