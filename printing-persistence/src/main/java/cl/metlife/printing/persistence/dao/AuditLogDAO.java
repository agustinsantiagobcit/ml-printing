package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.AuditLog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class AuditLogDAO implements Serializable {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public AuditLog getById(Long id) {
        return em.find(AuditLog.class,id);
    }

    public List<AuditLog> findAll() {
        List<AuditLog> list = em.createQuery("select i from AuditLog i ORDER BY i.id asc").getResultList();

        for (AuditLog data : list) {
            em.detach(data);
        }

        return list;
    }

    public List<AuditLog> findByDate(Date fromDate, Date toDate) {
        Query query= null;

        if(fromDate != null && toDate == null){
            query= em.createQuery("select i from AuditLog i where i.date >= :fromDate ORDER BY i.id asc");
            query.setParameter("fromDate",fromDate);
        }
        if(fromDate != null && toDate != null){
            query= em.createQuery("select i from AuditLog i where i.date between :fromDate and :toDate ORDER BY i.id asc");
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
        }
        if(fromDate == null ){
            return Collections.emptyList();
        }

        List<AuditLog> list = query.getResultList();

        for (AuditLog data : list) {
            em.detach(data);
        }
        return list;
    }

    public AuditLog create(AuditLog auditLog) {

        em.persist(auditLog);

        em.flush();

        return auditLog;
    }

    public AuditLog update(AuditLog auditLog) {
        if ( auditLog == null )
            throw new IllegalArgumentException("auditLog can't be null");

        AuditLog updated = em.merge(auditLog);
        em.flush();

        return updated;
    }

    public boolean delete(AuditLog auditLog) {

        AuditLog toDelete = em.find(AuditLog.class,auditLog.getId());
        em.remove(toDelete);

        return true;
    }

}