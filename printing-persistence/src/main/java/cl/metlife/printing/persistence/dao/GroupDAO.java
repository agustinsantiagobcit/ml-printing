package cl.metlife.printing.persistence.dao;


import cl.blueprintsit.framework.domain.Group;

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
public class GroupDAO {

    public static final String PARAMETER_CANT_BE_NULL = "PArameter can't be null";
    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Group getById(long id) {

        try {
            Group group = em.find(Group.class,id);
            em.detach(group);
            return group;
        }catch (NoResultException e){
            return null;
        }

    }

    public List<Group> findAll() {
        Query query = em.createQuery("select i from Group i");

        List<Group> resultList = query.getResultList();

        for (Group group : resultList) {
            em.detach(group);
        }

        List<Group> returnList = new ArrayList<Group>();
        returnList.addAll(resultList);

        return returnList;
    }

    public Group create(Group group) {
        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        em.persist(group);
        //se debe hacer flush para garantizar la creacion del ID
        em.flush();
        em.detach(group);
        return group;
    }

    public Group update(Group group) {

        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Group updated = em.merge(group);
        em.flush();

        em.detach(updated);

        return updated;
    }

    public boolean delete(Group group) {

        if ( group == null )
            throw new IllegalArgumentException(PARAMETER_CANT_BE_NULL);

        Group toDelete = em.find(Group.class,group.getId());
        
        if(toDelete==null)
            return false;
        
        em.remove(toDelete);

        return true;
    }

}