package cl.metlife.documentsender.persistence.dao;

import cl.metlife.documentsender.domain.ContentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ContentEntityDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public ContentEntity getById(Long id) {
        return em.find(ContentEntity.class,id);
    }

    public List<ContentEntity> findAll() {
        List<ContentEntity> list = em.createQuery("select i from ContentEntity i ORDER BY i.id asc").getResultList();

        for (ContentEntity ContentEntity : list) {
            em.detach(ContentEntity);
        }

        return list;
    }

    public ContentEntity create(ContentEntity ContentEntity) {

        em.persist(ContentEntity);

        em.flush();

        return ContentEntity;
    }

    public ContentEntity update(ContentEntity ContentEntity) {
        if ( ContentEntity == null )
            throw new IllegalArgumentException("ContentEntity can't be null");

        ContentEntity updated = em.merge(ContentEntity);
        em.flush();

        return updated;
    }

    public boolean delete(ContentEntity ContentEntity) {

        ContentEntity toDelete = em.find(ContentEntity.class,ContentEntity.getId());
        em.remove(toDelete);

        return true;
    }

}