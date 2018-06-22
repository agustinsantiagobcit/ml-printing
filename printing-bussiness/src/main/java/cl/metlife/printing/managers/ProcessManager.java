package cl.metlife.printing.managers;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.printing.domain.AuditLogType;
import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.printing.persistence.dao.PrintingProcessDAO;
import cl.metlife.saam.domain.Process;
import cl.metlife.ws.clients.saam.execution.ProcessExecution;
import cl.metlife.ws.clients.saam.execution.ProcessExecutionService;
import cl.metlife.ws.clients.timer.*;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Singleton
public class ProcessManager {

    private static final Logger LOGGER = Logger.getLogger(ProcessManager.class);

    @EJB
    private PrintingProcessDAO printingProcessDAO;

    @EJB
    private ConfigurationManager configurationManager;

    @EJB
    private AuditLogManager logManager;

    private ProcessTimer processTimer;
    private ProcessExecution processExecution;

    private static final String WSDL_CONFIG_KEY_TIMER =         "processTimer.service.wsdl";
    private static final String ENDPOINT_CONFIG_KEY_TIMER =     "processTimer.service.endpoint";
    private static final String WSDL_CONFIG_KEY_EXECUTION =     "processExecution.service.wsdl";
    private static final String ENDPOINT_CONFIG_KEY_EXECUTION = "processExecution.service.endpoint";


    public List<PrintingProcess> findProcessListByFilters(Long sponsorID) {
        return printingProcessDAO.findBySponsor(sponsorID);
    }

    public List<PrintingProcess> getAllPrintingProcess(){
        return printingProcessDAO.findAll();
    }

    public List<Process> getAllSaamProcess(){
        return printingProcessDAO.findAllSaamProcess();
    }

    public void initProcessTimerService(Long processId,String username){
        logManager.log(
                AuditLogType.INICIA_PROCESO_PRINTING,
                "Se inicia el proceso printing: "+ processId,
                username);

        this.getExecutionService().initProcess(processId,null,null);
    }

    public void setProcessTimerService(SimpleDisplayableTimer simpleDisplayableTimer,Long processId, String usuario){

        String log = "second: "+simpleDisplayableTimer.getSecond()+
        ", minute: "+simpleDisplayableTimer.getMinute()+
        ", hour: "+simpleDisplayableTimer.getHour()+
        ", dayOfMonth: "+simpleDisplayableTimer.getDayOfMonth()+
        ", month: "+simpleDisplayableTimer.getMonth()+
        ", dayOfWeek: "+simpleDisplayableTimer.getDayOfWeek()+
        ", year: "+simpleDisplayableTimer.getYear();


        logManager.log(AuditLogType.PROGRAMAR_PROCESO,log,usuario);
        this.getTimerService().setTimer(simpleDisplayableTimer,processId);
    }

    public SimpleDisplayableTimer getSchedulerTimerByProcessSAAM(Long processId){
        return this.getTimerService().getTimer(processId);
    }

    public Date getNextExecutionForProcessId(Long processId){
        XMLGregorianCalendar xmlGregorianCalendar = this.getTimerService().getNextExecutionForProcess(processId);

        if(xmlGregorianCalendar == null)
            return null;

        return xmlGregorianCalendar.toGregorianCalendar().getTime();
    }


    public ProcessTimer getTimerService(){
        if (processTimer == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY_TIMER).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY_TIMER).getValor();

            ProcessTimerService service = null;
            try {
                service = new ProcessTimerService(new URL(wsdl_url));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.processTimer = service.getProcessTimerPort();
            setEndpointAddress(processTimer, endpoint_url);
        }
        return processTimer;
    }

    public ProcessExecution getExecutionService(){
        if (processExecution == null) {
            String wsdl_url = configurationManager.getByKey(WSDL_CONFIG_KEY_EXECUTION).getValor();
            String endpoint_url = configurationManager.getByKey(ENDPOINT_CONFIG_KEY_EXECUTION).getValor();

            ProcessExecutionService service = null;
            try {
                service = new ProcessExecutionService(new URL(wsdl_url));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error con URL", e);
            }
            this.processExecution = service.getProcessExecutionPort();
            setEndpointAddress(processExecution, endpoint_url);
        }

        return processExecution;
    }

    private void setEndpointAddress(Object port, String newAddress) {
        BindingProvider bp = (BindingProvider) port;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, newAddress);
    }

    public PrintingProcess getById(Long processId) {
        return printingProcessDAO.getById(processId);
    }

    public PrintingProcess update(PrintingProcess item, String username) {

        PrintingProcess update = printingProcessDAO.update(item);
        logManager.log(AuditLogType.PROGRAMAR_PROCESO,"Se actualiza proceso "+update ,username);

        return update;
    }

    public PrintingProcess create(PrintingProcess item, String username) {

        PrintingProcess printingProcess = printingProcessDAO.create(item);
        logManager.log(AuditLogType.PROGRAMAR_PROCESO,"Se actualiza proceso "+printingProcess,username);
        return printingProcess;
    }
}