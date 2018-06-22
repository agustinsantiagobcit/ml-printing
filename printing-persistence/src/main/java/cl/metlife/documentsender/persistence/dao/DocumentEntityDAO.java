package cl.metlife.documentsender.persistence.dao;

import cl.metlife.documentsender.domain.DocumentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class DocumentEntityDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public DocumentEntity getById(Long id) {
        return em.find(DocumentEntity.class,id);
    }

    public List<DocumentEntity> findAll() {
        List<DocumentEntity> list = em.createQuery("select i from DocumentEntity i ORDER BY i.documentId asc").getResultList();

        for (DocumentEntity DocumentEntity : list) {
            em.detach(DocumentEntity);
        }

        return list;
    }

    public DocumentEntity create(DocumentEntity DocumentEntity) {

        em.persist(DocumentEntity);

        em.flush();

        return DocumentEntity;
    }

    public DocumentEntity update(DocumentEntity DocumentEntity) {
        if ( DocumentEntity == null )
            throw new IllegalArgumentException("DocumentEntity can't be null");

        DocumentEntity updated = em.merge(DocumentEntity);
        em.flush();

        return updated;
    }

    public boolean delete(DocumentEntity DocumentEntity) {

        DocumentEntity toDelete = em.find(DocumentEntity.class,DocumentEntity.getDocumentId());
        em.remove(toDelete);

        return true;
    }

}