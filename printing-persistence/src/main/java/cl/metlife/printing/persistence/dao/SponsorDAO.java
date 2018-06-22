package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.domain.Sponsor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class SponsorDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public Sponsor getById(Long id) {
        return em.find(Sponsor.class,id);
    }

    public List<Sponsor> findAll() {
        List<Sponsor> list = em.createQuery("select i from Sponsor i ORDER BY i.id asc").getResultList();
        if(list == null){
            return Collections.emptyList();
        }
        for (Sponsor data : list) {
            em.detach(data);
        }

        return list;
    }

    public Sponsor create(Sponsor sponsor) {

        em.persist(sponsor);

        em.flush();

        return sponsor;
    }

    public Sponsor update(Sponsor sponsor) {
        if ( sponsor == null )
            throw new IllegalArgumentException("sponsor can't be null");

        Sponsor updated = em.merge(sponsor);
        em.flush();

        return updated;
    }

    public boolean delete(Sponsor sponsor) {

        Sponsor toDelete = em.find(Sponsor.class,sponsor.getId());
        em.remove(toDelete);

        return true;
    }

}