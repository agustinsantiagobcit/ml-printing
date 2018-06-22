package cl.metlife.printing.beans;

import cl.metlife.printing.domain.*;
import cl.metlife.printing.generation.GenerationServiceManager;
import cl.metlife.printing.managers.DocumentManager;
import cl.metlife.printing.managers.ReportsManager;
import cl.metlife.saam.domain.ProcessExecution;
import cl.metlife.saam.web.controller.BaseBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Blueprints on 1/31/3500.
 */

@ManagedBean(name="reportsBean")
@ViewScoped
public class ReportsBean extends BaseBean implements Serializable {

    /* Init params */
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsBean.class);

    /* Form params */
    private String documentType;
    private String policy;
    private String rut;
    private Date dateFrom;
    private Date dateTo;

    /* Table List */
    public List<Document> documentList;

    /* Persistence Objects */
    @EJB
    private ReportsManager reportsManager;

    @EJB
    private GenerationServiceManager generationServiceManager;

    @EJB
    private DocumentManager documentManager;


    public void search(){
        this.documentList = reportsManager.findDocumentListByFilters(documentType, policy, rut, dateFrom, dateTo);
    }

    public List<Document>getAllDocuments(){
        if (this.documentList == null) {
            this.documentList = new ArrayList<Document>();
        }
        return this.documentList;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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


    public void makeNewPrint(Document refDocument){

        Document document = documentManager.getById(refDocument.getId());

        long printNumber = document.getPrints().size()+1;

        String finalpath = "/prints/Document_"+document.getId()+"_print_"+printNumber+".pdf";

        Print p = makePrint(finalpath);
        p.setPrintNumber(printNumber);

        document.getPrints().add(p);

        document.setDocumentStatusId(DocumentStatus.ENVIADO_A_IMPRESION);
        document = documentManager.update(document);


        refDocument.setPrints(document.getPrints());

        byte[] bytes = generationServiceManager.createNewPrint(document.getId());

        try {
            writeFileToDisk(bytes,finalpath);
        } catch (IOException e) {
            LOGGER.error("Error al guardar archivo en disco",e);
            showError("Error","Error al escribir al disco");
        }


    }

    private Print makePrint( String finalpath) {
        Print print = new Print();
        print.setPath(finalpath);
        print.setPrintNumber(1L);
        print.setPrintTypeId(PrintType.PRINT_TYPE_REIMPRESION_MANUAL);
        print.setPrintStatusId(PrintStatus.REIMPRESO);
        print.setSendDate(new Date());
        print.setPath(finalpath);
        return print;
    }

    private String writeFileToDisk(byte[] file,String finalpath) throws IOException {



        File folder = new File("/prints/");
        folder.mkdirs();


        FileOutputStream fos =null;
        try {
            fos = new FileOutputStream(finalpath);
            fos.write(file);
        }finally {
            if(fos!=null)
                fos.close();
        }
        return finalpath;
    }

    public List<Print> getPrints(Document document){
        return reportsManager.getDocumentPrints(document);
    }

    public StreamedContent downloadPrint(Print print, Document document){

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }

        byte[] bytes = generationServiceManager.getPrint(document.getId(), print.getPrintNumber());

        String filename = document.getClientRut() + "_" + print.getId() + ".pdf";
        return new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/pdf", filename);

    }
}

