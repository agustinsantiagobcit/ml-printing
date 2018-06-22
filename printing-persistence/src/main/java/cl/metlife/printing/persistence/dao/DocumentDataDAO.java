package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.DocumentData;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class DocumentDataDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public DocumentData getById(Long id) {
        return em.find(DocumentData.class,id);
    }

    public List<DocumentData> findAll() {
        List<DocumentData> list = em.createQuery("select i from DocumentData i ORDER BY i.id asc").getResultList();

        for (DocumentData data : list) {
            em.detach(data);
        }

        return list;
    }

    public DocumentData create(DocumentData documentData) {

        em.persist(documentData);

        em.flush();

        return documentData;
    }

    public DocumentData update(DocumentData documentData) {
        if ( documentData == null )
            throw new IllegalArgumentException("documentData can't be null");

        DocumentData updated = em.merge(documentData);
        em.flush();

        return updated;
    }

    public boolean delete(DocumentData documentData) {

        DocumentData toDelete = em.find(DocumentData.class,documentData.getId());
        em.remove(toDelete);

        return true;
    }

}