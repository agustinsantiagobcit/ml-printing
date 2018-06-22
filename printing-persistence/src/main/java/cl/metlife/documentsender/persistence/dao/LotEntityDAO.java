package cl.metlife.documentsender.persistence.dao;

import cl.metlife.documentsender.domain.LotEntity;
import cl.metlife.documentsender.domain.LotEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class LotEntityDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public LotEntity getById(Long id) {
        return em.find(LotEntity.class,id);
    }

    public List<LotEntity> findAll() {
        List<LotEntity> list = em.createQuery("select i from LotEntity i ORDER BY i.name asc").getResultList();

        for (LotEntity LotEntity : list) {
            em.detach(LotEntity);
        }

        return list;
    }

    public LotEntity create(LotEntity LotEntity) {

        em.persist(LotEntity);

        em.flush();

        return LotEntity;
    }

    public LotEntity update(LotEntity LotEntity) {
        if ( LotEntity == null )
            throw new IllegalArgumentException("LotEntity can't be null");

        LotEntity updated = em.merge(LotEntity);
        em.flush();

        return updated;
    }

    public boolean delete(LotEntity LotEntity) {

        LotEntity toDelete = em.find(LotEntity.class,LotEntity.getName());
        em.remove(toDelete);

        return true;
    }

}