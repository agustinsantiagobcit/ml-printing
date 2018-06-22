package cl.metlife.printing.beans;

import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.managers.PrintingProcessManager;
import cl.metlife.printing.managers.ProcessExecutionManager;
import cl.metlife.saam.domain.ProcessExecution;
import cl.metlife.saam.web.controller.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 1/31/3500.
 */

@ManagedBean(name="logbookBean")
@ViewScoped
public class LogbookBean extends BaseBean implements Serializable {

    /* Init params */
    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(LogbookBean.class);

    /* Form params */
    private Long selectedPrintingProcess;
    private Date dateFrom;
    private Date dateTo;

    /* Table List */
    public List<ProcessExecution> queryResult;

    /* Persistence Objects */

    @EJB
    private ProcessExecutionManager processExecutionManager;

    @EJB
    private PrintingProcessManager printingProcessManager;


    public void search(){
        if(selectedPrintingProcess == null || dateFrom == null || dateTo == null){
            this.addMessage("Error", "Los campos Plantilla printing, Fecha Desde y Fecha Hasta son obligatorios.");
        }
    }


    public List<PrintingProcess> getAllPrintingProcess(){
        return printingProcessManager.findAll();
    }

    public Long getSelectedPrintingProcess() {
        return selectedPrintingProcess;
    }

    public void setSelectedPrintingProcess(Long selectedPrintingProcess) {
        this.selectedPrintingProcess = selectedPrintingProcess;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }



    public List<ProcessExecution> getQueryResult() {
        if (queryResult == null)
            queryResult = processExecutionManager.findPrintingExecutions();
        return queryResult;
    }

    public void setQueryResult(List<ProcessExecution> queryResult) {
        this.queryResult = queryResult;
    }


}

