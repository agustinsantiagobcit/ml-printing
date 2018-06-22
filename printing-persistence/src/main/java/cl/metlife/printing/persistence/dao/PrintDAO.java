package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.Print;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class PrintDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Print getById(Long id) {
        return em.find(Print.class,id);
    }

    public List<Print> findAll() {
        List<Print> list = em.createQuery("select i from Print i ORDER BY i.id asc").getResultList();

        for (Print data : list) {
            em.detach(data);
        }

        return list;
    }

    public Print create(Print print) {

        em.persist(print);

        em.flush();

        return print;
    }

    public Print update(Print print) {
        if ( print == null )
            throw new IllegalArgumentException("print can't be null");

        Print updated = em.merge(print);
        em.flush();

        return updated;
    }

    public boolean delete(Print print) {

        Print toDelete = em.find(Print.class,print.getId());
        em.remove(toDelete);

        return true;
    }

}