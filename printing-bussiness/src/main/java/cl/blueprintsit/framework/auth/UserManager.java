package cl.blueprintsit.framework.auth;

import cl.blueprintsit.framework.domain.User;
import cl.blueprintsit.framework.domain.Group;
import cl.metlife.printing.persistence.dao.GroupDAO;
import cl.metlife.printing.persistence.dao.UserDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Created by Francisco Mendez on 23-05-2017.
 */
@Stateless
public class UserManager {

    @EJB
    UserDAO userDAO;

    @EJB
    GroupDAO groupDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User getByUsername(String username){
        return userDAO.getByUsername(username);
    }

    public User create(User user) throws UsernameExistsException {
        try{

            user.setCreationDate(new Date());
            user.setFailedLoginAttempts(0);
            user.setLocked(Boolean.FALSE);

            return userDAO.create(user);
        }catch (UserDAO.UserExistsException e){
            throw new UsernameExistsException("Ya existe un usuario con ese nombre");
        }
    }


    public User update(User user){
        return userDAO.update(user);
    }


    public Boolean delete(User user){
        return userDAO.delete(user);
    }

    public Group getGroupById(Long id) {
        return groupDAO.getById(id);
    }

    public List<Group> getAllGroups() {
        return groupDAO.findAll();
    }

    public cl.blueprintsit.framework.domain.User createUserInDBForTracking(cl.blueprintsit.framework.auth.User bpfwUser) throws UsernameExistsException {

        cl.blueprintsit.framework.domain.User userInDB = new cl.blueprintsit.framework.domain.User();

        userInDB.setLocked(false);
        userInDB.setUsername(bpfwUser.getUsername());
        userInDB.setCreationDate(new Date());
        userInDB.setEmail(bpfwUser.getEmail());
        userInDB.setName(bpfwUser.getName());


        try {
            return userDAO.create(userInDB);
        } catch (UserDAO.UserExistsException e) {
            throw new UsernameExistsException(e.getMessage(),e);
        }

    }
}
