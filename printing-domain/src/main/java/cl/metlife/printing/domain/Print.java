package cl.metlife.printing.domain;

import cl.metlife.saam.domain.ProcessExecution;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "PRINTING_IMPRESIONES")
public class Print extends BaseEntity implements Serializable {
    private Long id;
    private String path;
    private Date sendDate;
    private Long printNumber;
    private Long processExecutionId;
    private ProcessExecution processExecution;
    private Long printTypeId;
    private PrintType printType;
    private Long printStatusId;
    private PrintStatus printStatus;

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
    @Column(name = "NUMERO_IMPRESION")
    public Long getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Long printNumber) {
        this.printNumber = printNumber;
    }

    @Basic
    @Column(name = "RUTA")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "FECHA_ENVIO")
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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
    @Column(name = "PRINTING_ESTADOS_IMPRESION_ID", nullable = true, precision = 0)
    public Long getPrintStatusId() {
        return printStatusId;
    }

    public void setPrintStatusId(Long printStatusId) {
        this.printStatusId = printStatusId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRINTING_ESTADOS_IMPRESION_ID")
    public PrintStatus getPrintStatus() {
        return printStatus;
    }

    public void setPrintStatus(PrintStatus printStatus) {
        this.printStatus = printStatus;
    }

    @Basic
    @Column(name = "PRINTING_TIPOS_IMPRESION_ID", nullable = true, precision = 0)
    public Long getPrintTypeId() {
        return printTypeId;
    }

    public void setPrintTypeId(Long printTypeId) {
        this.printTypeId = printTypeId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRINTING_TIPOS_IMPRESION_ID")
    public PrintType getPrintType() {
        return printType;
    }

    public void setPrintType(PrintType printType) {
        this.printType = printType;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "EJECUCION_ID", referencedColumnName = "ID_EJECUCION_PROCESO")
    public ProcessExecution getProcessExecution() {
        return processExecution;
    }

    public void setProcessExecution(ProcessExecution processExecution) {
        this.processExecution = processExecution;
    }
}
