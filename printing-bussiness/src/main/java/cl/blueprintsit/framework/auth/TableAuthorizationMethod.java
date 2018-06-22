package cl.blueprintsit.framework.auth;

import cl.metlife.printing.persistence.dao.UserDAO;
import cl.blueprintsit.utils.encryption.Encryption;
import cl.blueprintsit.utils.encryption.EncryptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
@Stateless
public class TableAuthorizationMethod extends AuthorizationMethod {

    private static final Logger logger = LoggerFactory.getLogger(TableAuthorizationMethod.class);

    @EJB
    UserDAO userDAO;

    public User getUser(String username) throws UserNotFoundException {

        cl.blueprintsit.framework.domain.User dbUser = getDBUser(username);
        return map(dbUser);
    }

    @Override
    public void lockUser(String username) throws UserNotFoundException {
        cl.blueprintsit.framework.domain.User dbUser = getDBUser(username);
        dbUser.setLocked(true);
        userDAO.update(dbUser);

    }

    @Override
    public void unlockUser(String username) throws UserNotFoundException {
        cl.blueprintsit.framework.domain.User dbUser = getDBUser(username);
        dbUser.setLocked(false);
        userDAO.update(dbUser);
    }

    @Override
    public void markLogin(String username) throws UserNotFoundException {
        cl.blueprintsit.framework.domain.User dbUser = getDBUser(username);
        dbUser.setLastLogin(new Date());
        dbUser.setFailedLoginAttempts(0);

        userDAO.update(dbUser);
        logger.info("User {} logged in",username);
    }

    @Override
    public void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException {

        cl.blueprintsit.framework.domain.User dbUser = getDBUser(username);

        try {
            String secret = randomAlphaNumeric(16);
            dbUser.setPassSecret(secret);
            dbUser.setPassSecretEncrypted(encrypt(secret,password));
            dbUser.setLastPasswordChange(new Date());
        } catch (AuthenticationException e) {
           throw new PasswordChangeException("Error al encriptar",e);
        }

        this.userDAO.update(dbUser);
    }


    private cl.blueprintsit.framework.domain.User getDBUser(String username) throws UserNotFoundException {
        cl.blueprintsit.framework.domain.User dbUser = userDAO.getByUsername(username);
        if(dbUser==null)
            throw new UserNotFoundException("Usuario no existe.");
        return dbUser;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private String randomAlphaNumeric(int lenght) {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();

        int count = lenght;
        while (count-- != 0) {
            int character =  random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private byte[] encrypt(String strClearText,String strKey) throws AuthenticationException {
        try {
            return Encryption.encrypt(strClearText,strKey);
        }catch (EncryptionException e){
            throw  new AuthenticationException("error al encriptar",e);
        }
    }


    private User map(cl.blueprintsit.framework.domain.User dbUser) {

        User user = new User();

        user.setEmail(dbUser.getEmail());
        user.setName(dbUser.getName());
        user.setUsername(dbUser.getUsername());
        user.setGroups(map(dbUser.getGroups()));
        user.setLastLogin(dbUser.getLastLogin());

        return user;
    }

    private Group map(cl.blueprintsit.framework.domain.Group dbGroup) {
        Group group = new Group();
        group.setName(dbGroup.getName());
        group.setDescription(dbGroup.getDescription());

        return group;
    }

    private List<Group> map(List<cl.blueprintsit.framework.domain.Group> dbGroups) {
        List<Group> ret = new ArrayList<Group>();

        for (cl.blueprintsit.framework.domain.Group dbGroup : dbGroups) {
            ret.add(map(dbGroup));
        }
        return ret;
    }




}
