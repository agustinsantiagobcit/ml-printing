package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.DocumentData;
import cl.metlife.printing.domain.DocumentStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class DocumentStatusDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public DocumentStatus getById(Long id) {
        return em.find(DocumentStatus.class,id);
    }

    public List<DocumentStatus> findAll() {
        List<DocumentStatus> list = em.createQuery("select i from DocumentStatus i ORDER BY i.id asc").getResultList();

        for (DocumentStatus data : list) {
            em.detach(data);
        }

        return list;
    }

}