package cl.metlife.printing.persistence.dao;

import cl.metlife.saam.domain.ProcessExecution;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class ProcessExecutionDAO {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public ProcessExecution getById(Long id) {
        return em.find(ProcessExecution.class,id);
    }

    public List<ProcessExecution> findAll() {
        return em.createQuery("select i from ProcessExecution i ORDER BY i.id desc").getResultList();
    }

    public List<ProcessExecution> findLastExecutions() {
        return em.createQuery("select i from ProcessExecution i ORDER BY i.id desc").setMaxResults(100).getResultList();
    }

    public List<ProcessExecution> findPrintingExecutions() {
        String qlString = "select pe from ProcessExecution pe , PrintingProcess pp where pe.process IS NOT NULL ORDER BY pe.id desc";
        return em.createQuery(qlString).getResultList();
    }

}