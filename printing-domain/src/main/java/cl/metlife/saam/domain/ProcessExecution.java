package cl.metlife.saam.domain;

import cl.metlife.printing.domain.PrintingProcess;
import cl.metlife.saam.domain.runtime.ProcessExecutionRuntime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@javax.persistence.Table(name = "SAAM2_EJECUCION_PROCESO")
public class ProcessExecution extends BaseEntity implements Serializable {
    private Long id;
    private Long processId;
    private Long startTypeId;
    private Long executionStatusId;
    private String starterUserName;
    private ExecutionStatus executionStatus;
    private ProcessStartType startType;
    private Date startDate;
    private int issuedDocuments;
    private int generatedDocuments;
    private int sentDocuments;
    private PrintingProcess process;

    @ManyToOne
    @JoinColumn(name = "PROCESO_ID", referencedColumnName = "PROCESO_ID")
    public PrintingProcess getProcess() {
        return process;
    }

    public void setProcess(PrintingProcess process) {
        this.process = process;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EJECUCION_PROCESO", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROCESO_ID", nullable = true, precision = 0)
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long procesoId) {
        this.processId = procesoId;
    }


    @Basic
    @Column(name = "TIPO_INICIO_ID", nullable = true, precision = 0)
    public Long getStartTypeId() {
        return startTypeId;
    }

    public void setStartTypeId(Long tipoInicioId) {
        this.startTypeId = tipoInicioId;
    }

    @Basic
    @Column(name = "ESTADO_EJECUCION_ID", nullable = true, precision = 0)
    public Long getExecutionStatusId() {
        return executionStatusId;
    }

    public void setExecutionStatusId(Long executionStatusId) {
        this.executionStatusId = executionStatusId;
    }

    @Basic
    @Column(name = "USUARIO_INICIADOR", nullable = true, length = 255)
    public String getStarterUserName() {
        return starterUserName;
    }

    public void setStarterUserName(String starterUserName) {
        this.starterUserName = starterUserName;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ESTADO_EJECUCION_ID")
    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_INICIO_ID")
    public ProcessStartType getStartType() {
        return startType;
    }

    public void setStartType(ProcessStartType startType) {
        this.startType = startType;
    }


    public ProcessExecutionRuntime processExecutionRuntime;

    @Transient
    public ProcessExecutionRuntime getProcessExecutionRuntime() {
        return processExecutionRuntime;
    }

    public void setProcessExecutionRuntime(ProcessExecutionRuntime processExecutionRuntime) {
        this.processExecutionRuntime = processExecutionRuntime;
    }

    @Basic
    @Column(name = "FECHA_INICIO")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "PRINTING_DOC_EMITIDOS")
    public int getIssuedDocuments() {
        return issuedDocuments;
    }

    public void setIssuedDocuments(int issuedDocuments) {
        this.issuedDocuments = issuedDocuments;
    }

    @Basic
    @Column(name = "PRINTING_DOC_GENERADOS")
    public int getGeneratedDocuments() {
        return generatedDocuments;
    }

    public void setGeneratedDocuments(int generatedDocuments) {
        this.generatedDocuments = generatedDocuments;
    }

    @Basic
    @Column(name = "PRINTING_DOC_ENVIADOS")
    public int getSentDocuments() {
        return sentDocuments;
    }

    public void setSentDocuments(int sentDocuments) {
        this.sentDocuments = sentDocuments;
    }
}
