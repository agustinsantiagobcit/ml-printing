package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.PrintStatus;
import cl.metlife.printing.domain.PrintType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class PrintTypeDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public PrintType getById(Long id) {
        return em.find(PrintType.class,id);
    }

    public List<PrintType> findAll() {
        List<PrintType> list = em.createQuery("select i from PrintType i ORDER BY i.id asc").getResultList();

        for (PrintType data : list) {
            em.detach(data);
        }

        return list;
    }

}