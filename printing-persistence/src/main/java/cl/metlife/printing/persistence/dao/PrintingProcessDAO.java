package cl.metlife.printing.persistence.dao;

import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.saam.domain.Process;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Blueprints on 9/16/2015.
 */
@Stateless
public class PrintingProcessDAO {

    @PersistenceContext(unitName = "SAAMPersistenceUnit")
    private EntityManager em;

    public PrintingProcess getById(Long id) {
        return em.find(PrintingProcess.class,id);
    }

    public List<PrintingProcess> findAll() {
        List<PrintingProcess> list = em.createQuery("select i from PrintingProcess i ORDER BY i.id asc").getResultList();

        for (PrintingProcess data : list) {
            em.detach(data);
        }

        return list;
    }

    public List<Process> findAllSaamProcess() {
        List<Process> list = em.createQuery("select i from Process i ORDER BY i.id asc").getResultList();

        for (Process data : list) {
            em.detach(data);
        }

        return list;
    }

    public List<PrintingProcess> findBySponsor(Long sponsorId) {
        List<PrintingProcess> list = em.createQuery("select i from PrintingProcess i where i.sponsorId=:sponsorID ORDER BY i.id asc").setParameter("sponsorID",sponsorId).getResultList();

        for (PrintingProcess data : list) {
            em.detach(data);
        }

        return list;
    }

    public PrintingProcess create(PrintingProcess printingProcess) {

        em.persist(printingProcess);

        em.flush();

        return printingProcess;
    }

    public PrintingProcess update(PrintingProcess printingProcess) {
        if ( printingProcess == null )
            throw new IllegalArgumentException("printingProcess can't be null");

        PrintingProcess updated = em.merge(printingProcess);
        em.flush();

        return updated;
    }

    public boolean delete(PrintingProcess printingProcess) {

        PrintingProcess toDelete = em.find(PrintingProcess.class,printingProcess.getId());
        em.remove(toDelete);

        return true;
    }

}