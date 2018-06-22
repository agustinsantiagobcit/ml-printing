package cl.metlife.printing.managers;

import cl.metlife.printing.persistence.dao.ProcessExecutionDAO;
import cl.metlife.saam.domain.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


/**
 * Created by BluePrints Developer on 25-01-2017.
 */
@Stateless
public class ProcessExecutionManager {

    @EJB
    private ProcessExecutionDAO processExecutionDAO;

    public ProcessExecution getById(Long id) {
        return processExecutionDAO.getById(id);
    }

    public List<ProcessExecution> findPrintingExecutions() {
        return processExecutionDAO.findPrintingExecutions();
    }
}
