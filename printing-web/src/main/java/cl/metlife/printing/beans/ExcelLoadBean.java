package cl.metlife.printing.beans;

import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.domain.AuditLogType;
import cl.metlife.printing.managers.AuditLogManager;
import cl.metlife.printing.managers.PrintingProcessManager;
import cl.metlife.printing.managers.ProcessManager;
import cl.metlife.saam.web.controller.BaseBean;
import cl.metlife.ws.clients.saam.execution.ProcessExecution;
import cl.metlife.ws.clients.saam.execution.StringArray;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 1/31/3500.
 */

@ManagedBean(name="nnLoadBean")
@ViewScoped
public class ExcelLoadBean extends BaseBean implements Serializable {

    /* Init params */
    private static final long serialVersionUID = 1L;
    static final Logger logger = LoggerFactory.getLogger(ExcelLoadBean.class);
    static final String CSV_SEPARATOR = ";";
    static final String LINE_BREAK = "\n";

    /* Form params */
    private Long printingProcessId;
    private UploadedFile file;

    /* EJB Managers */
    @EJB
    private ProcessManager saamProcessManager;

    @EJB
    private PrintingProcessManager printingProcessManager;


    @EJB
    private AuditLogManager auditLogManager;

    /* Session Bean */
    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;


    /**
     * This method start the load of CSV file.
     */
    public void startLoad(){
        auditLogManager.log(
                AuditLogType.INICIA_CARGA_NN,
                "Se inicia carga NN de archivo: " + file.getFileName(),
                authenticationBean.getLoggedUser().getUsername());

        try{

            List<StringArray> forceData = new ArrayList<StringArray>();


            Iterable<CSVRecord> csvRecords = parseFile();

            boolean first =true;

            for (CSVRecord row : csvRecords) {
                if(first){
                    first=false;
                    if(!row.toMap().isEmpty()) {
                        forceData.add(makeHeaderStringArray(row));
                    }
                }
                StringArray stringArray = makeStringArray(row);
                forceData.add(stringArray);
            }

            ProcessExecution executionService = saamProcessManager.getExecutionService();

            Long saamProcessId = printingProcessManager.getById(printingProcessId).getSaamProcessId();

            Long executionId = executionService.initProcessWithData(saamProcessId, authenticationBean.getLoggedUser().getUsername(), null, forceData);

            showInfo("Aviso", "Se cargó correctamente el archivo. El servicio de ejecución SAAM se ejecutó satisfactoriamente.");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error", "Hubo un error en la carga del archivo.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "Hubo un error en la llamada al servicio.");
        }
    }

    private StringArray makeHeaderStringArray(CSVRecord row) {
        StringArray stringArray = new StringArray();
        List<String> cells = new ArrayList<String>();

        cells.addAll(row.toMap().keySet());
        stringArray.setItem(cells);

        return stringArray;
    }

    private StringArray makeStringArray(CSVRecord row) {
        StringArray stringArray = new StringArray();
        List<String> cells = new ArrayList<String>();

        for (int j = 0; j < row.size(); j++) {
            cells.add(row.get(j));
        }
        stringArray.setItem(cells);
        return stringArray;
    }


    private Iterable<CSVRecord> parseFile() throws IOException {

        InputStream inputstream = file.getInputstream();
        InputStreamReader isr = new InputStreamReader(inputstream);
        return CSVFormat.DEFAULT.parse(isr);
    }


    public List<PrintingProcess> getAllPrintingProcesses(){
        return saamProcessManager.getAllPrintingProcess();
    }

    public Long getPrintingProcessId() {
        return printingProcessId;
    }

    public void setPrintingProcessId(Long printingProcessId) {
        this.printingProcessId = printingProcessId;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }
}

