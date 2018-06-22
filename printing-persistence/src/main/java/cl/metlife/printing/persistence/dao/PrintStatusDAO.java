package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.DocumentStatus;
import cl.metlife.printing.domain.PrintStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class PrintStatusDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public PrintStatus getById(Long id) {
        return em.find(PrintStatus.class,id);
    }

    public List<PrintStatus> findAll() {
        List<PrintStatus> list = em.createQuery("select i from PrintStatus i ORDER BY i.id asc").getResultList();

        for (PrintStatus data : list) {
            em.detach(data);
        }

        return list;
    }

}