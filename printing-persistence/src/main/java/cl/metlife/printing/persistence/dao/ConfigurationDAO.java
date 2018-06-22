package cl.metlife.printing.persistence.dao;

import cl.blueprintsit.framework.domain.Configuration;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ConfigurationDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Configuration getByKey(String key) {
        Query query = em.createQuery("select i from Configuration i where i.llave=:llave");

        query.setParameter("llave", key);

        return (Configuration)query.getSingleResult();
    }

    public List<Configuration> findAll() {
        return em.createQuery("select i from Configuration i ORDER BY i.llave asc").getResultList();
    }

    public Configuration create(Configuration configuration) {

        em.persist(configuration);

        em.flush();

        return configuration;
    }

    public Configuration update(Configuration configuration) {
        if ( configuration == null )
            throw new IllegalArgumentException("configuration can't be null");

        Configuration updated = em.merge(configuration);
        em.flush();

        return updated;
    }

    public boolean delete(Configuration configuration) {

        Configuration toDelete = em.find(Configuration.class,configuration.getLlave());
        em.remove(toDelete);

        return true;
    }

}