package cl.metlife.printing.beans;


import cl.metlife.saam.domain.templates.Template;
import cl.metlife.printing.managers.TemplateManager;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;

@ManagedBean(name = "templatePreviewBean")
@SessionScoped
public class TemplatePreviewBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplatePreviewBean.class);

    @EJB
    private TemplateManager templateManager;

    public StreamedContent getPDFPreview(){
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        String templateId = context.getExternalContext().getRequestParameterMap().get("templateId");

        Template openTemplate = TemplateEditBean.openTemplates.get(Long.parseLong(templateId));

        try {
            byte[] pdf = templateManager.getPreviewPDF(openTemplate);

            return new DefaultStreamedContent(new ByteArrayInputStream(pdf), "application/pdf", "preview.pdf");
        }catch (RuntimeException e){
            LOGGER.error("Error al generar contenido",e);
        }

        return new DefaultStreamedContent();
    }

}
