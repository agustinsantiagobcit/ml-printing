package cl.metlife.printing.managers;

import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.persistence.dao.PrintingProcessDAO;
import cl.metlife.saam.domain.templates.Parameter;
import cl.metlife.saam.domain.templates.TemplateElement;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class PrintingProcessManager {

    @EJB
    PrintingProcessDAO processDAO;

    public PrintingProcess getById(Long id) {
        return processDAO.getById(id);
    }

    public List<PrintingProcess> findAll() {
        return processDAO.findAll();
    }

    public PrintingProcess create(PrintingProcess process) {
        return processDAO.create(process);
    }

    public PrintingProcess update(PrintingProcess process) {
        return processDAO.update(process);
    }

    public boolean delete(PrintingProcess process) {
        return processDAO.delete(process);
    }

}
