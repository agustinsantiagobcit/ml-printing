package cl.metlife.printing.domain;

import cl.metlife.saam.domain.ProcessExecution;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@javax.persistence.Table(name = "PRINTING_DOCUMENTOS")
public class Document extends BaseEntity implements Serializable {
    private Long id;
    private String clientRut;
    private String clientName;
    private String clientMail;
    private Date creationDate;
    private Long processId;
    private PrintingProcess process;
    private Long documentStatusId;
    private DocumentStatus documentStatus;
    private Long processExecutionId;
    private ProcessExecution processExecution;
    private List<Print> prints;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RUT_CLIENTE")
    public String getClientRut() {
        return clientRut;
    }

    public void setClientRut(String clientRut) {
        this.clientRut = clientRut;
    }

    @Basic
    @Column(name = "NOMBRE_CLIENTE")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "EMAIL_CLIENTE")
    public String getClientMail() {
        return clientMail;
    }

    public void setClientMail(String clientMail) {
        this.clientMail = clientMail;
    }

    @Basic
    @Column(name = "FECHA_CREACION")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "EJECUCION_ID")
    public Long getProcessExecutionId() {
        return processExecutionId;
    }

    public void setProcessExecutionId(Long processExecutionId) {
        this.processExecutionId = processExecutionId;
    }

    @Basic
    @Column(name = "PRINTING_PROCESOS_ID", nullable = true, precision = 0)
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long procesoId) {
        this.processId = procesoId;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRINTING_PROCESOS_ID")
    public PrintingProcess getProcess() {
        return process;
    }

    public void setProcess(PrintingProcess process) {
        this.process = process;
    }

    @Basic
    @Column(name = "PRINTING_ESTADOS_DOC_ID", nullable = true, precision = 0)
    public Long getDocumentStatusId() {
        return documentStatusId;
    }

    public void setDocumentStatusId(Long documentStatusId) {
        this.documentStatusId = documentStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRINTING_ESTADOS_DOC_ID")
    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @JoinColumn(name = "PRINTING_DOCUMENTOS_ID")
    public List<Print> getPrints() {
        return prints;
    }

    public void setPrints(List<Print> printList) {
        this.prints = printList;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "EJECUCION_ID",referencedColumnName = "ID_EJECUCION_PROCESO")
    public ProcessExecution getProcessExecution() {
        return processExecution;
    }

    public void setProcessExecution(ProcessExecution processExecution) {
        this.processExecution = processExecution;
    }
}
