package cl.metlife.printing.beans;


import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.domain.AuditLogType;
import cl.metlife.printing.managers.AuditLogManager;
import cl.metlife.printing.managers.TemplateManager;
import cl.metlife.saam.domain.templates.Template;
import cl.metlife.saam.web.controller.BaseBean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name="templateBean")
@ViewScoped
public class TemplateBean extends BaseBean {

    @EJB
    TemplateManager templateManager;

    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;

    private List<Template> allTemplates;

    @PostConstruct
    public void init(){
        allTemplates = templateManager.getAllTemplates();
    }

    public void remove(Template plantilla){
        templateManager.delete(plantilla, authenticationBean.getLoggedUser().getUsername() );
        allTemplates = templateManager.getAllTemplates();
    }

    public String clone(Template plantilla){
        Template clon = templateManager.clone(plantilla.getId(),authenticationBean.getLoggedUser().getUsername());
        return "editar?templateId="+clon.getId()+"&faces-redirect=true";
    }


    public List<Template> getAllTemplates() {
        return allTemplates;
    }

    public void setAllTemplates(List<Template> allTemplates) {
        this.allTemplates = allTemplates;
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }
}
