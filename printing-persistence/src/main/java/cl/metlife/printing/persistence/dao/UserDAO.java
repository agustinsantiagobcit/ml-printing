package cl.metlife.printing.persistence.dao;

import cl.blueprintsit.framework.domain.Group;
import cl.blueprintsit.framework.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class UserDAO {

    public static final String PARAMETER_CANT_BE_NULL = "Parameter can't be null";
    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public User getByUsername(String username) {

        try {
            User user = em.find(User.class,username);
            if(user!=null)
                cleanForOutput(user);
            return user;
        }catch (NoResultException e){
            return null;
        }
    }

    public List<User> findAll() {
        Query query = em.createQuery("select i from User i");

        List<User> resultList = query.getResultList();

        for (User user : resultList) {
            cleanForOutput(user);
        }

        List<User> returnList = new ArrayList<User>();
        returnList.addAll(resultList);

        return returnList;
    }

    public User create(User user) throws UserExistsException{

        if ( user == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        if(getByUsername(user.getUsername())!= null)
            throw new UserExistsException("Ya existe un usuario con ese nombre");


        List<Group> groups = user.getGroups();
        user.setGroups(new ArrayList<Group>());

        em.persist(user);

        user.setGroups(groups);

        em.merge(user);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        cleanForOutput(user);
        return user;
    }

    /**
     * deatachs entity and turns children list into arraylist (to be editable)
     * @param user User Entity
     */
    private void cleanForOutput(User user) {
        user.getGroups();
        em.detach(user);
        ArrayList<Group> groups = new ArrayList<Group>();
        groups.addAll(user.getGroups());
        user.setGroups(groups);
    }

    public User update(User user) {

        if ( user == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        User updated = em.merge(user);
        em.flush();

        cleanForOutput(updated);

        return updated;
    }

    public boolean delete(User user) {

        if ( user == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        User toDelete = em.find(User.class,user.getUsername());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

    public class UserExistsException extends Exception {
        public UserExistsException(String s) {
            super(s);
        }
    }
}