package cl.metlife.printing.beans;

import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.managers.ProcessManager;
import cl.metlife.saam.web.controller.BaseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 1/31/3500.
 */

@ManagedBean(name="processBean")
@ViewScoped
public class ProcessBean extends BaseBean implements Serializable {

    /* Init params */
    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(ProcessBean.class);


    /* Table List */
    public List<PrintingProcess> dataList;

    /* Persistence Objects */
    @EJB
    private ProcessManager processManager;


    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;


    public void initProcess(PrintingProcess printingProcess){
        processManager.initProcessTimerService(printingProcess.getSaamProcessId(),authenticationBean.getLoggedUser().getUsername());
    }

    public List<PrintingProcess> getAllProcesses(){
        return processManager.getAllPrintingProcess();
    }

    public Date getNextExecution(PrintingProcess process){
        if(process == null)
            return null;

        return processManager.getNextExecutionForProcessId(process.getSaamProcessId());
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }
}

