package cl.metlife.printing.beans;


import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.domain.Sponsor;
import cl.metlife.printing.managers.ProcessManager;
import cl.metlife.printing.managers.SponsorManager;
import cl.metlife.printing.managers.TemplateManager;
import cl.metlife.saam.domain.Process;
import cl.metlife.saam.domain.templates.Template;
import cl.metlife.saam.web.controller.BaseBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "processEditBean")
@ViewScoped
public class ProcessEditBean extends BaseBean {

    private PrintingProcess item;
    private Long processId;

    @EJB
    private ProcessManager processManager;

    @EJB
    private SponsorManager sponsorManager;

    @EJB
    private TemplateManager templateManager;


    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;


    public void init(){

        if(item!=null)
            return;

        if(getProcessId()==null || getProcessId()==-1L){
            item = new PrintingProcess();
            item.setName("Nuevo Proceso");
        }
        else{
            item = processManager.getById(getProcessId());
        }

    }

    public void save(){

        if(item.getId()!=null){
            processManager.update(item,authenticationBean.getLoggedUser().getUsername());
            showInfo("Aviso", "Cambios guardados satisfactoriamente.");
        } else {
            processManager.create(item,authenticationBean.getLoggedUser().getUsername());
            showInfo("Éxito", "Se creó correctamente el Proceso.");
        }
    }


    public PrintingProcess getItem() {
        return item;
    }

    public void setItem(PrintingProcess item) {
        this.item = item;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }

    public List<Process> getAllSaamProcess(){
        return this.processManager.getAllSaamProcess();
    }


    public List<Sponsor> getAllSponsors(){
        return this.sponsorManager.getAllSponsor();
    }

    public List<Template> getAllTemplates(){
        return templateManager.getAllTemplates();
    }
}
